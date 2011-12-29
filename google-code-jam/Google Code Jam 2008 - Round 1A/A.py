problem = 'A'

input = open('%s.in'%problem, 'r')
def tokenize(file):
    for line in file:
        for token in line.split(' '):
            yield token
output = open('%s.out'%problem, 'w')
tokens = tokenize(input)

OUT = lambda s: output.write(str(s)+'\n')
INT = lambda : int(next(tokens))

t = INT()
for i in range(t):
    n = INT()
    val = sum(x * y for x, y in zip(sorted(INT() for i in range(n)), \
        sorted((INT() for i in range(n)), reverse = True)))
    OUT('Case #%d: %d' %(i+1, val))
