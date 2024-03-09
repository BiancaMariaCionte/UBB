#ifndef ACTION_H
#define ACTION_H
#include "Song2.h"

class Repository; // Forward declaration of Repository class

class Action {
public:
    virtual ~Action() {}
    virtual void apply(Repository& repository) = 0;
};

class ActionAdd : public Action {
public:
    ActionAdd(const Song& song);
    void apply(Repository& repository) override;

private:
    Song m_song;
};

class ActionDelete : public Action {
public:
    ActionDelete(const Song& song);
    void apply(Repository& repository) override;

private:
    Song m_song;
};

#endif // ACTION_H
