#include "SongController.h"
#include "Repository.h"

/*
SongController::SongController()
{
     repository = Repository();
}
*/

void SongController::addSong(const std::string title, const std::string artist, int year, const std::string lyrics, const std::string link)
{
    try {
        const Song& foundSong = repository.find(title, artist);
        
    }
    catch (...) {
        //daca a aruncat exceptie, si am prins-o aici, inseamna ca nu avem elementul in lista
        // il putem adauga
        Song s;
        repository.store(s);
       
    }
}

void SongController::addSongToPlaylist(const Song& song) {
    // Add the song to the playlist
    m_playlist.push_back(song);

    // Push the Action onto the undo stack
    m_undoStack.push(new ActionAdd(song));
}

void SongController::removeSong(const std::string& title) {
    // Find the song with the matching title
    auto it = std::find_if(m_playlist.begin(), m_playlist.end(), [&](const Song& song) {
        return song.getTitle() == title;
        });

    if (it != m_playlist.end()) {
        Song song = *it;

        // Remove the song from the playlist
        m_playlist.erase(it);

        // Push the Action onto the undo stack
        m_undoStack.push(new ActionDelete(song));
    }
}

const Song& SongController::searchSong(const std::string& title, const std::string& artist)
{
    try {
        return repository.find(title, artist);
    }
    catch (const SongException&) {
        throw SongException("The song '" + title + "' by '" + artist + "' does not exist.");
    }
}

std::vector<Song> SongController::getSortedByArtist(bool ascendingOrder)
{
    std::vector<Song> sortedSongs = repository.getAllSongs();
    std::sort(sortedSongs.begin(), sortedSongs.end(), [&](const Song& song1, const Song& song2)
        {
            if (ascendingOrder)
            {
                return song1.getArtist() < song2.getArtist();
            }
            else
            {
                return song1.getArtist() > song2.getArtist();
            }
        });
    return sortedSongs;
}

void SongController::addAction(Action* action)
{
    m_undoStack.push(action);
    // Clear the redo stack since a new action is performed
    while (!m_redoStack.empty())
    {
        delete m_redoStack.top();
        m_redoStack.pop();
    }
}

std::vector<Song> SongController::getSortedByTitle(bool ascendingOrder)
{
    std::vector<Song> sortedSongs = repository.getAllSongs();
    std::sort(sortedSongs.begin(), sortedSongs.end(), [&](const Song& song1, const Song& song2)
        {
            if (ascendingOrder)
            {
                return song1.getTitle() < song2.getTitle();
            }
            else
            {
                return song1.getTitle() > song2.getTitle();
            }
        });
    return sortedSongs;
}

const std::vector<Song>& SongController::getAll() const
{
    return m_playlist;
}

void SongController::undo() {
    if (!m_undoStack.empty()) {
        // Pop the top action from the undo stack
        Action* action = m_undoStack.top();
        m_undoStack.pop();

        // Push the action onto the redo stack
        m_redoStack.push(action);

        // Apply the popped action (undo the operation)
        action->apply(repository);

        // Delete the action object
        delete action;
    }
}

void SongController::redo() {
    if (!m_redoStack.empty()) {
        // Pop the top action from the redo stack
        Action* action = m_redoStack.top();
        m_redoStack.pop();

        // Push the action onto the undo stack
        m_undoStack.push(action);

        // Apply the popped action (redo the operation)
        action->apply(repository);

        // Delete the action object
        delete action;
    }
}

std::string SongController::getLyrics(const std::string& title) {
    // Find the song with the given title
    auto it = std::find_if(m_playlist.begin(), m_playlist.end(), [&](const Song& song) {
        return song.getTitle() == title;
        });

    if (it != m_playlist.end()) {
        // Return the lyrics of the found song
        return it->getLyrics();
    }

    // Song with the given title not found
    throw std::runtime_error("Song not found.");
}

void SongController::generateRandomPlaylist(int n) {
    // Check if there are enough songs in the repository
    if (n > repository.getSize()) {
        throw std::out_of_range("Not enough songs in the repository.");
    }

    // Clear the current playlist
    m_playlist.clear();

    // Generate a random playlist of size n
    std::vector<Song> m_repository = repository.getAllSongs();
    std::random_device rd;
    std::mt19937 gen(rd());
    std::shuffle(m_repository.begin(), m_repository.end(), gen);
    m_playlist.assign(m_repository.begin(), m_repository.begin() + n);
}

