'''
Created on 2010. 6. 20.

@author: Wonjohn Choi
'''

class sudoku():
    def __init__(self, map, isEmpty):
        '''
        map is a list consists of lines
        isEmpty indicates the empty space letter
        '''
        self.isEmpty=isEmpty
        self.candi=[[list(range(1, 10)) for y in range(9)] for x in range(9)]
        for x in range(9):
            for y in range(9):
                if map[x][y]!=isEmpty:
                    self.candi[x][y]=[int(map[x][y])]
                    
    def check(self, x):
        if x==0:
            self.directSolve()
                    
    def directSolve(self):
        new=[[True for x in range(9)] for y in range(9)]
        done=False
        while not done:
            done=True
            for x in range(9):
                for y in range(9):
                    if new[x][y] and len(self.candi[x][y])==1:
                        self.updateNeighbor(x,y)
                        new[x][y]=False
                        done=False
    
    def updateNeighbor(self, x, y):
        for z in range(9):
            if z!=x and self.candi[x][y][0] in self.candi[z][y]:
                self.candi[z][y].remove(self.candi[x][y][0])
            if z!=y and self.candi[x][y][0] in self.candi[x][z]:
                self.candi[x][z].remove(self.candi[x][y][0])
    def __str__(self):
        ans=''
        for x in range(9):
            for y in range(9):
                if len(self.candi[x][y])==1:
                    ans+=str(self.candi[x][y][0])
                else:
                    ans+=self.isEmpty
            ans+='\n'
        return ans.strip()   
                        
                            
                        
        
if __name__=='__main__':
    puzzle = sudoku(
'''
003020600
900305001
001806400
008102900
700000008
006708200
002609500
800203009
005010300
'''.strip().split('\n'), '0')
    print(puzzle)
    print()
    puzzle.check(0)
    print(puzzle)