import time
import math
import itertools

def nextPermutation(given):
    if len(given) <=1:
        yield given
    else:
        for permutation in nextPermutation(given[1:]):
            for i in range(len(permutation)+1):
                yield permutation[:i] + given[0:1] + permutation[i:]
def isPrime(num):
    if num==2:
        return True
    if num%2==0:
        return False
    for x in range(3, int(math.sqrt(num))+1, 2):
        if num%x == 0:
            return False
    return True

def solve():
    max=-1
    for perm in nextPermutation('1234567'):
        perm=int(perm)
        if isPrime(perm):
            if max<perm:
                max=perm
    return max
    
def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))

main()

"""
@author Wonjohn Choi
@date June 18, 2010
@result
    Answer: 7652413
    Time elapsed: 0.39 seconds
"""
