'''
Problem
@CONTEST Google Code Jam 2011: Round 1B
@ID B
@NAME Revenge of the Hot Dogs
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\B.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\B.in','r')
    t = int(next(fin).strip())
    for i in range(t):
        c,d = map(int, next(fin).strip().split(' '))
        pts = []
        
        for j in range(c):
            p, v = map(int, next(fin).strip().split(' '))
            pts.append([p,v])
        
        pts = sorted(pts, key = lambda pt: pt[0])
        #print(pts)
        
        #involves lots of mathemaitcs
        chunk = -100001
        vpre = 0
        t = 0
        
        for pt in pts:
            p = pt[0]
            v = pt[1]
            chunk = max(chunk+vpre*d, p)
            t = max(t, (chunk+v*d-p-d)/2)
            vpre = v
        fout.write('Case #%d: %f\n'%(i+1, t))
            
solve()