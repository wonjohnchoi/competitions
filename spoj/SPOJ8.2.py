'''
Problem
@ID 8
@NAME Complete the Sequence!
@CODE CMPLS
@URL https://www.spoj.pl/problems/CMPLS/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG Python 31
@RESULT time limit exceeded (Resursion in python is costly)
'''

def nextSeq(seq, count):
    '''
    given a sequence 'seq', return next 'count' # of elements of the sequence
    it is done recursively:
    base condition: if the sequence has one element 'e', its next elements will have the same value.
    else: get next elements of subsequence 'subseq' and use them to find 'seq''s next elements
    '''
    
    size = len(seq)
    if size==1:
        return [seq[0] for i in range(count)]
    else:
        subseq = []
        for i in range(size-1):
            subseq.append(seq[i+1]-seq[i])
        nextsubseq = nextSeq(subseq, count)
        #print(seq, sub_seq, fsize)
        nextseq = [seq[-1]]
        for i in range(count):
            nextseq.append(nextseq[i] + nextsubseq[i])
        return nextseq[1:]
def solve():
    t = int(input())
    
    for i in range(t):
        s,c = list(map(int, str(input()).split(' ')))
        seq = list(map(int, str(input()).split(' ')))
        
        for e in nextSeq(seq, c):
            print(e, end=' ')
        print()
        
solve()