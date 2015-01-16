import time
from math import factorial

def solve():
    counter = 0
    for n in range(1, 101):
        for r in range(0, n+1):
            if factorial(n)/factorial(n-r)/factorial(r)>1000000:
                counter+=1
    return counter
    
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
    Answer: 4075
    Time elapsed: 0.29 seconds
"""
