'''
@author Wonjohn Choi
@problemTitle QuizShow
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=5869&pm=2989
@lang Python 31
'''

def wager(scores, wager1, wager2):
    if scores[0]*2<=min(scores[1]-wager1, scores[2]-wager2): #worst case
        return 0
    
    case = [-1,1]
    maxPoint = -1
    wager = -1
    for wager0 in range(0, scores[0]+1):
        point = 0
        for c0 in case:         #win or loss for 1st player
            for c1 in case:     #win or loss for 2nd player
                for c2 in case: #win or loss for 3th player
                    results = [scores[0]+c0*wager0, scores[1]+c1*wager1, scores[2]+c2*wager2]
                    if results[0]>results[1] and results[0]>results[2]:
                        point+=1
        #print("%d %d"%(point, wager0))
        if point>maxPoint:
            maxPoint = point
            wager = wager0
    return wager

print(wager([100, 100, 100], 25, 75)) #76
print(wager([10, 50, 60], 30, 41)) #0
print(wager([10, 50, 60], 31, 41)) #10
print(wager([ 2, 2, 12], 0, 10)) #1
print(wager([10000, 10000, 10000], 9998, 9997)) #9999
print(wager([5824, 4952, 6230], 364, 287)) #694