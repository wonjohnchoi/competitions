'''
Problem
@ID 8
@NAME Complete the Sequence!
@CODE CMPLS
@URL https://www.spoj.pl/problems/CMPLS/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG Python 31
@RESULT time limit exceeded (python is just too slow)
'''

cache = [[0 for j in range(100)] for i in range(100)]

def buildCache(seq, c):
    s = len(seq)
    for i in range(s):
        cache[0][i] = seq[i]

    #build
    for order in range(1, s):
        for idx in range(s-order):
            cache[order][idx] = cache[order-1][idx+1]-cache[order-1][idx]
    
    for idx in range(1,1+c):
        cache[s-1][idx] = cache[s-1][0]
        
    #add next sequences to all levels
    for order in range(s-2,-1,-1):
        for idx in range(s-order, s-order+c):
            cache[order][idx] = cache[order][idx-1]+cache[order+1][idx-1]
            
def solve():
    t = int(input())
    
    for i in range(t):
        s,c = list(map(int, str(input()).split(' ')))
        seq = list(map(int, str(input()).split(' ')))
        
        buildCache(seq, c)
        
        result = cache[0][s:s+c]
        
        for e in result:
            print(e, end=' ')
        print()
        
solve()