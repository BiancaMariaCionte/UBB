# FEATURE 6
"""
6. Undo
• 𝑢𝑛𝑑𝑜() – undo the last operation that modified the list
"""
def undo(history_list):
    if len(history_list) > 1:
        history_list.pop()
        lst = history_list[-1].copy()
        return lst
    else:
        print("You can not undo the list.")
        return history_list[-1]