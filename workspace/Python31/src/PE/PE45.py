import time
from math import sqrt

def isTri(x):
    idx = (sqrt(1+8*x)-1)/2
    return idx == int(idx)

def isPenta(x):
    idx = (sqrt(1+24*x)+1)/6
    return idx == int(idx)

def solve():
    idx = 144
    while True:
        hexa = idx*(2*idx-1)
        if isTri(hexa) and isPenta(hexa):
            return int(hexa)
        idx+=1

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:',solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 16, 2010
@result
    Answer: 1533776805
    Time elapsed: 0.28 seconds
"""
