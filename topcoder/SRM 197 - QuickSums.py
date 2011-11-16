'''
@author Wonjohn Choi
@problemTitle QuickSums
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=5072&pm=2829
@lang Python 31
'''

def minSums(numbers, sum):
    vals = [[] for i in range(len(numbers)+1)]
    vals[0].append([0,-1])

    for i in range(0,len(numbers)):
        for val, count in vals[i]:
            for j in range(i+1, len(numbers)+1):
                newVal = val + int(numbers[i:j])
                newCount = count + 1
                if newVal<=sum:
                    for k in range(len(vals[j])):
                        if vals[j][k][0] == newVal:
                            if vals[j][k][1] > newCount:
                                vals[j][k][1] = newCount            
                            break
                    else:
                        vals[j].append([newVal, newCount])
    count = 9999
    for val in vals[len(vals)-1]:
        if val[0]==sum:
            count = min(count, val[1])

    if count == 9999:
        count = -1

    return count
print(minSums("9230560001", 71))
