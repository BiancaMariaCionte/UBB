#pragma once

#include "Repository.h"
#include "Song2.h"

class Playlist {
private:
    Repository m_repository;
    std::vector<Song> m_songs;

public:
    void addSong(const Song& song);
    void clear();
    void removeSong(const Song& song);
    void generateRandom(int n);
   
};