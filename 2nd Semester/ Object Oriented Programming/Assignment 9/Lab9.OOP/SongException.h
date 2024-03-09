#ifndef SONGEXCEPTION_H
#define SONGEXCEPTION_H

#include <exception>
#include <string>

class SongException : public std::exception
{
public:
    //when creating a SongException object, we need to explicitly call the constructor
    //with a std::string argument
    explicit SongException(const std::string& description) : description(description) {}
    const char* what() const noexcept override //const char* pointer that points to the description of the exception
    //noexept - promises that the function will not throw any exceptions
    { return description.c_str();// C-style string
    }

private:
    std::string description;
};

#endif // SONGEXCEPTION_H

/*
In order to create a SongException object:
std::string errorMessage = "An error occurred";
SongException exception(errorMessage);


*/
