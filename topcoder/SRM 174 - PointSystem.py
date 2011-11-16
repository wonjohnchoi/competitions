'''
@author Wonjohn Choi
@problemTitle PointSystem
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4675&pm=1849
@lang Python 31
'''

odds = [-1]*800

def odw(pw, pwb, ratio, count, diff):
    #if count>20:return
    #print(count,diff)
    if count>=800:
        return 0
    elif pw<=count:
        if odds[diff]!=-1:
            return odds[diff]
        elif diff>=pwb:
            #print('debug1',count,diff)
            return 1
        elif diff<=-pwb:
            #print('debug2',count,diff)
            return 0
        else:
            #print('debug3',count,diff)
            odds[diff] = odw(pw,pwb,ratio,count+1,diff+1)*ratio + odw(pw,pwb,ratio,count+1,diff-1)*(1-ratio)
            return odds[diff]
    else:
        #print('debug4',count,diff)
        return odw(pw,pwb,ratio,count+1,diff+1)*ratio + odw(pw,pwb,ratio,count+1,diff-1)*(1-ratio) 

def oddsOfWinning(pointsToWin, pointsToWinBy, skill):
    return odw(pointsToWin, pointsToWinBy, skill/100, 0, 0)
    
print(oddsOfWinning(2,1,40))
print(oddsOfWinning(4,5,50))