#include <math.h>
#include <stdio.h>
#include <complex.h>

#include "complex_number.h"

Complex complex_create(float real, float imag)
{
	struct Complex c;
	c.real = real;
	c.imag = imag;
	return c;
}
float get_real(Complex c)
{
	return c.real;
}

float get_imag(Complex c)
{
	return c.imag;
}

void set_real(Complex* c, float real)
{
	c->real = real;
}

void set_imag(Complex* c, float imag)
{
	c->imag = imag;
}

Complex complex_conjugate(Complex c)
{
	c.imag = c.imag * (-1);
	return c;
}

Complex complex_add(Complex c1, Complex c2)
{
	Complex c;
	c.real = c1.real + c2.real;
	c.imag = c1.imag + c2.imag;
	return c;
}
Complex complex_subtract(Complex c1, Complex c2)
{
	Complex c;
	c.real = c1.real - c2.real;
	c.imag = c1.imag - c2.imag;
	return c;
}

Complex complex_multiply(Complex c1, Complex c2)
{
	Complex c;
	c.real = c1.real * c2.real - (c1.imag * c2.imag);
	c.imag = c1.real * c2.imag + (c2.real * c1.imag);
	return c;
}

void complex_scalar_mult(Complex* c, float s)
{
	c->real *= s;
	c->imag *= s;
}

bool complex_equals(Complex c1, Complex c2)
{
	if ((c1.real == c2.real) && (c1.imag == c2.imag))
		return true;
	else
		return false;
}

float complex_mag(Complex c)
{
	float x;
	x = sqrt(c.real * c.real + c.imag * c.imag);
	return x;
}

float complex_phase(Complex c)
{
	float p;
	p = atan2(c.imag, c.real);
	return p;
}
void complex_to_polar(Complex c, float* r, float* theta)
{
	*r = complex_mag(c);
	*theta = complex_phase(c);
}

void complex_display(Complex c)
{
	printf("c=%f+%f*i", c.real, c.imag);
}