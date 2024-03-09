#include <stdio.h>
#include <limits.h>
#include "tests.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>


// The strchr function is used to locate a character in a string. 
// More specifically, it returns a pointer to the first occurrence of the character in the string and NULL otherwise. 
// Write a function my_strchr, with the same parametersand return value as strchr.
const char* my_strchr(const char* s, char character)
{
    // TODO your code here
    for (int i = 0; *(s+i) != '\0'; i++)  //sintaxa de parcurgere a unui sir de pointeri
        if (*(s + i) == character)
            return s + i;
    return NULL;
}

int main()
{
#if ENABLE_TESTS > 0
    run_tests(true);
#endif
    _CrtSetReportMode(_CRT_WARN, _CRTDBG_MODE_DEBUG);
    _CrtDumpMemoryLeaks();
    return 0;
}
