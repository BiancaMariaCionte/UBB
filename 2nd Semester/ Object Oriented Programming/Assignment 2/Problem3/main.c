#include <stdio.h>
#include <limits.h>
#include "tests.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>


const char* my_strchr(const char* s, char character)
{
    // TODO your code here
    for (int i = 0; *(s + i) != '\0'; i++)
        if (*(s + i) == character)
            return s + i;
    return NULL;
}

// Using the function that you wrote for problem 2, write a function that computesand returns an array with all the positions of the occurrences of a character in a string.
void find_all( const char * str, char character, int*  positions, unsigned int cap, unsigned int * l)
{
    for (int i = 0; i < cap; i++)
        positions[i] = -1;
    *l = 0;
    char* p = my_strchr(str, character);
    while (p && *l < cap)
    {
        positions[(*l)++] = (p - str);  // p = str+i hence in positions we put i which is the adress of each char
        p = my_strchr(p + 1, character);
    }

 }

int main()
{
#if  ENABLE_TESTS > 0
    run_tests(true);
#endif 
    _CrtSetReportMode(_CRT_WARN, _CRTDBG_MODE_DEBUG);
    _CrtDumpMemoryLeaks();
    return 0;
}
