#define _CRT_SECURE_NO_WARNINGS
#include <math.h>
#include <stdio.h>
#include <limits.h>
#define _CRTDBG_MAP_ALLOC
#include "tests.h"
#include <crtdbg.h>


void generateAllCombinations(int** numbers, char** operators)
{
	int sol = -1;
	char   ops[4] = { '+', '-', '*','/' };
	for (int i = 1; i <= 9; i++)
		for (int j = 1; j <= 9; j++)
			for (int k = 1; k <= 9; k++)
				for (int l = 1; l <= 9; l++)
				{
					for (int op1 = 0; op1 < 4; op1++)
					{
						double rez1;
						if (ops[op1] == '+')
							rez1 = (double)i + j;
						else if (ops[op1] == '-')
							rez1 = (double)i - j;
						else if (ops[op1] == '*')
							rez1 = (double)i * j;
						else if (ops[op1] == '/')
							rez1 = (double)i / j;
						for (int op2 = 0; op2 < 4; op2++)
						{
							double rez2;
							if (ops[op2] == '+')
								rez2 = rez1 + k;
							else if (ops[op2] == '-')
								rez2 = rez1 - k;
							else if (ops[op2] == '*')
								rez2 = rez1 * k;
							else if (ops[op2] == '/')
								rez2 = rez1 / k;
							for (int op3 = 0; op3 < 4; op3++)
							{
								double rez3;
								if (ops[op3] == '+')
									rez3 = rez2 + l;
								else if (ops[op3] == '-')
									rez3 = rez2 - l;
								else if (ops[op3] == '*')
									rez3 = rez2 * l;
								else if (ops[op3] == '/')
									rez3 = rez2 / l;
								double res = 24;
								if (fabs(rez3 - 24) < 0.0001) 
								{
									sol++;
									numbers[sol][0] = (int)i;  //it generates a matrix of solutions
									numbers[sol][1] = (int)j;
									numbers[sol][2] = (int)k;
									numbers[sol][3] = (int)l;
									operators[sol][0] = (char)ops[op1];
									operators[sol][1] = (char)ops[op2];
									operators[sol][2] = (char)ops[op3];
									double r = fabs(rez3 - 24);
								}
							}
						}
					}
				}
}

int main()
{
#if ENABLE_TESTS > 0
    run_tests(true);
#endif

    bool ok = true;
    char var;
    char op1, op2, op3;

    // dynamically allocated memory for the matrix of numbers
    int** numbers = malloc(3188 * sizeof(int*));
    for (int i = 0; i < 3188; i++)
        numbers[i] = malloc(4 * sizeof(int));

    char** operators = malloc(3188 * sizeof(char*));
    for (int i = 0; i < 3188; i++)
        operators[i] = malloc(4 * sizeof(char));

    generateAllCombinations(numbers, operators);

    while (ok)
    {
        printf("Welcome to the game of 24.\n");
        printf("Use each of the 4 numbers printed below exectly once,\n");
        printf("combining them somehow with the basic mathematical opperations\n");
        printf("(+,-,*,/) to yeald the value 24.\n\n");
        printf("The numbers are: ");

        int r = rand() % 3188;
        printf("%d, %d, %d, %d.\n", numbers[r][0], numbers[r][1], numbers[r][2], numbers[r][3]);

        printf("Enter the 3 operations to be used, in order (+,-,*,/): ");
        
        int t = scanf(" %c,%c,%c ", &op1, &op2, &op3);

        if (((int)op1 > 47 || (int)op1 < 42) || ((int)op2 > 47 || (int)op2 < 42)
            || ((int)op3 > 47 || (int)op3 < 42))
        {
            printf("Error: You typed an invalid operator. Please retry!");
        }

           
        int t1 = scanf("%c", &var);
        switch (var)
        {
        case 'h':
            printf("RULES:\n");
            printf("The 24 puzzle is a math game and 4 digits are given to the player\n");
            printf("and he / she must figure out a way of combining these digits \n");
            printf("and the addition(+), subtraction(-), multiplication(*), and\n");
            printf("division(/ ) operators such that the result is 24.\n");
            printf("\n MENU: \n");
            printf("-h: display information about how the game is played and exits.\n");
            printf("-s: save all the possible solutions for the game in a text file called 'solution.txt' and then exits.\n");
            printf("-e: play the game in easy mode.\n");
            printf("If no command line arguments are specified, the game is played in the 'full'' mode\n");
            break;
        case 's':
            printf("You exited the game.\n");
            ok = false;
            break;
        case 'e':
            printf("You entered easy mode.\n");
            break;
        default:
            printf("You need to introduce a valid comand.\n");
            break;
        }
    }

    //free memory
    for (int i = 0; i < 3188; i++)
    {
        free(numbers[i]);
        free(operators[i]);
    }
    free(numbers);
    free(operators);
    return 0;

}

