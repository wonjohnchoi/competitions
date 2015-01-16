fi = open('C.in')
fo = open('C.out', 'w')
def line():
    return fi.next().strip()
n = int(line())
fair_square = []

def is_pal(s):
    if len(s) in (0, 1):
        return True
    return s[0] == s[-1] and is_pal(s[1:-1])
for i in range(1, 10**7 + 1):
    if is_pal(str(i)) and is_pal(str(i * i)):
        fair_square.append(i * i)
for i in range(1, n + 1):
    a, b = map(int, line().split())
    i1 = 0
    i2 = len(fair_square) - 1
    cnt = 0
    while i1 < len(fair_square) and fair_square[i1] < a: i1 += 1 
    while i2 >= 0 and fair_square[i2] > b: i2 -= 1
    if i1 != len(fair_square) and i2 != 0:
        cnt = i2 - i1 + 1
    '''
    idx = 0
    cnt = 0
    for j in range(a, int((b + 1) ** 0.5)):
        while idx < len(fair_square) and j > fair_square[idx]:
            idx += 1
        if idx < len(fair_square) and j == fair_square[idx]:
            idx += 1
            cnt += 1
    '''        
    fo.write('Case #%d: %d\n' %(i, cnt))
