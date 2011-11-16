'''
Problem
@CONTEST Google Code Jam 2011: Round 1B
@ID C
@NAME
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT 
'''

rooms = []
def findRoom(connect, room):
    n = len(connect)
    for i in range(n):
        if connect[room[-1]][i]:
            if i==room[0] and len(room)>2:
                r = sorted(room)
                #print(r)
                if not r in rooms:
                    rooms.append(r)
                    #print(rooms)
            elif i in room:
                pass
            else:
                room.append(i)
                findRoom(connect, room)
                room.pop()
                
def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\C.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\C.in','r')
    t = int(next(fin).strip())
    for i in range(t):
        n,m = map(int, next(fin).strip().split(' '))
        connect = [[False for j in range(n)] for k in range(n)]
        uu = list(map(lambda x: int(x)-1, next(fin).strip().split(' ')))
        vv = list(map(lambda x: int(x)-1, next(fin).strip().split(' ')))
        for j in range(m):
            connect[uu[j]][vv[j]] = True
            connect[vv[j]][uu[j]] = True
        for j in range(n-1):
            connect[j][j+1] = True
            connect[j+1][j] = True
        connect[n-1][0] = True
        connect[0][n-1] = True
        
        for j in range(n):
            findRoom(connect, [j])
            
        newRooms = []
        for j in range(len(rooms)):
            bbad = False
            for k in range(len(rooms)):
                if j!=k:
                    for c in rooms[k]:
                        if not c in rooms[j]:
                            break
                    else:
                        bbad = True
            if not bbad:
                newRooms.append(rooms[j])
        print(newRooms)
                        
                    
        print(rooms)
        
        
solve()