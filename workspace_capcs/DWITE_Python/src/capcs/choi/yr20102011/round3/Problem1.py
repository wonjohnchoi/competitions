questionNumber = 1

def tokenize():
    for line in open('data%s.txt'%questionNumber,'r'):
        for token in line.split():
            yield token

tokens = tokenize()
def nextToken(): return next(tokens)


#=============
for i in range(5):
    height = int(nextToken())
    width = (height-1,height)[height%2]
    map = [[['.']*width] for i in range(height)]
    
    middle = int(height/2)
    print(height, middle)
    maxError = 0
    
    for i in range(height):
        for error in range(0, maxError+1):
            print(error, i)
            map[i][middle-error]='*'
            map[i][middle+error]='*'
        if height%2==1:
            maxError+=1
    
    print('\n'.join([''.join(line) for line in map]))
    
