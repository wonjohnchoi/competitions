import time

def solve():
    return max(sum(int(digit) for digit in str(a**b)) for a in range(90,100) for b in range(90,100))
    
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
    Answer: 972
    Time elapsed: 0.08 seconds
"""
