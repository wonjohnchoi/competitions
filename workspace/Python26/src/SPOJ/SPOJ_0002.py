'''
@SPOJ Message

@author Wonjohn Choi
'''
import math, itertools, random


def getInputs():
    return [tuple(map(int,raw_input().split())) for x in range(int(raw_input()))]

def sieveGen(size):
    isPrime = [False]*2+[True]*(size-2)
    for i in range(2, int(math.sqrt(size))+1):
        if isPrime[i]:
            isPrime[i*i:size:i] = [False]*((size-1)//i-i+1)
    return isPrime

def witness(a, n):
    '''
    developed by miller_rabin
    '''
    d = n - 1
    s = 0
    while d % 2 == 0:
        d >>= 1 #divide by 2 using bit
        s += 1
    a_to_power = pow(a, d, n)
    if a_to_power == 1:
        return True #prime
    for i in range(s-1):
        if a_to_power == n - 1:
            return True
        a_to_power = (a_to_power * a_to_power) % n
    return a_to_power == n - 1


def isProbablyPrime(n):
    '''
    it is ok to test 2, 7, 61 for 32 bits <2**32
    only use for huge number over 1355000
    modified version of miller_rabin prime
    '''
    if n<2: return False
    if n>2 and not witness(2,n): return False
    if n>7 and not witness(7,n): return False
    if n>61 and not witness(61,n): return False
    return True

inputs=getInputs()
#sieve = sieveGen(max(input[1] for input in inputs)+1)
for input in inputs:
    print '\n'.join(map(str,itertools.ifilter(lambda x:isProbablyPrime(x), range(input[0],input[1]+1))))
    print 



