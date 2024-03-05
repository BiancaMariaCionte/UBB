# FEATURE 4

"""
4. Obtain different characteristics of participants
• 𝑎𝑣𝑔(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑓𝑟𝑜𝑚_𝑖𝑛𝑑𝑒𝑥,𝑡𝑜_𝑖𝑛𝑑𝑒𝑥) – get the average score for participants between the two given index
  e.g. 𝑎𝑣𝑔(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,1,5) – get the average score for participants 1..5
• 𝑚𝑖𝑛(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑓𝑟𝑜𝑚_𝑖𝑛𝑑𝑒𝑥,𝑡𝑜_𝑖𝑛𝑑𝑒𝑥) – get minimum score for participants between the two given index
  e.g. 𝑚𝑖𝑛(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,1,5)– get the minimum score for participants 1..5
• 𝑚𝑢𝑙(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,𝑣𝑎𝑙𝑢𝑒,𝑓𝑟𝑜𝑚_𝑖𝑛𝑑𝑒𝑥,𝑡𝑜_𝑖𝑛𝑑𝑒𝑥) – get the score of participants between the
  two given index, which are multiples of 𝑣𝑎𝑙𝑢𝑒
  e.g. 𝑚𝑢𝑙(𝑠𝑐𝑜𝑟𝑒_𝑙𝑖𝑠𝑡,10,1,5)– get the score of participants 1..5, which are multiples of 10
"""


def avg(score_list,from_index,to_index):
    """
    This function computes the average score between two given indexes
    :param score_list: A list
    :param from_index:
    :param to_index:
    :return: Average score
    """

    scores_sum=sum(score_list[from_index:to_index+1])
    scores_length=len(score_list[from_index:to_index+1])
    average=scores_sum/scores_length
    return average


def minimum(score_list,from_index,to_index):
    """
    This function returns the minimum score for participants between the two given indexes
    :param score_list: A list
    :param from_index: Index from where the function search for the minimum
    :param to_index: Index where the function stops to search for the minimum
    :return: Minimum score between the two indexes
    """
    mn=score_list[from_index]
    for i in range(from_index,to_index+1):
        if score_list[i]<mn:
            mn=score_list[i]
    return mn


def mul(score_list,value,from_index,to_index):
    """
    This function returns the scores of participants between the two given index, which are multiples of a value
    :param score_list: A list
    :param value: Value
    :param from_index: Beginning index
    :param to_index: Ending index
    :return: List with the scores of participants between the two given index, which are multiples of "value"
    """
    val_list=[]
    for i in range(from_index,to_index+1):
        if score_list[i]%value==0:
            val_list.append(score_list[i])
    return val_list
