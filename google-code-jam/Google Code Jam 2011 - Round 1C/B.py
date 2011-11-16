'''
Problem
@CONTEST Google Code Jam 2011: Round 1C
@ID B
@NAME Space Emergency
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT AC [small, large]
'''

import re
import itertools

#IO FILE NAMES
inFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2011 - Round 1C\B.in'
outFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2011 - Round 1C\B.out'

#INPUT HELPER
fin = open(inFile,'r')
def getTokens():
    while True:
        ins = re.findall(r'\S+', next(fin))
        for input in ins:
            yield input
tokens = getTokens()
inStr = lambda: next(tokens) #inStr is same to next in Java
inInt = lambda: int(next(tokens)) #inInt is same to nextInt in Java
inFloat = lambda: float(next(tokens)) #inFloat is same to nextFloat in Java

#OUTPUT HELPER
fout = open(outFile,'w')
out = lambda s: fout.write('%s'%str(s)) #out is same to print in Java
outln = lambda s: fout.write('%s\n'%str(s)) #outln is same to println in Java


#LOGIC
def calc(l, t, n, c, pattern):
    dists = pattern * (n//c) + pattern[:n%c]
    time = sum(dists)*2
    
    t1 = t
    idx = 0
    while t!=0 and n>idx:
        if t>=dists[idx]*2:
            t-=dists[idx]*2
            idx+=1
        else:
            dists[idx]-=t/2
            t=0
    
    if n==idx: return t1-t
    dists = dists[idx:]
    dists.sort(reverse=True)
    return time-sum(dists[0:min(l,len(dists))])
    
tt = inInt()
for i in range(tt):
    l, t, n, c = (inInt() for j in range(4))
    pattern = [inInt() for j in range(c)]
    outln('Case #%d: %d'%(i+1, calc(l, t, n, c, pattern)))
#ABANDONED CODE BELOW IS CLOSE TO ANSWER. BUT I WENT HARD WAY AND MADE AN ERROR.
def memoryOfFailure():
    def calc(l, t, n, c, pattern):
        n_pattern = n//c
        thres = n%c
        
        cost_pattern = sum(pattern) * 2
        cost_patterns = cost_pattern * n_pattern
        cost_all = cost_patterns + sum(pattern[:n%c]) * 2
        
        
        if cost_all <= t: return cost_all
        elif cost_patterns <= t:
            t -= cost_patterns
            remain = pattern[:thres]
            while t!=0:
                if remain[0]*2<=t:
                    t-=remain[0]*2
                    remain.pop(0)
                else:
                    remain[0]-=t/2
                    t = 0
            remain.sort()
            return sum(remain)*2-sum(remain[len(remain)-l:])
        
        #spent t seconds
        #by chunk of patterns
        n_pattern -= t//cost_pattern
        t%=cost_pattern
        
        #by parts of a pattern
        n_pattern -= 1
        used_pattern = pattern[:]
        while t!=0:
            if used_pattern[0]*2<=t:
                t-=used_pattern[0]*2
                used_pattern.pop(0)
            else:
                used_pattern[0]-=t/2
                t = 0
        
        
        dist = {e: n_pattern for e in pattern}
        for e in pattern[:thres]:
            dist[e]+=1
        
        for e in used_pattern:
            try:
                dist[e]+=1
            except KeyError:
                dist[e]=1
                pattern+=[e]
        #print(cost_all, dist)
        pattern.sort(reverse=True)
        for e in pattern:
            if dist[e]<=l:
                l-=dist[e]
                cost_all-=e*dist[e]
            else:
                cost_all-=e*l
                l=0
            if l==0: break
        return cost_all
        
            

    tt = inInt()
    for i in range(tt):
        l, t, n, c = (inInt() for j in range(4))
        pattern = [inInt() for j in range(c)]
        outln('Case #%d: %d'%(i+1, calc(l, t, n, c, pattern)))
        
