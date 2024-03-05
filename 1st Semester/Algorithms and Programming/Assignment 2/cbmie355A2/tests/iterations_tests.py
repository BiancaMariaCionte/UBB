
from options.iteration_1.add import add_to_the_end, insert
from options.iteration_2.obtain import avg, minimum, mul
from options.iteration_3.filter import filter_mul, filter_greater
from options.iteration_2.get import less, sorted_list, sorted_higher
from options.iteration_1.modify import remove_index, remove_from_to_index, replace
from options.iteration_3.undo import undo
from utils.copy import copy_list
from utils.print import print_list


def test_iteration_1():

    score_list = [12,27,59,100,89]
    print_list(lst=score_list, option=0)

    print("\n1. Remove element of index 0.")
    remove_index(score_list=score_list, index=0)
    print_list(lst=score_list, option=0)

    print("\n2. Add value 44 at the end of the list.")
    add_to_the_end(score_list=score_list, value=44)
    print_list(lst=score_list, option=0)

    print("\n3. Insert value 9 on the position with index 1.")
    insert(score_list=score_list, index=1, value=9)
    print_list(lst=score_list, option=0)

    print("\n4. Remove element of index 0.")
    remove_index(score_list=score_list, index=0)
    print_list(lst=score_list, option=0)

    print("\n5. Insert value 98 at the end of the list.")
    insert(score_list=score_list, index=len(score_list), value=98)
    print_list(lst=score_list, option=0)

    print("\n6. Remove elements from index 1 to index 4.")
    remove_from_to_index(score_list=score_list, from_index=1, to_index=4)
    print_list(lst=score_list, option=0)

    print("\n7. Insert value 67 on the position with index 3.")
    insert(score_list=score_list, index=3, value=67)
    print_list(lst=score_list, option=0)

    print("\n8. Replace element of index 3 with value 100.")
    replace(score_list=score_list, index=3, new_value=100)
    print_list(lst=score_list, option=0)

    print("\n9. Replace element of index 0 with value 67.")
    replace(score_list=score_list, index=0, new_value=67)
    print_list(lst=score_list, option=1)

    print("\n10. Insert value 13 at the end of the list.")
    insert(score_list=score_list, index=len(score_list), value=13)
    print_list(lst=score_list, option=0)


def test_iteration_2():

    score_list = [49, 91, 75, 29, 99, 0, 5, 32, 97, 24]
    print_list(lst=score_list, option=0)

    print("\n1. Get participants with score less than 60.")
    returned_list = less(score_list=score_list, value=60)
    print_list(lst=returned_list, option=2)

    print("\n2. Get the average score for participants between 1 and 4.")
    average = avg(score_list=score_list, from_index=1, to_index=4)
    print("The average is: ", average)

    print("\n3. Get the minimum score for participants between 0 and 8.")
    mn = minimum(score_list=score_list, from_index=0, to_index=8)
    print("The minimum is: ", mn)

    print("\n4. Get all participants sorted by their score.")
    returned_list = sorted_list(score_list=score_list)
    print_list(lst=returned_list, option=2)

    print("\n5. Get the participants with scores higher than 34, sorted.")
    returned_list = sorted_higher(score_list=score_list, value=34)
    print_list(lst=returned_list, option=2)

    print("\n6. Get the score of participants between index 3 and index 9, which are multiples of 2.")
    returned_list = mul(score_list=score_list, value=2, from_index=3, to_index=9)
    print_list(lst=returned_list, option=2)

    print("\n7. Get all participants sorted by their score.")
    returned_list = sorted_list(score_list=score_list)
    print_list(lst=returned_list, option=2)

    print("\n8. Get the participants with scores higher than 25, sorted.")
    returned_list = sorted_higher(score_list=score_list, value=25)
    print_list(lst=returned_list, option=2)

    print("\n9. Get the score of participants between 0 and 3, which are multiples of 6.")
    returned_list = mul(score_list=score_list, value=6, from_index=0, to_index=3)
    print_list(lst=returned_list, option=2)

    print("\n10. Get the average score in the list.")
    average = avg(score_list=score_list, from_index=0, to_index=len(score_list)-1)
    print("The average is: ", average)


def test_iteration_3():

    score_list = [42, 91, 76, 29, 99, 0, 3, 32, 97]
    history_list = []
    history_list.append(score_list.copy())
    print_list(lst=score_list, option=0)

    print("\n1. Keep only participants with scores multiple of 3, removing the other participants.")
    filter_mul(score_list=score_list, value=3)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)

    print("\n2. Keep only participants with scores higher than 40, removing the other participants.")
    filter_greater(score_list=score_list, value=40)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)

    print("\n3. Undo last operation.")
    score_list = undo(history_list)
    print_list(lst=score_list, option=0)

    print("\n4. Undo last operation.")
    score_list = undo(history_list)
    print_list(lst=score_list, option=0)

    print("\n5. Keep only participants with scores multiple of 2, removing the other participants.")
    filter_mul(score_list=score_list, value=2)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)

    print("\n6. Undo last operation.")
    score_list = undo(history_list)
    print_list(lst=score_list, option=0)

    print("\n7. Keep only participants with scores higher than 5, removing the other participants.")
    filter_greater(score_list=score_list, value=4)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)

    print("\n8. Keep only participants with scores multiple of 3, removing the other participants.")
    filter_mul(score_list=score_list, value=3)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)

    print("\n9. Undo last operation.")
    score_list = undo(history_list)
    print_list(lst=score_list, option=0)

    print("\n10. Keep only participants with scores higher than 60, removing the other participants.")
    filter_greater(score_list=score_list, value=60)
    print_list(lst=score_list, option=0)
    copy_list(score_list, history_list)
