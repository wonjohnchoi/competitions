import time

def isLychrel(i, depth):
    if depth==0: return True
    if str(i)==str(i)[::-1]: return False
    return isLychrel(i+int(str(i)[::-1]), depth-1)
    
    
def solve():
    return sum(isLychrel(i+ int(str(i)[::-1]), 50) for i in range(1, 10000))
    
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
    Answer: 249
    Time elapsed: 0.54 seconds
"""
