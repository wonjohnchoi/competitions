from copy import deepcopy

def mostStars(level):
    level = [[int(ch) for ch in each] for each in level]
    r, c = len(level), len(level[0])

    if r<=3 or c<=3: return sum([sum(each) for each in level]) #special case
    
    accum = [[[-1]*c for j in range(c)] for i in range(c)]
    accum[0][0][0] = level[0][0]
    
    for i in range(r):
        cur = [[[-1]*c for j in range(c)] for i in range(c)]
        for j in range(c):
            for k in range(j,c):
                for l in range(k,c):
                    if accum[j][k][l]!=-1:
                        '''
                        for j2 in range(j,k):
                            cur[j2][k][l] = max(cur[j2][k][l], accum[j][k][l]+sum(level[i][j+1:j2+1]))
                        for k2 in range(k,l):
                            cur[j][k2][l] = max(cur[j][k2][l], accum[j][k][l]+sum(level[i][k+1:k2+1]))
                        for l2 in range(l,c):
                            cur[j][k][l2] = max(cur[j][k][l2], accum[j][k][l]+sum(level[i][l+1:l2+1]))
                        '''
                        for j2 in range(j,k+1):
                            for k2 in range(k,l+1):
                                for l2 in range(l,c):
                                    extra = 0
                                    extra+=sum(level[i][j+1:j2+1])
                                    if j2 == k: extra-=level[i][k] #avoid duplicate counting
                                    
                                    extra+=sum(level[i][k+1:k2+1])
                                    if k2 == l: extra-=level[i][l] #avoid duplicate counting
                                    
                                    extra+=sum(level[i][l+1:l2+1]) 
                                    
                                    cur[j2][k2][l2] = max(cur[j2][k2][l2], accum[j][k][l]+extra)
                                    
                                    
        if i<r-1:     
            for j in range(c):
                for k in range(j,c):
                    for l in range(k,c):
                        if cur[j][k][l]!=-1:
                            cur[j][k][l]+=level[i+1][j]+level[i+1][k]+level[i+1][l]
        accum = cur

    return accum[c-1][c-1][c-1]

print(mostStars(["01",
 "11"])) #3    
    
print(mostStars(["0999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"])) #450

print(mostStars(["012"
,"012"
,"012"
,"012"
,"012"
,"012"
,"012"])) #21

print(mostStars(["0123456789",
 "1123456789",
 "2223456789",
 "3333456789",
 "4444456789",
 "5555556789",
 "6666666789",
 "7777777789",
 "8888888889",
 "9999999999"])) #335

print(mostStars(["0215238487", "1054982143", "4535280178", "3454052620", "5169642841", "3523683821", "5901796641", "9947358419", "9034609169", "0375085579"])) #277

