def check_score(sc):
    """
    This function checks whether the score that has been introduced is valid or not
    :param sc: score
    :return: True- if the score is valid, false- if the score is NOT valid
    """
    if sc<0 or sc>100:
        return False
    return True

def check_index(ind,lst):
    """
    This function cheks whether the index that has been introduced is valid or not
    :param ind: index
    :param lst: scores list
    :return: True- if the index is valid, false- if the index is NOT valid
    """
    if ind<0 or ind>=len(lst):
        return False
    return True