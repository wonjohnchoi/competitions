'''
@author Wonjohn Choi
@problemTitle Surveyor
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=5000&pm=1359
@lang Python 31
'''
def area(direction, length):
    vectors = []
    cx, cy = 0, 0

    i=0
    while i<len(direction):
        vectors.append([cx,cy])
        cDir = direction[i]
        cLen = length[i]
        while (i+1)<len(direction) and direction[i+1] == cDir:
            i+=1
            cLen+=length[i]
        if cDir == 'N': cy-=cLen
        elif cDir == 'S': cy+=cLen
        elif cDir == 'E': cx+=cLen
        elif cDir == 'W': cx-=cLen
        else: print('INPUT ERROR'); exit(0)
        i+=1
        
    #computed with cross product of adjacent vectors
    area = int(abs(sum([vectors[i][0]*vectors[i+1][1]-vectors[i+1][0]*vectors[i][1] for i in range(1, len(vectors)-1)]))/2)
    return area    

#test
if __name__ == '__main__':
    print(area("NWWSE", [10,3,7,10,10])) #100
    print(area("NESWNWSW", [20,200,30,100,20,30,10,70])) #4700
    print(area("EEEEEEEEEEEESSSSSSSSSSSSSWWWWWWWWWWWWNNNNNNNNNNNNN", [1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000])) #156000000
