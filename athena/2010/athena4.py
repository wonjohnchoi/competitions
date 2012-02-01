'''
For a prime number p the set of co-primes less than or equal to it is given by {1,2,3,4,...p-1} .

We define f(x,p) 0<x<p = 1 if and only if all the numbers from 1 to p-1 can be written as a power of x in modulo-p arithmetic . 

Let n be the largest 12-digit prime number . Find the product of all integers j less than n such that f(j,n)=1 in the form of modula n

'''

'''
Rearranging the problem tells us that finding the product of primitive root in modula n will give us answer.
Gauss proved that for any prime number p (with the sole exception of 3) the product of its primitive roots is ≡ 1 (mod p).
Therefore, answer % n ==1.
Since questions asks for answer modula n, 1 is answer
'''
