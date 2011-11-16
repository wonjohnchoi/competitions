'''
Problem
@CONTEST Google Code Jam 2011: Round 1C
@ID A
@NAME Square Tiles
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT AC [small, large]
'''

import re

#IO FILE NAMES
inFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2011 - Round 1C\A.in'
outFile = r'C:\Users\Wonjohn Choi\source_codes\Google Code Jam\Google Code Jam 2011 - Round 1C\A.out'

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
import itertools
t = inInt()
for i in range(t):
    outln('Case #%d:'%(i+1))
    
    r, c = inInt(), inInt()
    map = []
    for j in range(r):
        map.append(list(inStr()))
    #print('\n'.join(map))
    
    fail = False
    
    while not fail:
        pos = None
        for j, k in itertools.product(range(r), range(c)):
            if map[j][k] == '#':
                pos = (j, k)
                break
        if pos == None:
            break
        else:
            y, x = pos
            if (y+1)>=r or (x+1)>=c:
                fail = True
            elif map[y+1][x] == '.' or map[y][x+1] == '.' or map[y+1][x+1] == '.':
                fail = True
            else:
                map[y][x], map[y][x+1] = '/', '\\'
                map[y+1][x], map[y+1][x+1] = '\\', '/'
                
    
    if fail:
        outln('Impossible')
    else:
        outln('\n'.join([''.join(line) for line in map]))
        
        