questionNumber = 2

def tokenize():
    for line in open('data%s.txt'%questionNumber,'r'):
        for token in line.split():
            yield token

tokens = tokenize()
def nextToken(): return next(tokens)

out = open('OUT2.txt','w')
#=============
primes = [True] * 50000

for i in range(2,int(len(primes)**0.5)):
    if primes[i]:
        for j in range(i*i, int(len(primes)/i), i):
            primes[j] = False
            
for i in range(5):
    input = int(nextToken())
    cur = 2
    while True:
        if primes[cur]:
            input-=1
            if input==0:
                break
        cur+=1
        
    input= cur
    cur=2
    while True:
        if primes[cur]:
            input-=1
            if input==0:
                break
        cur+=1
    out.write(str(cur))
    out.write('\n')
out.close()

