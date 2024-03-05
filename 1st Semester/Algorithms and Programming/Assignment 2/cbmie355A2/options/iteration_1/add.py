#FEATURE 1
""""
1. Add the result of a new participant to the array
• 𝑎𝑑𝑑(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑣𝑎𝑙𝑢𝑒) – 𝑣𝑎𝑙𝑢𝑒 as last element of 𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡
• 𝑖𝑛𝑠𝑒𝑟𝑡(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑖𝑛𝑑𝑒𝑥,𝑣𝑎𝑙𝑢𝑒) – insert number 𝑣𝑎𝑙𝑢𝑒 at 𝑖𝑛𝑑𝑒𝑥 (the index of the first element is 0)
"""

def add_to_the_end(score_list,value):
    """
    This function appends a value at the end of the list
    :param score_list: a list
    :param value: the value which is going to be added at the end of the list
    :return: scores list
    """
    score_list.append(value)
    return score_list

def insert(score_list,index,value):
    """"
    This function inserts a value at a specified index in the list
    :param score_list: a list
    :param index: the index at which the value will be inserted
    :param value: the value which will be inserted in the list
    :return: scores list
    """
    score_list.insert(index,value)
    return score_list
    
    

