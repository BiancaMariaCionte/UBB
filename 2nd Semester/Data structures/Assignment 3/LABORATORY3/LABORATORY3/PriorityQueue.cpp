
#include "PriorityQueue.h"
#include <exception>
#include<iostream>

using namespace std;


PriorityQueue::PriorityQueue(Relation r) {
	cp = 180000;
	//allocate dynamic memory for arrays "e" and "nextLink" of size cp
	e = new Element[cp]; //array of Element containing an element-priority pair 
	nextLink = new int[cp]; //array of int, representing the index of the next element in the list

	for (int i = 0; i < cp - 1; i++) {
		nextLink[i] = i + 1; //every element in the array points to the next element in the list
	}
	nextLink[cp - 1] = -1; //last element is set to -1 bsc it's the end of the list

	first = -1;
	firstEmpty = 0;
	this->relation = r;
}

/*
Best case: theta(1) 
Worst case: theta(1)
Average case: theta(1)
*/

int PriorityQueue::allocate() {
	int posToAdd = firstEmpty;  // index of the first empty slot
	if (posToAdd != -1) //if there is an empty slot available
	{
		firstEmpty = nextLink[firstEmpty]; //update the value of firstEmpty
		return posToAdd;// index of the allocated slot
	}
	else {
		return -1; //  no empty slots
	}
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/

void PriorityQueue::deallocate(int i) {
	//we add the slot at index i at the beginning of the list of empty spaces
	nextLink[i] = firstEmpty;
	firstEmpty = i;
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/

int PriorityQueue::createNode(TElem e, TPriority p) {

	int posToAdd = allocate();
	if (posToAdd != -1)
	{
		this->e[posToAdd] = std::make_pair(e, p);
		nextLink[posToAdd] = -1;
	}
	return posToAdd;
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/


void PriorityQueue::push(TElem e, TPriority p) {
	int posToAdd = createNode(e, p); //the index of the newNode in the e array is saved to posToAdd
	if (isEmpty()) {
		
		first = posToAdd; //new node becomes the head of the queue
	}
	else if (relation(p, this->e[first].second)) //priority(newNode) >= priority(head)

	{
		nextLink[posToAdd] = first; //the new node becomes the new head of the queue
		first = posToAdd; //updade the head
	}
	else 
	{ //we search for the correct position to insert the node into the queue
		int current = first; //set current to the index of the head
		int previous = first;
		while (current != -1 && relation(this->e[current].second, p))//current>p
		{
			previous = current;
			current = nextLink[current];//index of the next node
		}
		//when we find the correct position
		this->nextLink[posToAdd] = this->nextLink[previous]; //we update the next pointer of the new node to point to the next node in the queue
		this->nextLink[previous] = posToAdd; //we update the next pointer of the previous node to point to the new node
	}
}

/*
Best case: theta(1) ( inserting at the beginning)
Worst case: theta(n) ( inserting at the end)
Average case: O(n)

*/



Element PriorityQueue::top() const
{
	if (isEmpty())
	{
		throw exception("Queue is empty!");
	}
	return e[first];
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/

Element PriorityQueue::pop() {
	if (isEmpty())
		throw exception("Queue is empty!");
	else
	{

		Element elemToReturn = e[first]; // assign the element with the highest priority to elemToReturn
		int oldFirst = first; //store the index of the old first element of the queue in the oldFirst
		first = nextLink[first]; //update the first index to point to the next element in the queue (removes oldFirst)
		deallocate(oldFirst);//free the memory

		return elemToReturn;
	}
	
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/

bool PriorityQueue::isEmpty() const {
	//TODO - Implementation
	if (first == -1)
		return true;
	return false;
}

/*
Best case: theta(1)
Worst case: theta(1)
Average case: theta(1)
*/


PriorityQueue::~PriorityQueue() {
	//TODO - Implementation
	delete[] e;
	delete[] nextLink;
};

/*
Best case: theta(1) (queue is already empty)
Worst case: theta(n) (queue is full)
Average case: O(n)
*/
