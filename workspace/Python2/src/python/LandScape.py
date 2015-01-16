count=input("# of inputs")
width=0
maxHeight=-1
height=[]
background=[]

while count!=0 and width<60:
    height.append(input())
    count-=1
    if(height[-1]==0):
        width+=1
    else:
        width+=height[-1]*2-1
    if(maxHeight<height[-1]):
        maxHeight=height[-1]

for i in range(maxHeight):
    background.append(['.' for i in range(width)])

def printArray():
    for i in range(maxHeight):
        for j in range(width):
            print background[i][j],
        print
    
def createMountain(pos, hei):
    for i in range(hei):
        for j in range(maxHeight-hei+i, maxHeight):
            background[maxHeight-hei+i:][pos-i]='x'
            background[maxHeight-hei+i:][pos+i]='x'
pos=-1

for i in range(len(height)):
    if height[i]==0:
        pos+=1
    else:
        pos+=height[i]
        createMountain(pos,height[i])
        pos+=height[i]-1

printArray()
    



