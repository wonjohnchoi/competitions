import time
from math import sqrt

def generatePrimes(size):
    primes = set(range(2, size))
    composites = set()
    for x in range(2, int(sqrt(size))):
        if x in primes:
            for y in range(x*x,size, x):
                composites.add(y)
                primes.discard(y)
    return composites, primes

def twiceSquare(x):
    y = sqrt(x/2)
    return y == int(y)
    
def solve(size):
    composites, primes = generatePrimes(size)
    
    for x in composites:
        if x%2==1:
            exist = False
            for y in primes:
                diff = x-y
                if diff>0:
                    if twiceSquare(diff):
                        exist = True
                else:
                    break
            if not exist:
                return x
    
def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve(10000))
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 16, 2010
@result
    Answer: 5777
    Time elapsed: 3.03 seconds
"""
