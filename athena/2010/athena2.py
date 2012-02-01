x=100
val = [[0 for i in range(x+1)] for i in range(x+1)]

val[0][0]=1

for i in range(x+1):
    for j in range(x+1):
        if 1<=i:
            val[i][j]+=val[i-1][j]
        if 1<=j:
            val[i][j]+=val[i][j-1]
        if 1<=i and 1<=j:
            val[i][j]+=val[i-1][j-1]

print(val[x][x]-1)
