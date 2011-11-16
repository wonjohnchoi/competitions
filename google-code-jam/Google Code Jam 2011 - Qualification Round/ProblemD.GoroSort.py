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
    t = int(input())
    for i in range(t):
        n = int(input())
        bag = list(map(int, str(input()).strip().split(' ')))
        cnt = 0
        
        for j in range(len(bag)):
            if bag[j]!=j+1:
                cnt+=1
        
        print('Case #%d: '%(i+1), end='')
        print(cnt)
        
solve()