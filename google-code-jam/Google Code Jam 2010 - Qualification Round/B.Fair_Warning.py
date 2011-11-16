'''
Problem
@YEAR Google Code Jam 2010: Qualification Round
@ID B
@NAME Snapper Chain
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def gcd(bag):
    while len(bag)!=1:
        e1, e2 = bag.pop(), bag.pop()
        
        while e1!=0:
            t = e1
            e1 = e2%e1
            e2 = t
        
        bag.append(e2)
    return bag[0]

def solve():
    fin = open('B.in', 'r') 
    fout = open('B.out', 'w')
    
    t = int(next(fin))
    
    for i in range(t):
        ins = sorted(list(map(int, next(fin).split(' ')))[1:], reverse = True)
        n = len(ins)
        diffs = []
        
        for j in range(n-1):
            diffs.append(ins[j]-ins[j+1])

        divisor = gcd(diffs)
        
        theDay = ins[-1]%divisor
        if theDay>0:
            theDay=abs(theDay-divisor)
        
        fout.write('Case #%d: %d\n'%(i+1, theDay))
    fin.close()
    fout.close()
solve()