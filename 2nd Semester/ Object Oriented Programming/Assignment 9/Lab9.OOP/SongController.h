#pragma once
#include <algorithm>
#include <stack>
#include <random>
#include "Repository.h"
#include "SongException.h"
#include"Action.h"

class SongController
{
    Repository& repository;
    //Repository m_repository;
    std::vector<Song> m_playlist;
    std::stack<Action*> m_undoStack;
    std::stack<Action*> m_redoStack;
public:
    

    SongController()= default;


    SongController(Repository& repository) : repository(repository) {}

    void addSong(const std::string title, const std::string artist, int year, const std::string lyrics = "", const std::string link ="");
    
    void addSongToPlaylist(const Song& song);

    void removeSong(const std::string& title);

    const Song& searchSong(const std::string& title, const std::string& artist);

    std::vector<Song> getSortedByArtist(bool ascendingOrder);
  
    std::vector<Song> getSortedByTitle(bool ascendingOrder);

    const std::vector<Song>& getAll() const;

    std::string getLyrics(const std::string& title);

    void generateRandomPlaylist(int n);

    void enableLyricsButton();

    void addAction(Action* action);

    void undo();

    void redo();
    
};