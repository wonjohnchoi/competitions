problem = 'B'

input = open('%s.in'%problem, 'r')
def tokenize(file):
    for line in file:
        for token in line.split(' '):
            yield token
output = open('%s.out'%problem, 'w')
tokens = tokenize(input)

OUT = lambda s: output.write(str(s)+'\n')
INT = lambda : int(next(tokens))


from math import sqrt, pow
t = INT()
for i in range(t):
    n = INT()
    val = int(pow(3 + sqrt(5), n) % 1000)
    OUT('Case #%d: %d' %(i+1, val))
