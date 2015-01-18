import time
import random
 
def witness(a, n):
    d = n - 1
    s = 0
    while d % 2 == 0:
        d >>= 1 #divide by 2 using bit
        s += 1
    a_to_power = pow(a, d, n)
    if a_to_power == 1:
        return True #prime
    for i in range(s-1):
        if a_to_power == n - 1:
            return True
        a_to_power = (a_to_power * a_to_power) % n
    return a_to_power == n - 1
    
    
def isProbablyPrime(n, re=0):
    '''
    it is ok to test 2, 7, 61 for 32 bits <2**32
    only use for huge number over 1355000
    modified version of miller_rabin prime
    '''
    if n<2: return False
    if n>2 and not witness(2,n): return False;
    if n>7 and not witness(7,n): return False;
    if n>61 and not witness(61,n): return False;
    
    for i in range(re):
        a = random.randrange(1, n)
        if not witness(a, n):
            return False
    return True

def solve():
    tot=5;prime=3;layer=1
    while prime/tot>=1/10:
        layer+=1;tot+=4
        prime+=sum((isProbablyPrime((layer*2+1)**2-x*layer*2)) for x in range(4))
    return layer*2+1
    

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
    Answer: 26241
    Time elapsed: 2.75 seconds
"""
