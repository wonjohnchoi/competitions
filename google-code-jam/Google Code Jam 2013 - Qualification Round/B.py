fi = open('B.in')
fo = open('B.out', 'w')
def line():
    return fi.next().strip()
n = int(line())
def is_good(maze, r, c):
    for i in range(r):
        for j in range(c):
            c1 = r1 = True
            for k in range(r):
                if maze[k][j] > maze[i][j]:
                    c1 = False
            for k in range(c):
                if maze[i][k] > maze[i][j]:
                    r1 = False
            if not c1 and not r1:
                return False
    return True
for i in range(1, n + 1):
    r, c = map(int, line().split())
    maze = []
    for j in range(r):
        maze.append(line().split())
    fo.write('Case #%d: %s\n' %(i, ('NO', 'YES')[int(is_good(maze, r, c))]))

fo.close()
fi.close()
