import time

from math import sqrt

def generatePrimes(size):
    isPrime = [True for x in range(size)]
    isPrime[0] = False
    isPrime[1] = False

    for x in range(2, int(sqrt(size))):
        if isPrime[x]:
            for y in range(x*x,size, x):
                isPrime[y] = False
    return isPrime


def getParts(x):
    x=str(x)
    parts = [x]
    right = x[:-1]
    left = x[1:]

    while len(right)>0:
        parts.append(right)
        right = right[:-1]
    while len(left)>0:
        parts.append(left)
        left = left[1:]
    return parts

def solve(size):
    isPrime = generatePrimes(size)
    count = 0
    cur = 11
    sum = 0

    while count!=11:
        isDone = True
        for str in getParts(cur):
            if not isPrime[int(str)]:
                isDone = False
                break
        if isDone:
            count+=1
            sum+=cur
        cur+=1

    return sum

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve(10000000))
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))

main()

"""
@author Wonjohn Choi
@date June 17, 2010
@result
    Answer: 748317
    Time elapsed: 28.05 seconds
"""
