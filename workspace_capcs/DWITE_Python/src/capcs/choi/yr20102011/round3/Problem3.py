questionNumber = 3

def tokenize():
    for line in open('data%s.txt'%questionNumber,'r'):
        for token in line.split():
            yield token

tokens = tokenize()
def nextToken(): return next(tokens)
out = open('OUT3.txt','w')

#=============

for i in range(5):
    input = int(nextToken())
    string = str(bin(input))
    counter = 0
    for c in string:
        if c=='1':
            counter+=1
    other = -1
    
    while other!=counter:
        other=0
        input+=1
        string = str(bin(input))
        for c in string:
            if c=='1':
                other+=1
    out.write(str(input))
    out.write('\n')
    
out.close()