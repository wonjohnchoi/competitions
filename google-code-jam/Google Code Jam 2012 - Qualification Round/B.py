'''
Problem B. Dancing With the Googlers

Input 
4
3 1 5 15 13 11
3 0 8 23 22 21
2 1 1 8 0
6 2 8 29 20 8 18 18 21
 	
Output  
Case #1: 3
Case #2: 2
Case #3: 1
Case #4: 3

Author: Wonjohn Choi
Language: Python3
'''
import IO

#IO FILE NAMES
inStr, inInt, inFloat, inLine  = IO.getInput('B.in')
out, outln = IO.getOutput('B.out')
#print(next(open('B.in')))
t = inInt()
for i in range(t):
    n = inInt()
    s = inInt()
    p = inInt()
    ts = []
    
    good = bad = fail = 0
    good_point = max(0, p - 1) + max(0, p - 1) + p #minimum point to succeed with p
    bad_point =  max(0, p - 2) + max(0, p - 2) + p #minimum point to be surprised with p
    for j in range(n):
        point = inInt()
        if point >= good_point:
            good += 1
        elif point >= bad_point :
            bad += 1
        else:
            fail += 1
    
    answer = good + min(bad, s)
    #print(good, bad, fail, s)
    outln('Case #%d: %d' %(i + 1, answer))
