#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

#include "complex_test.h"
#include "complex_number.h"

#define AC_RED "\x1b[31m"
#define AC_BLUE "\x1b[34m"
#define AC_GREEN "\x1b[32m"


void display_mandelbrot(int width, int height, int max_its)
{

    const float x_start = -3.0f;
    const float x_fin = 1.0f;
    const float y_start = -1.0f;
    const float y_fin = 1.0f;

    double dx = (x_fin - x_start) / (width - 1);
    double dy = (y_fin - y_start) / (height - 1);

    for (int y = 0; y < height; ++y)
    {
        for (int x = 0; x < width; ++x)
        {
            // TODO your code here
            // create complex number z = 0 + 0i
            Complex z;
            z = complex_create(0, 0);
            // create complex number c =  x_start+ x*dx + (y_start+y*dy)i
            Complex c;
            c = complex_create(x_start + x * dx, y_start + y * dy);

            int iteration = 0;
            // while |z| < 2 and we haven't reach max_its
            while (complex_mag(z) < 2&& ++iteration < max_its) {
                // apply the rule:  z =  z*z + c
                z = complex_multiply(z, z);
                z = complex_add(z, c);
            }

            // TODO: your code here (modify the code to display the mandelbrot fractal
            if (iteration == max_its) {
                printf("%s*", AC_BLUE);
            }
            else {
                printf("%s-", AC_GREEN);
            }

        }
        printf("\n");
    }
}




int main(int argc, char** argv) {

	run_complex_tests(true);

	// TODO your code here
    Complex c;                      //stack managed by PC
    c = complex_create(1.0, 2.0);
    complex_display(c);
    printf("\nMagnitude: %f\n", complex_mag(c));
    printf("Phase: %f\n", complex_phase(c));
    printf("\n");

    float r, s;
    complex_to_polar(c, &r, &s);
    printf("Polar representation:\nc= %f(cos%f + isin%f)\n", r, s, s);
    printf("\n");

    Complex c1 = complex_conjugate(c);      //
    complex_display(c1);
    complex_scalar_mult(&c, 3.0);
    printf("\n");
    complex_display(c);
    printf("\n");

    Complex* c2 = (Complex*)malloc(sizeof(Complex));  //heap pointer spre adresa din memorie, managed by USER
    *c2 = complex_create(5, 7);                         //pointer spre valoarea din memorie
    complex_display(*c2);
    printf("\nMagnitude: %f\n", complex_mag(*c2));
    printf("Phase: %f\n", complex_phase(*c2));
    
    complex_to_polar(*c2, &r, &s);
    printf("Polar representation:\nc= %f(cos%f+ isin%f)\n", r, s, s);

    Complex c3 = complex_conjugate(*c2);
    complex_display(*c2);
    complex_scalar_mult(c2, 5.3);
    printf("\n");
    complex_display(*c2);
    free(c2);

    printf("\n");
    display_mandelbrot(100, 25, 100);
    getchar();

    return 0;
}

