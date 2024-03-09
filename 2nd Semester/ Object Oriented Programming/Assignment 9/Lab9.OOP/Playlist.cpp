#include "Playlist.h"
#include "Repository.h"
#include "SongController.h"
#include <stdexcept>
#include <random>


void Playlist::addSong(const Song& song)
{
    m_songs.push_back(song);
}

void Playlist::clear() {
    m_songs.clear();
}

void Playlist::removeSong(const Song& song) {
    auto it = std::find(m_songs.begin(), m_songs.end(), song);
    if (it != m_songs.end()) {
        m_songs.erase(it);
    }
}

void Playlist::generateRandom(int n) {
    // Check if there are enough songs in the repository
    if (n > m_repository.getSize()) {
        throw std::out_of_range("Not enough songs in the repository.");
    }

    // Clear the current playlist
    clear();

    // Generate a random playlist of size n
    std::vector<Song> repository = m_repository.getAllSongs();
    std::random_device rd;
    std::mt19937 gen(rd()); //create a random number generator
    std::shuffle(repository.begin(), repository.end(), gen);
    m_songs.assign(repository.begin(), repository.begin() + n);
}
