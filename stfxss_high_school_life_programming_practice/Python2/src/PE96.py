'''
Created on 2010. 6. 19.

@author: user
'''

from sudoku import sudoku

f = open('sudoku.txt', 'r')
sum=0
for x in range(50):
    f.readline()
    map = []
    for y in range(9):
        map.append(f.readline().strip())
    
    result = sudoku(map)
    result.check()
    result.check(1)
    result.check(2)
    sum+=int(result.simple_text()[0:3])
    
print sum