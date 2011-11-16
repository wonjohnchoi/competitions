'''
@author Wonjohn Choi
@problemTitle ChessMetric
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4482&pm=1592
@lang Python 3
'''

moves = [[0,1],[1,0],[-1,0],[0,-1],[-1,-1],[1,1],[1,-1],[-1,1],[1,2],[2,1],[-1,2],[1,-2],[2,-1],[-2,1],[-2,-1],[-1,-2]]

def fillWays(metric, start, cur, numMoves):
    nWays = 0
    if numMoves==0:
        if start==cur:
            nWays = 1
    elif cur[0]<0 or cur[1]<0 or cur[0]>=len(metric) or cur[1]>=len(metric):
        pass
    else:
        piece = metric[cur[0]][cur[1]]
        if len(piece)<numMoves:
            piece+=[-1]*(numMoves-len(piece))

        if piece[numMoves-1]==-1:
            piece[numMoves-1] = 0
            for move in moves:
                piece[numMoves-1]+=fillWays(metric, start, [cur[0]+move[0],cur[1]+move[1]], numMoves-1)

        nWays = piece[numMoves-1]
    return nWays
            
def howMany(size, start, end, numMoves):
    metric = [[[] for j in range(size)] for i in range(size)]
    return fillWays(metric, start, end, numMoves)

    
    
print(howMany(100, [0,0], [0,99], 50))
#Output: 243097320072600
