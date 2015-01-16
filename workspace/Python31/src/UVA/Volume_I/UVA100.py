'''
Created on 2010. 6. 27.
@Titel: Volume I-UVA 100
@URL: http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
@Idea
    Approaching with dynamic solving
    Store results we get to a list
    When we need to check some value, if we already stored, just use it
@author Wonjohn Choi
'''

#basic list filled with 0; we are going to store values we get here.
cyclicLen=[0]*1000000

def getCyclicLen(n):
    if n==1: return 1
    result=-1
    if n<1000000:
        if cyclicLen[n]!=0:
            return cyclicLen[n]
        else:
            result=getCyclicLen(((n<<1)+n+1, n>>1)[(n+1)%2])+1
            cyclicLen[n]=result
    return result

while True:
    line=list(map(int,input().split()))
    print(line[0],line[1],max(getCyclicLen(x) for x in range(line[0], line[1])))
    
    
    
