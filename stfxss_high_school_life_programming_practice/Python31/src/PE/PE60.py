import time
import math
from util import ppf 

size=20000
p=ppf.prime(100000000)

def concat(x,y):
    return int(x*math.pow(10, int(math.log10(y))+1)+y)

def concatPrime(x, y):
    return p.isPrime(concat(x,y)) and p.isPrime(concat(y,x))     

def find(found=None):
    if found==None:
        found=[]
    if len(found)==5:
        return sum(found)
        
    limit=size
    if len(found)!=0:
        limit=found[-1]
    
    for prime in p.sievePrimeGen(0, limit):
        if all(concatPrime(prime, foundPrime) for foundPrime in found):
            val=find(found+[prime])
            if val!=None: return val
     
def main():
    if __name__ == '__main__':
        start = time.time()
        print('Answer:', find())
        end = time.time()
        print("Time elapsed: %.2f seconds" %(end-start))
    
main()
            
"""
@author Wonjohn Choi
@date June 17, 2010
@result
    Answer: 26033
    Time elapsed: 115.42 seconds
"""
