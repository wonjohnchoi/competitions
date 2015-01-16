'''
Problem
@YEAR Google Code Jam 2011: Qualification Round
@ID D
@NAME GoroSort
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\D.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\D.in','r')
    
    t = int(str(next(fin)).strip())
    for i in range(t):
        n = int(str(next(fin)).strip())
        bag = list(map(int, str(next(fin)).strip().split(' ')))
        cnt = 0
        
        for j in range(len(bag)):
            if bag[j]!=j+1:
                cnt+=1
        
        fout.write('Case #%d: %d\n'%(i+1,cnt))
        
solve()