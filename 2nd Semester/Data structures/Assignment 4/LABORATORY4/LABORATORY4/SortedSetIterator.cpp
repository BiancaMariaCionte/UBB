#include "SortedSetIterator.h"
#include <exception>
#include<algorithm>

using namespace std;

SortedSetIterator::SortedSetIterator(const SortedSet& m) : multime(m)
{
    // create a sorted array of the elements
    std::vector<TElem> sortedElements; // store the elements of the sorted set in an array
    for (int i = 0; i < m.capacity; i++) {
        if (m.hashTable[i].isActive) 
        {
            //the element from the hash table is added to the sortedElements vector
            sortedElements.push_back(m.hashTable[i].info);
        }
    }
    std::sort(sortedElements.begin(), sortedElements.end(), [=](int a, int b) {return this->multime.relation(a, b); }); //sort the elements in the relation order

    // create a copy of the sorted array for iteration
    sortedArray = std::move(sortedElements);
    current = 0; //initialise the current variable to point to the first element in the sorted array
}

/*
n - number of active elements in the SortedSet
log n - time complexity of sort
    Best Case: theta(n log n)
    Worst Case: theta(n log n)
    Average Case: theta(n log n)
*/

void SortedSetIterator::first() 
{
	//TODO - Implementation
    current = 0;
}

/*
    Best Case: theta(1)
    Worst Case: theta(1)
    Average Case: theta(1)
*/

void SortedSetIterator::next()
{
    //TODO - Implementation
    if (!valid())
        throw std::exception("Invalid iterator!");
    current++;
}
/*
    Best Case: theta(1)
    Worst Case: theta(1)
    Average Case: theta(1)
*/


TElem SortedSetIterator::getCurrent()
{
	//TODO - Implementation
    if (!valid()) {
        throw std::exception("Invalid iterator!");
    }

    return sortedArray[current];
}

/*
    Best Case: theta(1)
    Worst Case: theta(1)
    Average Case: theta(1)
*/

bool SortedSetIterator::valid() const
{
    return current < sortedArray.size();
}

/*
    Best Case: theta(1)
    Worst Case: theta(1)
    Average Case: theta(1)
*/

