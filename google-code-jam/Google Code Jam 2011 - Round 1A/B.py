'''
Problem
@CONTEST Google Code Jam 2011: Round 1A
@ID B
@NAME The Killer Word
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted for small and large
'''

import re

#IO FILE NAMES
inFile = 'C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 1A\\B.in'
outFile = 'C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 1A\\B.out'

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

#score is a dictionary for lost points when choosing a word
score = None       

#words contains many words with the same length
#a word is a list of characters
def updateScore(words, order, idx):
    #base case
    if len(words)==1: return

    #contains => collection of letters used in 'words'
    contains = [False]*26
    for word in words:
        for c in word:
            contains[ord(c)-ord('a')] = True

    #using contains and order (possible guesses), find the next letter to guess
    nextGuess = None
    while nextGuess==None:
        if contains[ord(order[idx])-ord('a')]:
            nextGuess = order[idx]
        idx+=1
    
    #classify words by the positions of letters guessed in a word    
    matched = [[] for i in range(2**len(words[0]))]
    
    for word in words:
        pos = 0
        multi = 1
        for c in word:
            if c == nextGuess:
                pos+=multi
            multi*=2
        matched[pos].append(word)
        
        if pos == 0:
            score[word]+=1
    
    #recursively use the classified words (group) to update score
    for group in matched:
        if len(group)!=0:
            updateScore(group, order, idx)
            

def solve():
    t = inInt()
    for i in range(t):
        n, m = inInt(), inInt()
        words = [inStr() for j in range(n)]
        
        out('Case #%d:'%(i+1))
        for k in range(m):
            global score
            score = {word:0 for word in words}
            order = inStr()
            
            #classify words by its length
            matched = [[] for l in range(10)]
            for word in words:
                matched[len(word)-1].append(word)
            
            #update score using the group of classified words
            for group in matched:
                if len(group)!=0:
                    updateScore(group, order, 0)
                    
            #find the best word based on the score
            best = words[0]
            for word in words:
                if score[best]<score[word]:
                    best = word
                    
            out(' %s'%(best))
        outln('')
    
               
solve()
fin.close()
fout.close()