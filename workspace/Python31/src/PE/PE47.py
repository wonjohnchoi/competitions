import time
import math


def generatePrimes(size):
    isPrime = [True for x in range(size)]
    isPrime[0] = False
    isPrime[1] = False

    for x in range(2, int(math.sqrt(size))):
        if isPrime[x]:
            for y in range(x*x,size, x):
                isPrime[y] = False
    
    primes=[]
    for x in range(2, size):
        if isPrime[x]:
            primes.append(x)
    return primes

primes = generatePrimes(1000000)

def numPrimeFactors(num):
    count=0
    for div in primes:
        if div * div>num: break
        if num%div==0:
            count+=1
            while num%div==0:
                num/=div
    if num!=1:
        count+=1
    return count

def solve():
    cur=647
    previous = [False, False, False, False]
    
    while True:
        previous=previous[1:]
        previous.append(numPrimeFactors(cur)==4)
                       
        if all(previous) :
            return cur-3
        cur+=1
    
def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()
            
"""
@author Wonjohn Choi
@date June 17, 2010
@result
    Answer: 134043
    Time elapsed: 6.73 seconds
"""
