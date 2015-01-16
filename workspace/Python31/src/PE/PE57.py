import time
from math import log10

def solve():
    top=3;bot=2;counter=0
    for i in range(1000):
        if int(log10(top))>int(log10(bot)):
            counter+=1
        bot, top = top+bot, 2*bot+top 
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
    Answer: 153
    Time elapsed: 0.01 seconds
"""
