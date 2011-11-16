'''
Problem
@CONTEST Google Code Jam 2011: Round 2
@ID A
@NAME
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT 
'''

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 2\\A.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 2\\A.in','r')

    
    t = int(next(fin))
    
    for i in range(t):
        
        x, s, r, t, n = map(int, next(fin).strip().split(' '))
        parts = [] #[[big, end, speed],  ] 
        
        for j in range(n):
            b, e, w = map(int, next(fin).strip().split(' '))
            parts.append([b, e, w+s])
            
        boost = r - s #for t seconds
        #print(parts)
        parts = sorted(parts, key = lambda part: part[0])
        #print(parts)
        
        
        parts.insert(0, [-1, 0, -1])
        parts.append([x, -1, -1])
        #print(parts)
        newParts = []
        
        for j in range(1, len(parts)):
            p1, p2 = parts[j-1], parts[j]
            
            if p2[0]>p1[1]:
                newParts.append([p1[1], p2[0], s])
                
        parts.pop(0)
        parts.pop()
        parts += newParts
        
        p = []
        for part in parts:
            p.append([part[1]-part[0], part[2]])
        parts = p
        
        #print(parts)
        parts = sorted(parts, key = lambda part: part[1])
        #print(parts)
        
        
        time = 0
        
        for part in parts:
            tWithBoost = (part[0])/(boost + part[1])
            if tWithBoost>=t:
                time += t
                part[0]-=t * (boost + part[1])
                t = 0
                break
            else:
                part[0] = 0
                time += tWithBoost
                t -= tWithBoost
        #print(parts)
       
        for part in parts:
            time+=part[0]/part[1]
        fout.write('Case #%d: %f\n'%(i+1, time))
        
        
        
    
    fout.close()
    fin.close()
solve()