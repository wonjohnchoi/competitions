'''
Created on 2010. 6. 17.

@author: Wonjohn Choi
'''

import math

def isTriangular(x):
    idx = (math.sqrt(1+8*x)-1)/2
    return idx == int(idx)

def isPentagonal(x):
    idx = (1+ math.sqrt(1+24*x))/6
    return idx == int(idx)

def isHexagonal(x):
    idx = (math.sqrt(1+8*x)+1)/4
    return idx == int(idx)

def getTriangular(idx):
    return (idx*idx+idx)/2

def getPentagonal(idx):
    return (3*idx*idx-idx)/2

def getHexagonal(idx):
    return idx*idx*2-idx

def isLychrel(i, depth):
    if depth==0: return True
    if str(i)==str(i)[::-1]: return False
    return isLychrel(i+int(str(i)[::-1]), depth-1)

    