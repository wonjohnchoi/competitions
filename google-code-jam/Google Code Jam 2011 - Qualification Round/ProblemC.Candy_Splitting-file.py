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
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\C.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\C.in','r')
    
    t = int(str(next(fin)).strip())
    for i in range(t):
        n = int(str(next(fin)).strip())
        bag = list(map(int, str(next(fin)).strip().split(' ')))
        
        fout.write('Case #%d: '%(i+1))
        
        if xorSum(bag)!=0:
            fout.write('NO\n')
        else:
            fout.write(str(sum(bag)-min(bag)))
            fout.write('\n')
            
    fin.close()
    fout.close()
solve()