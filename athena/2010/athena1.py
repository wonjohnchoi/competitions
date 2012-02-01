x=1000
val = [[0 for i in range(x+1)] for i in range(x+1)]

val[0][0]=1

for i in range(x+1):
    for j in range(x+1):
        if i>=j:
            if 1<=i and (i-1)>=j:
                val[i][j]+=val[i-1][j]
            if 1<=j and i>=(j-1):
                val[i][j]+=val[i][j-1]

print(val[x][x])
