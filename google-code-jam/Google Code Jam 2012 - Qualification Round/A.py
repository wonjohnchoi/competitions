'''
Speaking in Tongues

Input
3
ejp mysljylc kd kxveddknmc re jsicpdrysi
rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
de kr kd eoya kw aej tysr re ujdr lkgc jv


Output
Case #1: our language is impossible to understand
Case #2: there are twenty six factorial possibilities
Case #3: so it is okay if you want to just give up

Author: Wonjohn Choi
Language: Python3
'''
import IO

#IO FILE NAMES
inStr, inInt, inFloat, inLine  = IO.getInput('A.in')
out, outln = IO.getOutput('A.out')
input = [char for char in "ejp mysljylc kd kxveddknmc re jsicpdrysi rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd de kr kd eoya kw aej tysr re ujdr lkgc jv"]
output = [char for char in "our language is impossible to understand there are twenty six factorial possibilities so it is okay if you want to just give up"]

dictionary = {}
for inchar, outchar in zip(input, output):
    if inchar not in dictionary:
        dictionary[inchar] = outchar

dictionary['q'] = 'z' #given
dictionary['z'] = 'q' #given

n = int(inLine())
for i in range(n):
    line = inLine()
    #print(repr(line))
    translated = ''.join(dictionary[char] for char in line)
    outln('Case #%d: %s'%(i+1, translated))
