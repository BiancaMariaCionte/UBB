#pragma once
#include<string>
#include<iostream>


class BigInteger {

public:
	BigInteger(); //default constructor
	BigInteger(std::string s); //parameterized constructor
	//takes as input a string (std::string) of characters in the interval [0, 9] and stores the digits in the heap allocated array.

	BigInteger(const BigInteger& N);
	~BigInteger();

	void display();

	BigInteger& operator=(const BigInteger& N); //

	int sgn()const;
	void negate();


	friend bool operator==(const BigInteger& A, const BigInteger& B); //used for accessing private elements from a class
	friend bool operator<(const BigInteger& A, const BigInteger& B); //pentru a accesa elementele private dintr-o clasa
	friend bool operator<=(const BigInteger& A, const BigInteger& B);
	friend bool operator>(const BigInteger& A, const BigInteger& B);
	friend bool operator>=(const BigInteger& A, const BigInteger& B);


	std::string to_string();
	friend std::ostream& operator<<(std::ostream& out, BigInteger N);

private:
	int* digits;
	int num_digits;
	int sign;


	int compare(const BigInteger& N)const;

};

