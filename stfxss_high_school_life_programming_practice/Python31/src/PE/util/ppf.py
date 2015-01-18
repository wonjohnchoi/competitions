'''
Created on 2010. 6. 17.

@author: Wonjohn Choi
'''

import math, random

class prime():
    def __init__(self, size=None):
        if size==None:
            self.size=-1
        else:
            self.sieve=self.sieveGen(size)
            self.size=size

    def sieveGen(self,size):
        isPrime = [False]*2+[True]*(size-2)
        for i in range(2, int(math.sqrt(size))+1):
            if isPrime[i]:
                isPrime[i*i:size:i] = [False]*((size-1)//i-i+1)
        return isPrime
    
    def sievePrimeGen(self, start, end):
        return filter(lambda x:self.sieve[x], range(start, end))
    
    def primeFactors(self, n):
        i = 2
        limit = math.sqrt(n)  
        while i <= limit:  
            if n % i == 0:  
                count=0
                while n%i==0:
                    count+=1
                    n/=i
                yield (i, count)    
                limit = math.sqrt(n)  
            i += 1  
        if n > 1:  
            yield (int(n), 1)  
    
    def isPrime(self, n):
        if self.size>n: return self.sieve[n]
        if n<2: return False
        root=int(math.sqrt(n))+1
        if self.size*self.size>n:
            return all(n%x!=0 for x in self.sievePrimeGen(2, root))
        else:
            if n==2: return True
            if n&1==0: return False
            return all(n%x!=0 for x in range(3, root,2))
               
    
    def isPrime2(self, n):
        '''
        find whether it's prime using number's factor form
        @deprecated: slow
        '''
        if n<2: return False
        return all(x==n for x,y in self.primeFactors(n))
    
    def primeGen(self, counter, start=2):
        if counter<=0 or start!=int(start): raise ValueError
        
        cur=3
        base  = [2,3,5]

        if start<=2: 
            yield 2
            counter-=1
        else: 
            cur=start+(start+1)%2
            
        
        while counter>0:
            if cur in base:
                counter-=1
                yield cur
            else:  
                if not [m for m in base if (cur%60) % m == 0]:
                    if self.isPrime(cur):
                        counter-=1
                        yield cur
            cur+=2
    
    def nextPrime(self, n):
        return next((self.primeGen(1, n),self.primeGen(1, n+1))[self.isPrime(n)])
    
    def witness(self, a, n):
        '''
        developed by miller_rabin
        '''
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
    
    
    def isProbablyPrime(self, n, re=0):
        '''
        it is ok to test 2, 7, 61 for 32 bits <2**32
        only use for huge number over 1355000
        modified version of miller_rabin prime
        '''
        if n<2: return False
        if n>2 and not self.witness(2,n): return False
        if n>7 and not self.witness(7,n): return False
        if n>61 and not self.witness(61,n): return False
        
        return all(self.witness(random.randrange(1,n), n) for i in range(re))
            
class perfect():
    def perfectGen(self, counter, start=0):
        cur  = 0; p=prime()
        while counter!=0:
            cur = p.nextPrime(cur)

            if p.isPrime(2**cur - 1):
                pN =(2**(cur-1))*(2**cur - 1)
                if pN >= start:
                    counter -= 1
                    yield pN
    
    def isPerfect(self, num):
        return next(self.perfectGen(1,num))==num
    
    
    def nextPerfect(self, num):
        return next((self.PerfectGen(1,num),self.PerfectGen(1,num+1))[self.isPerfect(num)])          

class fibonacci():
    def fibonacciGen(self, counter, start=0, init1=0,init2=1):
        fib = [init1,init2]
        while counter!=0:
            if fib[0] >= start:
                counter-=1
                yield fib[0]
            fib.append(fib[len(fib)-1] + fib[len(fib)-2])
            fib.pop(0)

    def isFibonacci(self, num):
        return next(self.fibonacciGen(1,num))==num

    
    def nextFibonacci(self, num):
        return next((self.fibonacciGen(1, num),self.fibonacciGen(1,num+1))[self.isFibonacci(num)])



if __name__=='__main__':
    from time import time
    import math
    p=prime()
    
    print(p.isPrime(11))
    '''
    size=100000
    p=prime()
    '''
    '''
    s = time()
    for i in range(size):
        p.isPrime(i)
    print('brute',time()-s)
    '''
    '''
    s=time()
    for i in range(size):
        p.isPrime2(i)
    print('factor:',time()-s)
    '''
    '''
    s=time()
    for i in range(size):
        p.isProbablyPrime(i)
    print('maybe:',time()-s)    
    '''
    '''
    s=time()
    p=prime(size)
    for i in range(size):
        print(p.isPrime(i))
    print('sieve:',time()-s)
    '''