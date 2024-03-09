#include <stdio.h>
#include <limits.h>
#include "tests.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>

// Write a function that takes as an input an array of integer numbers (both positive and negative numbers and returns the value of the triplet with the maximum product, as well as the elements that form this triplet (in increasing order). 
// Use pass by pointer/address to return the elements that form that triplet. 
// Think about the appropriate data type for the result. If the size of the array is less than 3, you should return the minimum
// representable value of the data type and the elements that form the triplet should be set to 0.
long long computeMaxTriplet(int arr[], unsigned int sz, int *t1, int* t2, int* t3)
{
    // TODO your code here
    if (sz < 3)
    {
        *t1 = *t2 = *t3 = 0;
        return LLONG_MIN;
    }
    int a = INT_MIN, b = INT_MIN, c = INT_MIN;
    int d = 0, e = 0;
    for (int i = 0; i < sz; i++)
    {
        if (arr[i] > a) //we check if the elements of the array are greater than the minimun
        {
            c = b;      //we sort the greatest 3 positive elements of the array
            b = a;
            a = arr[i];
        }
        else
            if (arr[i] > b)
            {
                c = b;
                b = arr[i];
            }
            else
                if (arr[i] > c)
                    c = arr[i];
        if (arr[i] < d)  
        {
            e = d;          //we sort the lowest 2 negative elements of the array
            d = arr[i];
        }
        else
            if (arr[i] < e)
                e = arr[i];
    }
    if (((long long)a) * d * e > ((long long) a) * b * c)
    {
        *t1=d;
        *t2=e;
        *t3=a;
        return ((long long)a)*d*e;
    }
    else
    {
        *t1 = c;
        *t2 = b;
        *t3 = a;
        return ((long long)a) * b * c;
    }
    return 0;
}

int main()
{
#if ENABLE_TESTS > 0
    run_tests(true);
#endif
    _CrtSetReportMode(_CRT_WARN, _CRTDBG_MODE_DEBUG);
    _CrtDumpMemoryLeaks();
    unsigned int n;
    int ok = scanf_s("%d", &n);
    if (ok)
    {
        int arr[250];
        int i = 0;
        while (i < n)
        {
            int ok2 = scanf_s(" %d", arr + i);  //arr is the location of the first element of the aray 
            if (!ok2)
                return 0;
            i++;
        }
        if (n < 3)
        {
            printf("The array has less than 3 elements.Application will now stop.");
            return 0;
        }
        int t1, t2, t3;
        long long prod = computeMaxTriplet(arr, n, &t1, &t2, &t3);
        printf("The maximum triplet is(%d, %d, %d) with a product of %lld.", t1, t2, t3, prod);
    }
    return 0;
}
