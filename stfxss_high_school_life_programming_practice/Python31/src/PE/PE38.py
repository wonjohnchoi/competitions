import time

def isPandigitalGenerator(x):
    cur = 1
    result = ''
    while True:
        result+=str(cur*x)
        if len(result)>9:
            return False
        elif len(result) == 9:
            break
        cur+=1
    for x in range(1, 10):
        if result.find(str(x))==-1:
            return False
    return True

def solve():
    max = -1
    for cur in range(10000):
        if isPandigitalGenerator(cur):
            if max<cur:
                max=cur
    
    result = ''
    fac = 1
    while len(result)!=9:
        result+=str(fac*max)
        fac+=1
    return result

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
    Answer: 932718654
    Time elapsed: 0.12 seconds
"""
