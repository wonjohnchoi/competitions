'''
@author Wonjohn Choi
@problemTitle ZigZag
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4493&pm=1259
@lang Python 3
'''
def longestZigZag(sequence):
    lengths =[[1,1]]+[[0,0] for i in range(len(sequence)-1)] #[nextIsUp, nextIsDown]
    
    for i in range(len(sequence)):
        for j in range(i+1, len(sequence)):
            if sequence[j]>sequence[i]:
                if lengths[j][1]<lengths[i][0]+1:
                    lengths[j][1] = lengths[i][0]+1
            elif sequence[j]<sequence[i]:
                if lengths[j][0]<lengths[i][1]+1:
                    lengths[j][0] = lengths[i][1]+1
    maxLength = 0
    for twoLength in lengths:
        for length in twoLength:
            if maxLength<length:
                maxLength = length
    return maxLength

print(longestZigZag([374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
249, 22, 176, 279, 23, 22, 617, 462, 459, 244]))
