'''
Created on 2010. 6. 19.

@author: Wonjohn Choi
'''

class fraction():
    def __init__(self, numerator, denominator):
        gcd = self.gcd(numerator, denominator)
        self.numerator=numerator/gcd
        self.denominator=denominator/gcd
    def gcd(self, a, b):
        a=abs(a);b=abs(b)
        while b > 0: a,b = b,a%b
        return a
    