'''
Problem
@ID 2
@CODE PRIME1
@URL https://www.spoj.pl/problems/PRIME1/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
'''
from math import sqrt

def getPrimeSieve(size): 
    '''
    collection of primes from 0 to size-1 (inclusive)
    '''
    sieve = list(range(size))
    
    for i in range(2,int(sqrt(size))+1):
        if sieve[i]:
            sieve[i*i::i] = [None]*((size-1)//i-i+1)
    
    return [n for n in sieve[2:] if n]

def getAdvancedPrimeSieve(sieve, start, end): 
    '''
    collection of primes from start to end-1 (inclusive)
    '''
    
    #start ~ end is a part of sieve
    if (end-1)<=sieve[-1]:
        i = 0
        while sieve[i]<start: i+=1
        j = i
        while sieve[j]<end: j+=1
        return sieve[i:j]
    
    
    givenSieve = []
    
    #start ~ somewhere is a part of sieve
    if start<=sieve[-1]:
        i = 0
        while sieve[i]<start: i+=1
        givenSieve = sieve[i:]
        start = sieve[-1]+1

    size = end-start
    
    advancedSieve = list(range(start, end))
    
    limit = int(sqrt(end))+1
    for prime in sieve:
        if prime>limit: break
        
        fPrime = -start%prime
        if fPrime<0: fPrime+=prime
        tPrime = (end-1)-(end-1)%prime-start
        
        advancedSieve[fPrime::prime] = [None] * (tPrime//prime - fPrime//prime +1)
    return givenSieve+[n for n in advancedSieve if n]
    
def solve():
    mRange = 1000000000
    sieve = getPrimeSieve(int(sqrt(mRange))+1)
    
    t = int(input())
    for i in range(t):
        s,e = map(int, input().split(' '))
        
        for prime in getAdvancedPrimeSieve(sieve, s, e+1):
            print(prime)
        print()

solve()