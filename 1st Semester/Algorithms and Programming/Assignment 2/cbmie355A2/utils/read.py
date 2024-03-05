from tests.check_score_index import check_score, check_index

def get_valid_score():
    """
    This function reads values from the user until a valid score is introduced
    :return: valid score
    """
    while True:
        try:
            score=int(input("Please enter a score:"))
            if check_score(score):
                break
            else:
                raise ValueError
        except ValueError:
            print("Invalid score.")
    return score

def get_valid_index(lst, option):
    """
    This function reads values from the user until a valid index is introduced
    :param option: option
    :param lst: scores list
    :return: valid index
    """
    while True:
        try:
            index = int(input("Insert the index: "))
            if option == "2":
                if 0 <= index <= len(lst):
                    break
                else:
                    raise ValueError
            if check_index(index, lst):
                break
            else:
                raise ValueError
        except ValueError:
            print("Invalid index.")
    return index

def get_valid_from_to_indexes(lst):
    """
    Reads from the user until it inputs two valid indexes
    :param lst: Scores list
    :return: Valid indexes
    """
    both_valid = False
    from_index = 0
    to_index = 0
    while not both_valid:
        while True:
            try:
                from_index = int(input("Insert the first index: "))
                if check_index(from_index, lst):
                    break
                else:
                    raise ValueError
            except ValueError:
                print("This is not a valid index.")
        while True:
            try:
                to_index = int(input("Insert the last index: "))
                if check_index(to_index, lst):
                    break
                else:
                    raise ValueError
            except ValueError:
                print("This is not a valid index.")
        if from_index < to_index:
            both_valid = True
        else:
            print("Last index needs to be greater or equal to the first index.")
    return from_index, to_index

def get_valid_value():
    """
    This function reads values from the user until a valid value is introduced
    :return: valid score
    """
    while True:
        try:
            value = int(input("Insert the value: "))
        except ValueError:
            print("Invalid value.")
        else:
            break
    return value