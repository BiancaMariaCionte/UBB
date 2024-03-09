#pragma once
#include <iostream>
#include "Repository.h"
#include "SongController.h"
//#include "Song2.h"

class RepositoryTest {
public:
    void runTests() {
        testStoreSong();
        testFindSong();
        testRemoveSong();
       
    }

private:
    void testStoreSong() {
        Repository repository;
        try {
            repository.store(Song("Title1", "Artist1", 2021, "Lyrics1", "Link1"));
            repository.store(Song("Title2", "Artist2", 2022, "Lyrics2", "link2"));
            
        }
        catch (const SongException& e) {
            std::cout << "Error storing song: " << e.what() << std::endl;
        }
    }

    void testFindSong() {
        Repository repository;
        try {
            repository.store(Song("Title1", "Artist1", 2021, "Lyrics1", "Link1"));
            repository.store(Song("Title2", "Artist2", 2022, "Lyrics2", "link2"));
            const Song& foundSong = repository.find("Title1", "Artist1");
            std::cout << "Found song: " << foundSong.getTitle() << " by " << foundSong.getArtist() << std::endl;
            
        }
        catch (const SongException& e) {
            std::cout << "Error finding song: " << e.what() << std::endl;
        }
    }

    void testRemoveSong() {
        Repository repository;
        try {
            repository.store(Song("Title1", "Artist1", 2021, "Lyrics1", "Link1"));
            repository.store(Song("Title2", "Artist2", 2022, "Lyrics2", "link2"));
            const Song& songToRemove = repository.find("Title1", "Artist1");
            repository.remove(songToRemove);
            
        }
        catch (const SongException& e) {
            std::cout << "Error removing song: " << e.what() << std::endl;
        }
    }
};

class FileRepositoryTest {
public:
    void runTests() {
        testLoadSongs();
        testSaveSongs();

    }

private:
    void testLoadSongs() {
        FileRepository fileRepository("songs.txt");
        try {
            fileRepository.load();
            const std::vector<Song>& songs = fileRepository.getAllSongs();
            for (const Song& song : songs) {
                std::cout << "Loaded song: " << song.getTitle() << " by " << song.getArtist() << std::endl;
            }
        }
        catch (const std::runtime_error& e) {
            std::cout << "Error loading songs: " << e.what() << std::endl;
        }
    }

    void testSaveSongs() {
        FileRepository fileRepository("songs.txt");
        try {
            fileRepository.store(Song("Title1", "Artist1", 2021, "Lyrics1", "Link1"));
            fileRepository.store(Song("Title2", "Artist2", 2022, "Lyrics2", "link2"));
            fileRepository.save();
            std::cout << "Songs saved successfully." << std::endl;
        }
        catch (const std::runtime_error& e) {
            std::cout << "Error saving songs: " << e.what() << std::endl;
        }
    }
};

class SongControllerTest {
public:
    void runTests() {
        testAddSong();
        testSearchSong();
        testGetSortedByArtist();
        testGetSortedByTitle();
        
    }

private:
    void testAddSong() {
        Repository repository;
        SongController songController(repository);
        try {
            songController.addSong("Title1", "Artist1", 2021, "Lyrics1", "Link1");
            songController.addSong("Title2", "Artist2", 2022, "Lyrics2", "link2");
         
        }
        catch (const SongException& e) {
            std::cout << "Error adding song: " << e.what() << std::endl;
        }
    }

    void testSearchSong() {
        Repository repository;
        SongController songController(repository);
        try {
            songController.addSong("Title1", "Artist1", 2021, "Lyrics1", "Link1");
            songController.addSong("Title2", "Artist2", 2022,"Lyrics2", "Link2");
            const Song& foundSong = songController.searchSong("Title1", "Artist1");
            std::cout << "Found song: " << foundSong.getTitle() << " by " << foundSong.getArtist() << std::endl;
           
        }
        catch (const SongException& e) {
            std::cout << "Error searching song: " << e.what() << std::endl;
        }
    }

    void testGetSortedByArtist() {
        Repository repository;
        SongController songController(repository);
        try {
            songController.addSong("Title1", "Artist1", 2021, "Lyrics1", "Link1");
            songController.addSong("Title2", "Artist2", 2022, "Lyrics2", "link2");
            std::vector<Song> sortedByArtist = songController.getSortedByArtist(true);
            for (const Song& song : sortedByArtist) {
                std::cout << "Song sorted by artist: " << song.getTitle() << " by " << song.getArtist() << std::endl;
            }
            
        }
        catch (const SongException& e) {
            std::cout << "Error getting sorted songs by artist: " << e.what() << std::endl;
        }
    }

    void testGetSortedByTitle() {
        Repository repository;
        SongController songController(repository);
        try {
            songController.addSong("Title1", "Artist1", 2021, "Lyrics1", "Link1");
            songController.addSong("Title2", "Artist2", 2022, "Lyrics2", "link2");
            std::vector<Song> sortedByTitle = songController.getSortedByTitle(true);
            for (const Song& song : sortedByTitle) {
                std::cout << "Song sorted by title: " << song.getTitle() << " by " << song.getArtist() << std::endl;
            }
           
        }
        catch (const SongException& e) {
            std::cout << "Error getting sorted songs by title: " << e.what() << std::endl;
        }
    }
};