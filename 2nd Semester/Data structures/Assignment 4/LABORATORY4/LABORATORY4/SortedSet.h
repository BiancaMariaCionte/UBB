#pragma once
//DO NOT INCLUDE SETITERATOR
//DO NOT CHANGE THIS PART
typedef int TElem;
typedef TElem TComp; //create a type alias TComp that is equivalent to TElem (which is int)
typedef bool(*Relation)(TComp, TComp); // represents a relation function for the elements in the sorted set
#define NULL_TELEM -111111
class SortedSetIterator;
#include<vector>

class SortedSet 
{
	friend class SortedSetIterator;
private:
	struct Node
	{
		TElem info; //store the element value
		bool isActive; //checks if a position in the hashtable is occupied by an element
		bool isDeleted = false;
	};

	int capacity; //capacity of the hashtable
	Node* hashTable;//"pointer to Node" variable, hashTable, that will be used to represent the hashtable
	int count; //number of active elements in the hashtable i.e: size
	Relation relation;

	int hashFunction(TElem element) const {
		return abs(element) % capacity;
		//returns an integer representing the hash value for the element
	}

	int stepFunction(TElem element) const //used for determining the step size for double hashing
	{
		return 1 + (abs(element) % (capacity - 1));
		//we always move forward in the hashtable
	}
	

	
public:
	bool isPrime(int number) const;
	//constructor
	SortedSet(Relation r);

	//adds an element to the sorted set
	//if the element was added, the operation returns true, otherwise (if the element was already in the set) 
	//it returns false
	bool add(TComp e);

	
	//removes an element from the sorted set
	//if the element was removed, it returns true, otherwise false
	bool remove(TComp e);

	//checks if an element is in the sorted set
	bool search(TElem elem) const;


	//returns the number of elements from the sorted set
	int size() const;

	//checks if the sorted set is empty
	bool isEmpty() const;

	//returns an iterator for the sorted set
	SortedSetIterator iterator() const;

	// destructor
	~SortedSet();


};

/*
In open addressing, when a collision occurs,
a search is performed to find the next available or "open" slot in the array. 
This process continues until an empty slot is found. 
The search sequence is determined by a technique, which determines the order in which the slots are checked.

The technique we use is double hashing.

In double hashing, when a collision occurs, 
a secondary hash function is used to calculate a step size.
This step size is then added to the current position to determine the next position to probe.

*/