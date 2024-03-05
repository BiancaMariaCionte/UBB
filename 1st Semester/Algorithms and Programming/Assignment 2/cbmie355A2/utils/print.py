def print_list(lst,option):
    if option==0:
        print("The scores list is:",lst)
    elif option==1:
        if len(lst)>0: #if the list is not empty
            print("The scores list is:",lst)
        else:
            print("The scores list is empty.")
    elif option==2:
        print("The returned list is:",lst)
    return
