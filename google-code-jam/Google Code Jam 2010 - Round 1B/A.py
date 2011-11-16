'''
Problem
@CONTEST Google Code Jam 2010: Round 1B
@ID A
@NAME File Fix-it
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT AC (small, large)
'''

import re

#IO FILE NAMES
inFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2010 - Round 1B\A.in'
outFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2010 - Round 1B\A.out'

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
def construct(root, path):
    if len(path)==0: return 0
    
    try:
        return construct(root[path[0]], path[1:])
    except KeyError:
        root[path[0]] = {}
        return 1 + construct(root[path[0]], path[1:])
       
t = inInt()
for i in range(t):
    n = inInt()
    m = inInt()
    
    root = {}
    for j in range(n):
        construct(root, re.findall('[^/]+', inStr()))
    
    efforts = 0
    for j in range(m):
        efforts+=construct(root, re.findall('[^/]+', inStr()))
            
    outln('Case #%d: %d'%(i+1, efforts))
        
