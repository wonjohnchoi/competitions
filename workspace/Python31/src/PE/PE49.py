import time
import math

def permutations(given):
    if len(given) <=1:
        yield given
    else:
        for permutation in permutations(given[1:]):
            for i in range(len(permutation)+1):
                yield permutation[:i] + given[0:1] + permutation[i:]
                
def primeCheckList(size):
    isPrime = [True for x in range(size)]
    isPrime[0] = False
    isPrime[1] = False

    for x in range(2, int(math.sqrt(size))):
        if isPrime[x]:
            for y in range(x*x,size, x):
                isPrime[y] = False
    return isPrime
                
def solve():
    isPrime = primeCheckList(10000)
    used = [False for x in range(10000)]
    
    for x in range(1000, 10000):
        if not used[x]:
            perms=[]
            for perm in permutations(str(x)):
                if perm[0]!='0' and isPrime[int(perm)] and not perm in perms:
                    perms.append(perm)
                used[int(perm)]=True
            if len(perms)>=3 and not '1487' in perms:
                perms.sort()
                for x in range(len(perms)):
                    for y in range(x+1, len(perms)):
                        if str(int((int(perms[x])+int(perms[y]))/2)) in perms:
                            return '%s%s%s'%(perms[x],int((int(perms[x])+int(perms[y]))/2),perms[y])
    
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
    Answer: 296962999629
    Time elapsed: 0.14 seconds
"""
