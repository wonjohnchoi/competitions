'''
Created on 2010. 3. 10.

@author: Wonjohn Choi
'''

def fib(pre,cur,n):
    if n==0:
        return pre
    if n==1:
        return cur
    n-=1
    return fib(cur,pre+cur,n)

def factoring(n):
    factors=[]
    powers=[]
    div=2;
    while(n!=1):
        if n%div == 0:
            factors.append(div)
            powers.append(0)
            while(n%div == 0):
                n/=div
                powers[-1]+=1
        else:
            div+=1
    return factors, powers

num=fib(0,1,31)
luc=fib(2,1,32)
fac, pow=factoring(luc)
for i in range(len(fac)-1):
    print fac[i],'^', 
    if(pow[i]!=1):
        pow[i], '*',
print fac[-1],
if(pow[-1]!=1):
    print '^',pow[-1]
    

    

        