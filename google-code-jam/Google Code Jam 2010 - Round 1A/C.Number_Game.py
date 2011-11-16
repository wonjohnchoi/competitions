'''
Problem
@YEAR Google Code Jam 2010: Round 1A
@ID C
@NAME Number Game 
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT #accepted (small input, large input)
'''
from math import ceil, floor

goldenRatio = (1+5**0.5)/2

def cntWins(a1, a2, b1, b2):
    #assume b>a
    #b>=a*goldenRatio to win

    cnt = 0
    for a in range(a1, a2+1):
        limit=ceil(a*goldenRatio)
        if b2>=limit:
            cnt += b2 - max(limit, b1) + 1
    return cnt

def solve():
    dd = 'C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2010 - Round 1A\\'
    fin = open(dd+'C.in', 'r')
    fout = open(dd+'C.out', 'w') 

    t = int(next(fin))
    
    
    for i in range(t):
        a1, a2, b1, b2 = map(int, next(fin).strip().split(' '))
        
        cnt = 0
     
        cnt += cntWins(a1,a2,b1,b2)
        cnt += cntWins(b1,b2,a1,a2)
        
        
        fout.write('Case #%d: %d\n'%(i+1, cnt))    
            
    fin.close()
    fout.close()
solve()