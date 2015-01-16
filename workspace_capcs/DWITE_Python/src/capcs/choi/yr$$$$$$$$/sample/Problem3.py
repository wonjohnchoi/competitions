questionNumber = 3

def tokenize():
    for line in open('data%s.txt'%questionNumber,'r'):
        for token in line.split():
            yield token

tokens = tokenize()
def nextToken(): return next(tokens)


#=============
for x in range(10):
    print(nextToken())