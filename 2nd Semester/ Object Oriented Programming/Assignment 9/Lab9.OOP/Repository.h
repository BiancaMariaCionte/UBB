#pragma once
#include <vector>
#include <algorithm>
#include <stdexcept>
#include <fstream>
#include <string>
#include <iostream>
#include <sstream>
#include "SongException.h"
#include"Song2.h"

class Repository
{
public:
    Repository() = default;
    Repository(const Repository&) = delete; // Disable copy constructor

    void store(const Song& s);
    const Song& find(const std::string& title, const std::string& artist);

    void remove(const Song& s);
    int getSize() const;
    const std::vector<Song>& getAllSongs() const;

private:
    std::vector<Song> songs;


    //bool songExists(const std::string& title, const std::string& artist) const;

};


class FileRepository : public Repository
{
public:
    FileRepository() :Repository(),filepath{ "" } {};
    FileRepository(const std::string& filepath) : Repository(), filepath(filepath) {}

    void load();

    void save();

private:
    std::string filepath;

    Song parseSong(const std::string& line);

    std::string songToString(const Song& song);
};