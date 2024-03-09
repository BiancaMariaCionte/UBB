#include "BigInteger.h"

#include <cctype> // for isdigit
#include <string.h>
#include <stdio.h>
#include <iostream>
#include <iomanip>

BigInteger::BigInteger() //constructor
{
	this->digits = nullptr; // pointer that does not point to any memory location
	//digits is an array of digits
	this->num_digits = 0;
	this->sign = 0;
}

BigInteger::BigInteger(std::string s) //constructor for the sign
{
	this->num_digits = s.size(); //the size of the array of digits

	switch (s[0])
	{
	case '0':
		this->digits = new int[num_digits](); //new in c++ is malloc in c
		this->sign = 0;
		break;
	case '-':
		this->digits = new int[num_digits - 1]();
		s.erase(0, 1);
		this->num_digits--;
		this->sign = -1;
		break;
	case'+':
		this->digits = new int[num_digits - 1]();
		s.erase(0, 1);
		this->num_digits--;
		this->sign = 1;
		break;
	default:
		this->digits = new int[num_digits]();
		this->sign = 1;
		break;
	}

	int t = 0;

	for (int i = 0; i < this->num_digits; i++)
	{
		if (!isdigit(s[i])) throw("error");  //check if the digist are right
		*(this->digits + t) = (int)(s[i] - '0'); //we copy in the memory location the string which is converted into an integer
		t++; //we go through the entire memory location
	}
}

BigInteger::BigInteger(const BigInteger& N) //constructor for the digits
{
	this->digits = new int[N.num_digits] { 0 }; //{0}- we allocate memory of dimention N which is empty
	this->num_digits = N.num_digits;
	this->sign = N.sign;

	for (int i = 0; i < N.num_digits; i++)
		this->digits[i] = N.digits[i];  //we put all the digits in another array N
}

BigInteger::~BigInteger()
{
	delete[] digits;
}

void BigInteger::display()
{
	for (int i = 0; i < this->num_digits; i++)
		std::cout << this->digits[i] << " ";
}


BigInteger& BigInteger::operator=(const BigInteger& N) //if the two memory addresses are the same then we allocate another memory address for the new variable(this)
{
	if (this == &N)
		return *this;

	delete[] this->digits;

	this->digits = new int[N.num_digits] { 0 };
	this->num_digits = N.num_digits;
	this->sign = N.sign;

	for (int i = 0; i < N.num_digits; i++)
		this->digits[i] = N.digits[i];

	return *this;
}

int BigInteger::sgn() const
{
	return this->sign;
}

void BigInteger::negate()
{
	this->sign *= -1;
}

int BigInteger::compare(const BigInteger& N) const
{
	if (this->sign < N.sign)
		return -1;

	else if (this->sign > N.sign)
		return 1;
	if (this->num_digits < N.num_digits)
		return -1;
	else if (this->num_digits > N.num_digits)
		return 1;
	else
	{
		for (int i = 0; i < this->num_digits; i++)
		{
			if (this->digits[i] < N.digits[i])
				return -1;
			else if (this->digits[i] > N.digits[i])
				return 1;
		}
		return 0;
	}
}

bool operator==(const BigInteger& A, const BigInteger& B)
{
	if (A.compare(B) == 0)
		return true;
	return false;
}

bool operator<(const BigInteger& A, const BigInteger& B)
{
	if (A.compare(B) == -1)
		return true;
	return false;
}

bool operator<=(const BigInteger& A, const BigInteger& B)
{
	if (A.compare(B) == 0 || A.compare(B) == -1)
		return true;
	return false;
}

bool operator>(const BigInteger& A, const BigInteger& B)
{
	if (A.compare(B) == 1)
		return true;
	return false;
}

bool operator>=(const BigInteger& A, const BigInteger& B)
{
	if (A.compare(B) == 0 || A.compare(B) == 1)
		return true;
	return false;
}


std::ostream& operator<<(std::ostream& out, BigInteger N)
{
	out << N.to_string();
	return out;
}


std::string BigInteger::to_string()
{
	std::string result;
	if (this->sign == -1)
		result += "-";
	for (int i = 0; i < num_digits; i++)
		result += std::to_string(this->digits[i]);
	return result;
}
