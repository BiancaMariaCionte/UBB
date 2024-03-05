def print_menu():
    """
    This function displays the menu.
    :return: the menu
    """
    msg="Menu:\n"
    msg+="\n \t 0 - View list.\n"

    #FEATURE 1 - Add
    msg+="\n \t FEATURE 1\n"
    msg+="\t 1 - Append an element to the list.\n"
    msg+="\t 2 - Insert an element by index.\n"

    #FEATURE 2 - Modify
    msg+="\n \t FEATURE 2\n"
    msg+="\t 3 - Remove element by index.\n"
    msg+="\t 4 - Remove elements between two indexes.\n"
    msg+="\t 5 - Replace the element on index with a new value.\n"

    #FEATURE 3 - Get
    msg+="\n \t FEATURE 3\n"
    msg+="\t 6 - Get participants with score less than a value.\n "
    msg+="\t 7 - Get all participants sorted by their score.\n"
    msg+="\t 8 - Get the participants with scores higher than a value, sorted.\n"

    #FEATURE 4 - Obtain
    msg+="\n \t FEATURE 4\n"
    msg+="\t 9 - Get the average score for participants between two given index.\n"
    msg+="\t 10 - Get minimum score for participants between two given index.\n"
    msg+="\t 11 - Get the scores of participants between two given index, which are multiples of a value.\n"

    #FEATURE 5 - Filter
    msg+="\n \t FEATURE 5\n"
    msg+="\t 12 - Keep only participants with scores multiple of 𝑣𝑎𝑙𝑢𝑒, removing the other participants.\n"
    msg+="\t 13 - Keep only participants with scores greater than 𝑣𝑎𝑙𝑢𝑒, removing the other participants.\n"

    #FEATURE 6 - Undo
    msg+="\n \t FEATURE 6\n"
    msg+="\t u - Undo the last operation that modified the list.\n"

    #Test iterations
    msg+="\n \t ITERATIONS TEST\n"
    msg+="\t t1 - Test iteration 1.\n"
    msg+="\t t2 - Test iteration 2.\n"
    msg+="\t t3 - Test iteration 3.\n"

    #EXIT
    msg+="\n \t EXIT\n"
    msg+="\t e - Exit.\n"

    print(msg)