'''
Problem
@CONTEST Google Code Jam 2011: Round 1C
@ID A
@NAME Square Tiles
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT
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
