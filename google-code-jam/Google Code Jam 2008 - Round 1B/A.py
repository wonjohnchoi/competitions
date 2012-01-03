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

N = INT()

from itertools import product
for _ in range(N):
    n, A, B, C, D, x0, y0, M = (INT() for __ in range(8))
    cnt = [[0] * 3 for __ in range(3)]
    x, y = x0, y0
    for __ in range(n):
        cnt[x % 3][y % 3] += 1
        x = (A * x + B) % M
        y = (C * y + D) % M
    ret = 0
    for x, y in product(range(3), repeat = 2):
        ret += cnt[x][y] * (cnt[x][y] - 1) * (cnt[x][y] - 2) / 6
    for x1, x2, x3, y1, y2, y3 in product(range(3), repeat = 6):
        if (x1, y1) < (x2, y2) < (x3, y3):
            if (x1 + x2 + x3) % 3 == 0 and (y1 + y2 + y3) % 3 == 0:
                ret += cnt[x1][y1] * cnt[x2][y2] * cnt[x3][y3]
    OUT('Case #%d: %d' % (_ + 1, ret))
