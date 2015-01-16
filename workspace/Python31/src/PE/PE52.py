import time

def isPermutation(x, y):
    x,y=str(x),str(y)
    for letter in x:
        if letter not in y:
            return False
    return True

def solve():
    '''
    To have same digits, should have same length
    10000~99999
    99999/6 = 16666.5
    x=100000~166666
    '''
    for x in range(1,10):
        for num in range(10**x, int(10**(x+1)/6)):
            for z in range(2, 7):
                if not isPermutation(num, num*z):
                    break
            else:
                return num 
    
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
    Answer: 142857
    Time elapsed: 0.29 seconds
"""
