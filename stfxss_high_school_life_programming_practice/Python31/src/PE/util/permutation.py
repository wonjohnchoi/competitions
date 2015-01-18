'''
Created on 2010. 6. 18.

@author: Wonjohn Choi
'''
def isPermutation(x, y):
    x, y =str(x), str(y)
    if len(x)!=len(y):
        return False
    
    for letter in x:
        if letter not in y:
            return False
    return True
                
def next(given):
    """
    @author: http://code.activestate.com/recipes/252178/
    works for both list and string
    allow duplicate perms
    """
    if len(given) <=1:
        yield given
    else:
        for permutation in next(given[1:]):
            for i in range(len(permutation)+1):
                yield permutation[:i] + given[0:1] + permutation[i:]

if __name__=='__main__':
    import itertools
    for perm in next('123456789'):
        pass#print(perm) 
    
