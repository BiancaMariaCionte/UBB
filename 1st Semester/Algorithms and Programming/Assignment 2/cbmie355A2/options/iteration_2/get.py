#FEATURE 3

def less(score_list,value):
    """
    This function returns the participants with score less than a specified value.
    :param score_list: a list
    :param value: an int value with which we compare the score
    :return: a list with the participants whose scores are less than value
    """
    less_list=[]
    for i in range(len(score_list)):
        if score_list[i]<value:
            less_list.append(score_list[i])
    return less_list

def sorted_list(score_list):
    """
    This function returns the sorted list of scores
    :param score_list: a list
    :return: the sorted list
    """
    sort_list=[]
    copy_list=score_list.copy()
    for i in range(len(score_list)):
        minimum=min(copy_list)
        index=score_list.index(minimum)
        copy_list.remove(minimum)
        sort_list.append(minimum)
    return sort_list

def sorted_higher(score_list,value):
    """
    This function returns the sorted list of participants with scores higher than a specified value.
    :param score_list: a list
    :param value: the value with which we are going to compare the scores
    :return: the sorted list of the participants with scores higher than value
    """
    sort_list=[]
    copy_list=score_list.copy()
    for i in range(len(score_list)):
        minimum=min(copy_list)
        index=score_list.index(minimum)
        copy_list.remove(minimum)
        if(minimum>value):
            sort_list.append(minimum)
    return sort_list


