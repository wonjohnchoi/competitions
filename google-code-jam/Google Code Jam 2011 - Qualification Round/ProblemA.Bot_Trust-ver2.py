'''
Problem
@YEAR Google Code Jam 2011: Qualification Round
@ID A
@NAME Bot Trust
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''
import os

def solve():
    fin = open('A.in', 'r')
    fout = open('A.out', 'w')
    
    t = int(next(fin))
    
    for case in range(t):
        line = list(str(next(fin)).strip().split(' '))[1:]
        
        timeO = timeB = 0
        posO = posB = 1
        
        for i in range(0,len(line),2):
            line[i+1] = int(line[i+1])
            
            if str(line[i])=='O':
                timeO = max(timeO + abs(line[i+1]-posO), timeB) + 1
                posO = line[i+1]
            else:
                timeB = max(timeB + abs(line[i+1]-posB), timeO) + 1
                posB = line[i+1]
                
                
        fout.write('Case #%d: %d\n'%(case+1, max(timeO, timeB)))
        
    fin.close()
    fout.close()
solve()
        