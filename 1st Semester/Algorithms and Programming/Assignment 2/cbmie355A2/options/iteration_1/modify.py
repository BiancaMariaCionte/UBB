#FEATURE 2

"""
2. Modify the scores in the array (as a result of appeals)
• 𝑟𝑒𝑚𝑜𝑣𝑒(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑖𝑛𝑑𝑒𝑥) – removes the element at 𝑖𝑛𝑑𝑒𝑥
• 𝑟𝑒𝑚𝑜𝑣𝑒(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑓𝑟𝑜𝑚_𝑖𝑛𝑑𝑒𝑥,𝑡𝑜_𝑖𝑛𝑑𝑒𝑥) – removes elements between the two given index
  e.g. 𝑟𝑒𝑚𝑜𝑣𝑒(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,1,3) – removes the elements at indices 1, 2 and 3
• 𝑟𝑒𝑝𝑙𝑎𝑐𝑒(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑖𝑛𝑑𝑒𝑥,𝑛𝑒𝑤_𝑣𝑎𝑙𝑢𝑒) – replaces the score on 𝑖𝑛𝑑𝑒𝑥 with 𝑛𝑒𝑤_𝑣𝑎𝑙𝑢𝑒
"""

def remove_index(score_list,index):
    """"
    This function removes an element from the list at the specified index.
    :param score_list: the list
    :param index: the index of the element which is going to be removed
    :return: scores list
    """
    if 0<= index <len(score_list):
        del score_list[index]
    return score_list

def remove_from_to_index(score_list,from_index,to_index):
    """
    This function removes the elements that are between two given indexes.
    :param score_list: a list
    :param from_index: the index from which we want to start removing elements
    :param to_index: the index at which we want to stop removing elements
    :return: the modified scores list
    """
    if from_index <= to_index:
        if 0<= from_index <len(score_list) and 0<= to_index <len(score_list):
            for i in range(to_index,from_index-1,-1):
                del score_list[i]
        return score_list
    else:
        print("Invalid indexes, the list is unmodified")

def replace(score_list,index,new_value):
    """
    This function replaces the score on a specified index with a new value.
    :param score_list: a list
    :param index: the index of the score that we want to replace
    :param new_value: the new value with which we want to replace the old value of the score
    :return: the modified scores list
    """
    if 0<= index <len(score_list):
        score_list[index]=new_value
    return score_list


