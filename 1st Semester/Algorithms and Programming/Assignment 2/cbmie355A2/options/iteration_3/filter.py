#FEATURE 5
"""
5. Filter values
• 𝑓𝑖𝑙𝑡𝑒𝑟_𝑚𝑢𝑙(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑣𝑎𝑙𝑢𝑒) – keep only participants with scores multiple of 𝑣𝑎𝑙𝑢𝑒,
                           removing the other participants (scores)
• 𝑓𝑖𝑙𝑡𝑒𝑟_𝑔𝑟𝑒𝑎𝑡𝑒𝑟(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑣𝑎𝑙𝑢𝑒) – keep only participants with scores higher than 𝑣𝑎𝑙𝑢𝑒,
                              removing the other participants (scores)
"""
from options.iteration_1.modify import remove_index

def filter_mul(score_list,value):
    """
    This function removes the scores of the participants that are not multiple of value, keeping olny the participants with scores that are multiples of value.
    :param score_list: a list
    :param value: an int value
    :return: the filtered list containing only the scores of the participants that are a multiple of value
    """
    for i in range(len(score_list)-1,-1,-1):
        if score_list[i]%value!=0:
            remove_index(score_list,i)
    return score_list

def filter_greater(score_list,value):
    """
    This function removes the scores of participants that are smaller or equal to a given value, keeping only the participants with scores higher tha value.
    :param score_list: a list
    :param value: an int value
    :return: the filtered list containing the score of the participants that are higher than a specified value
    """
    for i in range(len(score_list)-1,-1,-1):
        if score_list[i]<=value:
            remove_index(score_list,i)
    return score_list
