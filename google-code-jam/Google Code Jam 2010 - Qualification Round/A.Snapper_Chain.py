'''
Problem
@YEAR Google Code Jam 2010: Qualification Round
@ID A
@NAME Snapper Chain
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

import math

def solve():
    fin = open('A.in', 'r')
    fout = open('A.out', 'w')
    
    t = int(next(fin))
    
    for i in range(t):
        n, k = map(int, next(fin).split(' '))
        
        ans = None
        if (k+1)%(2**n)==0:
            ans = 'ON'
        else:
            ans = 'OFF'
            
        fout.write('Case #%d: %s\n'%(i+1, ans))
    
    fin.close()
    fout.close()
solve()