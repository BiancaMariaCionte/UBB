#include "Repository.h"


void Repository::store(const Song& s)
{
    auto it = std::find_if(songs.begin(), songs.end(), [&](const Song& song)
        {
            return song.getTitle() == s.getTitle() && song.getArtist() == s.getArtist();
        });

    if (it != songs.end())
    {
        throw SongException("A song '" + s.getTitle() + "' by '" + s.getArtist() + "' already exists.");
    }

    songs.push_back(s);
}

int Repository::getSize() const {
    return songs.size();
}

const Song& Repository::find(const std::string& title, const std::string& artist)
{
    auto it = std::find_if(songs.begin(), songs.end(), [&](const Song& s)
        {
        return s.getTitle() == title && s.getArtist() == artist;
        }
    );

    if (it == songs.end()) {
        throw SongException("The song '" + title + "' by '" + artist + "' does not exist.");
    }

    return *it;
}

void Repository::remove(const Song& s)
{
    auto it = std::find(songs.begin(), songs.end(), s);

    if (it == songs.end()) {
        throw SongException("The song '" + s.getTitle() + "' by '" + s.getArtist() + "' does not exist.");
    }

    songs.erase(it);
}

const std::vector<Song>& Repository::getAllSongs() const
{
    return songs;
}


//bool Repository::songExists(const std::string& title, const std::string& artist) const
//{
//    for (const auto& song : songs) {
//        if (song.getTitle() == title && song.getArtist() == artist) {
//            return true;
//        }
//    }
//    return false;
//}


void FileRepository::load()
{
    std::ifstream file(filepath);
    if (!file.is_open()) {
        throw std::runtime_error("Failed to open file: " + filepath);
    }

    std::string line;
    while (std::getline(file, line)) {
        try {
            Song song = parseSong(line);
            store(song);
        }
        catch (const std::exception& e) {
         
        }
    }

    file.close();
}

void FileRepository::save()
{
    std::ofstream file(filepath);
    if (!file.is_open()) {
        throw std::runtime_error("Failed to open file: " + filepath);
    }

    const std::vector<Song>& songs = getAllSongs();
    for (const Song& song : songs) {
        file << songToString(song) << "\n";
    }

    file.close();
}

Song FileRepository::parseSong(const std::string& line)
{

    // we extract the title, artist, year, lyrics, and link from the line and create a Song using that values
    std::istringstream iss(line);
    std::string title, artist, yearStr, lyrics, link; //store the parsed values

    //We store the extracted values from "line" in their respective variables 
    if (std::getline(iss, title, ',') &&
        std::getline(iss, artist, ',') &&
        std::getline(iss, yearStr, ',') &&
        std::getline(iss, lyrics, ',') &&
        std::getline(iss, link)) {
        try {
            int year = std::stoi(yearStr); //convert the yearStr string to int
            return Song(title, artist, year, lyrics, link);
        }
        catch (const std::exception& e) {
            throw std::runtime_error("Invalid song format: " + line);
        }
    }
    else {
        throw std::runtime_error("Invalid song format: " + line);
    }
}

std::string FileRepository::songToString(const Song& song)
{
    std::ostringstream oss;
    oss << song.getTitle() << "," << song.getArtist() << "," << song.getYear() << ","
        << song.getLyrics() << "," << song.getLink();
    return oss.str();
}