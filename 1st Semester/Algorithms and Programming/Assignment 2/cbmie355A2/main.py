
from options.iteration_1.add import add_to_the_end, insert
from options.iteration_1.modify import remove_index, remove_from_to_index, replace
from options.iteration_2.get import less, sorted_list, sorted_higher
from options.iteration_2.obtain import avg, minimum, mul
from options.iteration_3.filter import filter_mul, filter_greater
from options.iteration_3.undo import undo
from tests.iterations_tests import test_iteration_1, test_iteration_2, test_iteration_3
from ui.menu import print_menu
from utils.copy import copy_list
from utils.print import print_list
from utils.read import get_valid_score, get_valid_index, get_valid_value, get_valid_from_to_indexes

history_list = []

def start():
    score_list=[12,27,79,33,90,98,100,0,71,62,53]
    history_list.append(score_list.copy())
    stop=False
    menu_print=False
    while stop==False:
        if menu_print==False:
            print_menu()
            menu_print=True
        else:
            print("\n Type 'm' to see the menu")
        option=input("Enter an option:")

        if option=='m':
            print_menu()
        elif option=='0':
            print_list(score_list,0)
             
        #FEATURE 1
        elif option=='1':
             score=get_valid_score()
             add_to_the_end(score_list,score)
             print_list(score_list,0)
             copy_list(score_list,history_list)

        elif option=='2':
            index=get_valid_index(score_list,option)
            score=get_valid_score()
            insert(score_list,index,score)
            print_list(score_list,0)
            copy_list(score_list,history_list)
            
        #FEATURE 2
        elif option=='3':
            if len(score_list)>0:
                index=get_valid_index(score_list,option)
                remove_index(score_list,index)
            print_list(score_list,1)
            copy_list(score_list,history_list)
        
        elif option=='4':
            if len(score_list)==0:
                print_list(score_list,1)
            else:
                from_index,to_index=get_valid_from_to_indexes(score_list)
                remove_from_to_index(score_list,from_index,to_index)
                print_list(score_list,0)
            copy_list(score_list,history_list)
            
        elif option=='5':
            if len(score_list)>0:
                index=get_valid_index(score_list,option)
                new_value=get_valid_score()
                replace(score_list,index,new_value)
            print_list(score_list,1)
            copy_list(score_list,history_list)

            # FEATURE 3
        elif option == "6":  # less(score_list, value)
            if len(score_list) > 0:
                value = get_valid_value()
                less_list = less(score_list, value)
                print_list(less_list, 2)
            else:
                print_list(score_list, 1)

        elif option == "7":  # sorted_list(score_list)
            if len(score_list) > 0:
                sort_list = sorted_list(score_list)
                print_list(sort_list, 2)
            else:
                print_list(score_list, 1)

        elif option == "8":  # sorted_higher(score_list, value)
            if len(score_list) > 0:
                value = get_valid_value()
                higher_list = sorted_higher(score_list, value)
                print_list(higher_list, 2)
            else:
                print_list(score_list, 1)

        # FEATURE 4
        elif option == "9":  # avg(score_list, from_index, to_index)
            if len(score_list) > 0:
                from_index, to_index = get_valid_from_to_indexes(score_list)
                average = avg(score_list, from_index, to_index)
                print("The average between the two indexes is:", average)
            else:
                print_list(score_list, 1)

        elif option == "10":  # minimum(score_list, from_index, to_index)
            if len(score_list) > 0:
                from_index, to_index = get_valid_from_to_indexes(score_list)
                mn = minimum(score_list, from_index, to_index)
                print("The minimum between the two indexes is:", mn)
            else:
                print_list(score_list, 1)

        elif option == "11":  # mul(score_list, value, from_index, to_index)
            if len(score_list) > 0:
                from_index, to_index = get_valid_from_to_indexes(score_list)
                value = get_valid_value()
                mul_list = mul(score_list, value, from_index, to_index)
                print_list(mul_list, 2)
            else:
                print_list(score_list, 1)

        # FEATURE 5
        elif option == "12":  # filter_mul(score_list, value)
            if len(score_list) > 0:
                value = get_valid_value()
                filter_mul(score_list, value)
                print_list(score_list, 0)
            else:
                print(score_list, 1)
            copy_list(score_list, history_list)

        elif option == "13":  # filter_greater(score_list, value)
            if len(score_list) > 0:
                value = get_valid_value()
                filter_greater(score_list, value)
                print_list(score_list, 0)
            else:
                print(score_list, 1)
            copy_list(score_list, history_list)

        # FEATURE 6
        elif option == "u":  # undo(history_list)
            score_list = undo(history_list)
            print_list(score_list, 0)

        # TEST ITERATIONS
        elif option == "t1":
            print()
            test_iteration_1()
        elif option == "t2":
            print()
            test_iteration_2()
        elif option == "t3":
            print()
            test_iteration_3()

            # Exit
        elif option == "e":
            print("\nExit", end="")
            import time
            for i in range(0, 3):
                time.sleep(0.5)
                print(".", end="")
            time.sleep(0.5)
            stop = True

        # Invalid option
        else:
            print("Option does not exist.")
            
start()




