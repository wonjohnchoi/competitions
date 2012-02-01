'''
@author Wonjohn Choi
@problemTitle Wine Tasting
@problemLink http://www.facebook.com/hackercup/problems.php/?round=123802894356576
@lang Python 31
'''
inFile = open("wine_tasting.txt", "r")
outFile = open("wine_tasting_output.txt","w")
nCase = int(next(inFile))

import math
for line in inFile:
    m, n = tuple(map(int,line.split(' ')))
    #m-n wrong
    wrongWays = [0]*(m-n+1)
    wrongWays[0] = 1
    for i in range(1,len(wrongWays)):
        wrongWays[i]=math.factorial(i)
        for j in range(1, i+1):
            wrongWays[i]-=math.factorial(i)/math.factorial(j)/math.factorial(i-j)*wrongWays[i-j]

    answer = 0
    for i in range(n,m+1):
        answer+=math.factorial(m)/math.factorial(i)/math.factorial(m-i) * wrongWays[m-i]
    outFile.write(str(int(answer%1051962371)))
    outFile.write('\n')
outFile.close()
inFile.close()

