

#include <iostream>
#include<string>
#include <vector>

using namespace std;

class Character {

public:
    Character();
    Character(std::string name, int health, int level);

    //SETTERS
    int getHealth() const;
    int getLevel() const;
    std::string getName() const;

    //GETTERS
    void setHealth(int h);
    void setName(std::string n);
    void setLevel(int lvl);


protected:

    int health;
    int level;
    std::string name;
};

//--------------------------- WIZARD

class Wizard : public Character {
public:
    Wizard();
    Wizard(std::string name, int health, int level, int mana, std::vector<std::string> spells, int spellPower);

    bool castSpell(std::string spell); //checks if the wizard knows the spell and has enough mana to cast it

    //GETTERS
    void setMana(int mana);
    void setSpells(std::vector<std::string> spells); // takes a vector of strings 'spells' and sets the spells attribute of the wizard to the specified vector
    void setSpellPower(int spellPower);

    //SETTERS
    int getMana();
    int getSpellPower();
    std::vector<std::string> getSpells(); //is a getter function that returns the current value of the spells attribute.

private:
    int mana; //mana unchanged if the wizard does not know the requested spell
    std::vector<std::string>spells; //each requests 10 mana
    int spellPower;

};

//--------------------------- KNIGHT

class Knight : public Character {
public:
    Knight();
    Knight(std::string name, int health, int level, int armor, int swordDamage);
    void takeDamage(int damage); //reduces armor by the specified amount

    //GETTERS
    void setArmor(int armor);
    void setSwordDamage(int swordDamage);

    //SETTERS
    int getSwordDamage();
    int getArmor();

private:
    int armor; // between [0,1]
    int swordDamage;


};