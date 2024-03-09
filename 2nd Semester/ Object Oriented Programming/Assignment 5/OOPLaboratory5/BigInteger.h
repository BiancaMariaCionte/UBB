#pragma once

#include<string>
#include<iostream>


//class DynamicArray
//{
//public:
//	DynamicArray();
//	DynamicArray(const DynamicArray& other);
//
//	~DynamicArray();
//
//	DynamicArray& operator=(const DynamicArray& other);
//
//private:
//	int* arr;
//	int capacity;
//	int dimension;
//};

class BigInteger
{

public:
	BigInteger();  //default constructor
	BigInteger(std::string s); //takes as input a string (std::string) of characters in the interval[0, 9] and stores the digits in the heap allocated array

	BigInteger(const BigInteger& N); //copy constructor(copies the date from oane big int into another)
	~BigInteger();

	void display();

	BigInteger& operator=(const BigInteger& N); // assignment operator (it verifies is this and N are different and then does what the copy constructor does)

	int sgn()const;
	void negate();

	void normalize();
	BigInteger abs() const;

	BigInteger& operator++();
	BigInteger operator++(int t);  //overloading

	friend bool operator==(const BigInteger& A, const BigInteger& B); //used for accessing private elements from a class
	friend bool operator<(const BigInteger& A, const BigInteger& B);
	friend bool operator<=(const BigInteger& A, const BigInteger& B);
	friend bool operator>(const BigInteger& A, const BigInteger& B);
	friend bool operator>=(const BigInteger& A, const BigInteger& B);

	friend BigInteger operator+(const BigInteger& A, const BigInteger& B);
	BigInteger& operator+=(const BigInteger& N); //we use the new variable N and the memory address of the other element(this) and we add them

	friend BigInteger operator-(BigInteger& A, BigInteger& B);
	BigInteger& operator-=(const BigInteger& N);

	std::string to_string();
	friend std::ostream& operator<<(std::ostream& out, BigInteger N);

private:
	int* digits;
	int num_digits;
	int sign;

	BigInteger add(const BigInteger& N)const;
	BigInteger sub(const BigInteger& N)const;

	int compare(const BigInteger& N)const;

};
