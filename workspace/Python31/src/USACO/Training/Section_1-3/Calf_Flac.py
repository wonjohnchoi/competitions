'''
Created on 2010. 6. 28.
@TItle: Calf Flac
@URL: http://ace.delos.com/usacoprob2?a=5CjWZbMMiS8&S=calfflac
@Idea: create a string that contains no punctuation or whilespace
       take out all punctuation and whilespace
       find best palindrome
       find the location
@author: Wonjohn Choi
'''

import string

def isPal(s):
    if len(s)==0 or len(s)==1: 
        return True
    if s[0][1]!=s[-1][1]:
        return False
    return isPal(s[1:-1])

def findLongestPal(s):
    length=len(s)
    size=min(length,2000)
    while size>=1:
        for x in range(0,length-size+1):
            if isPal(s[x: x+size]):
                return s[x][0],s[x+size-1][0], size
        size-=1


paragraph=''.join(open('calfflac.in').readlines())
shorten=[x for x in enumerate(paragraph.upper())]
punc=string.punctuation+' \n'

idx=0
while idx<len(shorten):
    for punctuation in punc:
        if shorten[idx][1]==punctuation:
            del shorten[idx]
            if idx<len(shorten):continue
            else: break
    idx+=1
s,e,size=findLongestPal(shorten)

file=open('calfflac.out','w')
file.write(str(size)+'\n')
file.write(paragraph[s:(e+1)])


