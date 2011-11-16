'''
Problem
@ID 8
@NAME Complete the Sequence!
@CODE CMPLS
@URL https://www.spoj.pl/problems/CMPLS/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG Python 31
@RESULT time limit exceeded (unnecessary computations)
'''

def fill(seq, fsize):
    '''
    given a sequence 'seq', append new element to the sequence until its size reaches a size of 'fsize'
    it is done recursively:
    base condition: if the sequence has one element 'e', its next element will have the same value.
    else: get subsequence 'sub_seq' by subtracting consequent values and fill to the size of 'fsize-1'
    '''
    isize = len(seq)
    if isize==1:
        for i in range(fsize-1):
            seq.append(seq[0])
    else:
        sub_seq = []
        for i in range(isize-1):
            sub_seq.append(seq[i+1]-seq[i])
        fill(sub_seq, fsize-1)
        #print(seq, sub_seq, fsize)
        for i in range(isize, fsize):
            seq.append(seq[i-1] + sub_seq[i-1])
        
def solve():
    t = int(input())
    
    for i in range(t):
        s,c = list(map(int, str(input()).split(' ')))
        seq = list(map(int, str(input()).split(' ')))
        
        fill(seq, s+c)
        
        for i in range(s, s+c):
            print(seq[i], end=' ')
        print()
        
solve()