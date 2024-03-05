#include "SortedSet.h"
#include "SortedSetIterator.h"
#include<iostream>

SortedSet::SortedSet(Relation r) 
{
	relation = r;
	capacity = 17;
	count = 0;
	hashTable = new Node[capacity]; //dynamically allocate an array of Node objects to represent the hashtable
	for (int i = 0; i < capacity; i++) {
		hashTable[i].isActive = false; //the array is initially empty
	}
}

/*
	Best Case: theta(1)
	Worst Case: theta(capacity)
	Average Case: O(capacity)

*/

bool SortedSet::isPrime(int number) const
{
	bool is_prime = true;
	if (number == 0 || number == 1) {
		is_prime = false;
	}

	for (int i = 2; i <= number / 2; i++) {
		if (number % i == 0) 
		{
			is_prime = false;
		}
	}
	return is_prime;
}

/*
	Best Case: theta(1)
	Worst Case: theta(sqrt(number))
	Average Case: O(sqrt(number))

*/

bool SortedSet::add(TComp e)
{
	if (count == capacity) 
	{
		// increase the capacity
		int oldCapacity = this->capacity;//we store the current capacity into a variable oldCapacity
		this->capacity = 2 * oldCapacity;
		while (!isPrime(this->capacity))
			this->capacity++;

		Node* newHashTable = new Node[this->capacity]; // create a new hashtable with the increased capacity

		// initialize the new hashtable slots
		for (int i = 0; i < this->capacity; i++) 
		{
			newHashTable[i].isActive = false; 
		}

		// rehash the existing elements into the new hashtable
		for (int i = 0; i < oldCapacity; i++) 
		{
			if (hashTable[i].isActive) 
			{
				//rehashe the element and inserte into the new hashtable
				int position = hashFunction(hashTable[i].info); //initial position in the new hashtable for the element
				int stepSize = stepFunction(hashTable[i].info); //step size for double hashing

				while (newHashTable[position].isActive)
				{
					position = (position + stepSize) % this->capacity;
				}

				newHashTable[position].info = hashTable[i].info; //the element from the existing hashtable is inserted into the new hashtable at the determined position
				newHashTable[position].isActive = true;
			}
		}

		// clean up the old hashtable
		delete[] hashTable;

		// update the hashtable
		hashTable = newHashTable;
	}

	int position = hashFunction(e); //compute the initial position in the hashtable for the element
	int stepSize = stepFunction(e); //compute the step size for double hashing

	while (hashTable[position].isActive) 
	{
		if (hashTable[position].info == e) // check if the element already exists in the set 
			return false;

		position = (position + stepSize) % this->capacity;
	}

	hashTable[position].info = e; // the element e is inserted at that position in the hashtable
	hashTable[position].isActive = true;
	count++;

	return true;
}

/*
	Best Case: theta(1)
	Worst Case: theta(capacity)
	Average Case: O(capacity)

*/

bool SortedSet::remove(TComp e)
{
	int position = hashFunction(e);
	int stepSize = stepFunction(e);

	while (hashTable[position].isActive|| hashTable[position].isDeleted)
	{
		if (hashTable[position].info == e) //check if the current slot contains the searched element 
		{
			hashTable[position].info = NULL_TELEM; //delete the element
			hashTable[position].isActive = false;
			hashTable[position].isDeleted = true;
			count--;
			return true;
		}

		position = (position + stepSize) % capacity;
	}

	return false;
}
/*
	Best Case: theta(1)
	Worst Case: theta(capacity)
	Average Case: O(capacity)
*/



bool SortedSet::search(TElem elem) const
{
	int position = hashFunction(elem);
	int stepSize = stepFunction(elem);

	while (hashTable[position].isActive) 
	{
		if (hashTable[position].info == elem)
			return true;

		position = (position + stepSize) % capacity;
	}

	return false;
}

/*
	Best Case: theta(1)
	Worst Case: theta(capacity)
	Average Case: O(capacity)

*/


int SortedSet::size() const
{
	return count;
}

/*
	Best Case: theta(1)
	Worst Case: theta(1)
	Average Case: theta(1)
*/
bool SortedSet::isEmpty() const
{
	return count == 0;
}
/*
	Best Case: theta(1)
	Worst Case: theta(1)
	Average Case: theta(1)
*/

SortedSetIterator SortedSet::iterator() const
{
	return SortedSetIterator(*this);
}

/*
	Best Case: theta(1)
	Worst Case: theta(capacity)
	Average Case: O(capacity)
*/

SortedSet::~SortedSet() {
	//TODO - Implementation
	delete[] hashTable;
}
/*
	Best Case: theta(1)
	Worst Case: theta(1)
	Average Case: theta(1)
*/

