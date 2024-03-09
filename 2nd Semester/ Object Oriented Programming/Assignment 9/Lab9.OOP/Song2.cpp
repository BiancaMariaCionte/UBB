#include "Song2.h"

Song::Song()
{
	this->title = "";
	this->artist = "";
	this->year = 0;
	this->lyrics = "";
	this->link = "";

}

Song::Song(std::string title, std::string artist, int year, std::string lyrics, std::string link)
{
	this->title = title;
	this->artist = artist;
	this->year = year;
	this->lyrics = lyrics;
	this->link = link;
}

std::string Song::getTitle() const
{
	return this->title;
}

std::string Song::getArtist() const
{
	return this->artist;
}

int Song::getYear() const
{
	return this->year;
}

std::string Song::getLyrics() const
{
	return this->lyrics;
}

std::string Song::getLink() const
{
	return this->link;
}
/*
std::vector<std::string> Song::getLyrics()
{
	return this->lyrics;
}
*/

void Song::setTitle( std::string title)
{
	this->title = title;
}

void Song::setArtist( std::string artist)
{
	this->artist = artist;
}

void Song::setYear(int year)
{
	this->year = year;
}

void Song::setLink(std::string link)
{
	this->link = link;
}

void Song::setLyrics(std::string lyrics)
{
	this->lyrics = lyrics;
}

bool Song::operator==(const Song& other) const
{
	return (title == other.title && artist == other.artist && year == other.year && lyrics == other.lyrics && link == other.link);
}

std::string Song::toString() const {
	std::string str = "Title: " + title + "\n";
	str += "Artist: " + artist + "\n";
	str += "Year: " + std::to_string(year) + "\n";
	str += "Lyrics: " + lyrics + "\n";
	str += "Link: " + link + "\n";
	return str;
}