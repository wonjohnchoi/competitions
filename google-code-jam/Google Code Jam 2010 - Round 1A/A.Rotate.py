'''
Problem
@YEAR Google Code Jam 2010: Round 1A
@ID A
@NAME Rotate 
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def gravity(data):
    n = len(data)
    
    for i in range(n):
        newLine = ''
        for char in data[i]:
            if char!='.':
                newLine+=char
        
        while len(newLine)!=n:
            newLine = '.' + newLine
        
        data[i] = newLine

#pos -> (x,y)
def search(pos, data, k):
    n = len(data)
    char = data[pos[0]][pos[1]]
    if char == '.':
        return None
        
    for dir in [(1,1), (1,0), (1,-1), (0,1)]:
        if pos[0]+dir[0]*(k-1)<n and pos[1]+dir[1]*(k-1)<n:
            for i in range(1, k):
                if data[pos[0]+dir[0]*i][pos[1]+dir[1]*i]!=char:
                    break
            else:
                return char
    return None

def solve():
    fin = open('A.in', 'r')
    fout = open('A.out', 'w')
    
    t = int(next(fin))
    
    for i in range(t):
        n, k = map(int, next(fin).split(' '))
        
        oldData = []
        
        for j in range(n):
            oldData.append(next(fin).strip())
        
        newData = list(reversed(oldData)) #access with (x,y)
        gravity(newData)
#        print('\n'.join(newData))
        hasBlue = hasRed = False
        for x in range(n):
            for y in range(n):
                result = search((x,y), newData, k)
                if result == 'R':
                    hasRed = True
                elif result == 'B':
                    hasBlue = True
                    
        answer = None
        if hasBlue and hasRed:
            answer = 'Both'
        elif hasBlue:
            answer = 'Blue'
        elif hasRed:
            answer = 'Red'
        else:
            answer = 'Neither'
        fout.write('Case #%d: %s\n'%(i+1, answer))
    fin.close()
    fout.close()
solve()