#pragma once
#include<string>
#include<vector>

class Song
{
private:
public:
	std::string title;
	std::string artist;
	int year;
	std::string link;
	std::string lyrics;

public:

	Song();
	Song(const std::string title, const std::string artist, const int year, const std::string lyrics, const std::string link);

	std::string getTitle() const;
	std::string getArtist() const;
	int getYear() const;
	std::string getLyrics() const;
	std::string getLink() const;
	

	//std::vector<std::string> getLyrics();

	void setTitle( std::string title);
	void setArtist( std::string artist);
	void setYear(int year);
	void setLink(std::string link);
	void setLyrics(std::string lyrics);

	bool operator==(const Song& other) const;

	std::string toString() const;
};