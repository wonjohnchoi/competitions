'''
IO utilities
Wonjohn Choi
Python3
'''
import re

def getInput(inFile):
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
    
    #lines = iter(fin.readlines())
    inLine = lambda: next(fin)[:-1] #inLine is same to nextLine in Java; don't mix with others.
    return inStr, inInt, inFloat, inLine

def getOutput(outFile):
    fout = open(outFile,'w')
    out = lambda s: fout.write('%s'%str(s)) #out is same to print in Java
    outln = lambda s: fout.write('%s\n'%str(s)) #outln is same to println in Java
    return out, outln
