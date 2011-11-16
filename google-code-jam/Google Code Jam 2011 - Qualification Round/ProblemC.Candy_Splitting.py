'''
Problem
@YEAR Google Code Jam 2011: Qualification Round
@ID C
@NAME Candy Splitting
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def xorSum(bag):
    if len(bag)==0: return 0
    
    sum = bag[0]
    for i in range(1, len(bag)):
        sum^=bag[i]
    return sum

def solve():
    t = int(input())
    for i in range(t):
        n = int(input())
        bag = list(map(int, str(input()).strip().split(' ')))
        
        print('Case #%d: '%(i+1), end='')
        
        if xorSum(bag)!=0:
            print('NO')
        else:
            print(sum(bag)-min(bag))
  
solve()