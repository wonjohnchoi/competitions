'''
@author Wonjohn Choi
@problemTitle NestedRandomness
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=6527&pm=3510
@lang Python 31
'''

def probability(N, nestings, target):
    props = [0]*N+[1]
    
    for i in range(nestings):
        for j in range(len(props)):
            if props[j]>0:
                for k in range(j):
                    props[k]+=props[j]/j
                props[j]=0
    return props[target]
    
print(probability(5,2,1))#0.216666666667
print(probability(10,4,0))#0.19942680776
print(probability(1000,10,990))#1.04617763971e-30

