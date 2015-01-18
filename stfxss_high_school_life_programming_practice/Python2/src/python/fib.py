'''
Created on 2010. 3. 10.

@author: Wonjohn Choi
'''

def fib(pre,cur,n):
    if n==1:
        return pre
    if n==2:
        return cur
    n-=1
    return fib(cur,pre+cur,n)
print "301th fibonacci number is {0}".format(fib(0,1,301)) 
#222232244629420445529739893461909967206666939096499764990979600
