#include "Action.h"
#include "Repository.h" // Include the Repository header file

ActionAdd::ActionAdd(const Song& song): m_song(song) {}

void ActionAdd::apply(Repository& repository) {
    repository.store(m_song);
}

ActionDelete::ActionDelete(const Song& song)
    : m_song(song) {}

void ActionDelete::apply(Repository& repository) {
    repository.remove(m_song);
}