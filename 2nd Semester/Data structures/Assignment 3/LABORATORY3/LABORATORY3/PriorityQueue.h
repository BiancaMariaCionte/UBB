#pragma once
#include <vector>
#include <utility>

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TElem;
typedef int TPriority;
typedef std::pair<TElem, TPriority> Element;
//each element has a priority value associated with it
//element-priority pairs are ordered based on a relation between the priorities
typedef bool (*Relation)(TPriority p1, TPriority p2); //the order relation is passed to the priority queue as a function pointer 
#define NULL_TELEM pair<TElem, TPriority> (-11111,-11111);

class PriorityQueue {
private:
	
	int cp;

	Element* e; //array of the elements
	int* nextLink;
	int first;
	int firstEmpty;

	Relation relation;

	int allocate();
	void deallocate(int i);
	int createNode(TElem e, TPriority p);
public:
	//implicit constructor
	PriorityQueue(Relation r);

	//pushes an element with priority to the queue

	void push(TElem e, TPriority p);

	//returns the element with the highest priority with respect to the order relation
	//throws exception if the queue is empty
	Element top()  const;

	//removes and returns the element with the highest priority
	//throws exception if the queue is empty
	Element pop();

	//checks if the queue is empty
	bool isEmpty() const;

	//destructor
	~PriorityQueue();

};

/*
PRIORITY QUEUE :
- the first element in the queue is either the gretest or the smallest of all in the queue
- elements are in non-increasing or non-decreasing order (each element has a priority {fixed order})

*/
