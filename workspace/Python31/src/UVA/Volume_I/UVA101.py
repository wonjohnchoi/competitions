'''
Created on 2010. 6. 27.
@Titel: Volume I-UVA 101
@URL: http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=37
@Idea
    The wording is confusing...
    So simply,
    move a onto b - move a to b returning all blocks above them
    move a over b - move a to b returning all blocks above a
    pile a onto b - move a to b returning all blocks above b
    pile a over b - move a to b returning no blocks
    quit - finish
    commands that a and b are in the same stack of blocks - ignore
@author Wonjohn Choi
'''

file=open('input.txt')
blocks=[[x] for x in range(int(next(file)))]

def blockOf(x): 
    for idx,block in enumerate(blocks):
        if x in block:
            return idx
        
def returnAbove(x, idx):
    while blocks[idx][-1]!=x:
        y=blocks[idx].pop(-1)
        blocks[y].append(y)
        
def moveOnto(a,b):
    returnAbove(a, blockOf(a))
    returnAbove(b, blockOf(b))
    blocks[blockOf(b)].append(blocks[blockOf(a)].pop(-1))

def moveOver(a,b):
    returnAbove(a, blockOf(a))
    blocks[blockOf(b)].append(blocks[blockOf(a)].pop(-1))

def pileOnto(a,b):
    blockA,blockB=blocks[blockOf(a)],blocks[blockOf(b)]
    returnAbove(b, blockB)
    blockB+=blockA[blockA.index(a):]
    blocks[blockOf(a)]=blockA[:blockA.index(a)]

def pileOver(a,b):
    blockA,blockB=blocks[blockOf(a)],blocks[blockOf(b)]
    blockB+=blockA[blockA.index(a):]
    blocks[blockOf(a)]=blockA[:blockA.index(a)]

for line in file:
    if line=='quit': break
    line=line.split()
    line[1],line[3]=int(line[1]),int(line[3])
    
    if blockOf(line[1]) is blockOf(line[3]): continue
    
    if line[0]=='move':
        if line[2]=='onto':
            moveOnto(line[1],line[3])
        else:
            moveOver(line[1],line[3])
    else:
        if line[2]=='onto':
            pileOnto(line[1],line[3])
        else:
            pileOver(line[1],line[3])

for idx,block in enumerate(blocks):
    print(idx,': ',' '.join(map(str,block)),sep='')