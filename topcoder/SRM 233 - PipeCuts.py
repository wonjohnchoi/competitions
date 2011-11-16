'''
@author Wonjohn Choi
@problemTitle PipeCuts
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=6532&pm=3994
@lang Python 31
'''
def probability(weldLocations, L):
    weldLocations.sort()
    #print(weldLocations)
    size = len(weldLocations)
    
    total = size*(size-1)/2 #size choose 2 (sizeC2)
    count = 0
    
    for i in range(size):
        for j in range(i+1, size):
            #print(weldLocations[i], weldLocations[j])
            if (weldLocations[i]>L or (weldLocations[j]-weldLocations[i])>L or (100-weldLocations[j])>L):
                count+=1
                
    return round(count/total, 9)
    
print(probability([25,50,75], 25))#1.0
print(probability([25,50,75], 50))#0.0
print(probability([25,50,75], 24))#1.0
print(probability([99, 88, 77, 66, 55, 44, 33, 22, 11], 50))#0.722222222222
