# python 2

fi = open('A.in')
fo = open('A.out', 'w')
def line():
    return fi.next().strip() 
def wins(c, maze):
    t = 'T'
    nd1 = 0
    nd2 = 0

    for i in range(4):
        if maze[i][i] in (c, t):
            nd1 += 1
        elif maze[i][3-i] in (c, t):
            nd2 += 1
        nr = 0
        nc = 0
        for j in range(4):
            if maze[i][j] in (c, t):
                nr += 1
            if maze[j][i] in (c, t):
                nc += 1
        if nr == 4 or nc == 4:
            return True
    if nd1 == 4 or nd2 == 4:
        return True
    return False

n = int(line())

for i in range(1, n + 1):
    maze = [line() for j in range(4)]
    ans = 'Case #%d: %s\n' %(i, '%s')
    done = True
    for j in range(4):
        if '.' in maze[j]:
            done = False
            break
    if wins('X', maze):
        ans %= 'X won'
    elif wins('O', maze):
        ans %= 'O won'
    elif not done:
        ans %= 'Game has not completed'
    else:
        ans %= 'Draw'
    fo.write(ans)
    if i != n: fi.next()
fo.close()
fi.close()
