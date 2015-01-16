import time
from math import sqrt

def isPenta(num):
    idx = (1+ sqrt(1+24*num))/6
    return idx == int(idx)

def solve():
    idx = 0
    penta = [0]
    while True:
        idx+=1
        penta.append(idx*(3*idx-1)/2)
        for x in range(1, idx-1):
            if isPenta(penta[idx]-penta[x]) and isPenta(penta[idx]+penta[x]):
                return int(penta[idx]-penta[x])

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 16, 2010
@result
    Answer: 5482660
    Time elapsed: 8.65 seconds
"""
