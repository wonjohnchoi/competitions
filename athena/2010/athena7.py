'''
Question 7
Points 100


Solve the following set of equations .
16a + 23b + 12c + 34d -2e -37f + 109g -141h + 139i + 149j + 29k + 12l + 131m = 74608 
13a + 31b -5c + 17d + 29e -67f -16g -101h -7i -201j + 32k + 17l + 171m = -4194 
81a -12b -5c + 17d -9e + 17f + 39g + 49h -67i -7j -41k + 13l + 121m = -15793 
91a -43b + 17c + 19d + 17e + 23f + 97g + 101h -101i + 173j -12k + 137l + 217m = -16661 
71a + 47b + 37c + 37d + 41e -37f -67g -17h -2i -6j + 41k -39l -12m = -6862 
61a + 13b + 101c + 7d + 117e + 111f -4g -3h -19i -2j + 13k + 17l -91m = 7802 
51a + 31b + 37c + 4d + 101e -13f -7g -13h -41i -3j + 31k + 73l -71m = 8846 
21a + 12b + 67c -67d -9e -67f + 9g + 98h + 101i + 171j + 13k + 191l -54m = -4544 
41a -2b + 5c + 17d + 18e + 19f + 20g + 21h + 22i + 23j -11k + 91l -2m = -4050 
31a + 100b + 2c + 4d + 5e + 10f -10g + 20h -50i -4j + 10k -67l -4m = -4459 
17a + 67b + 2c -2d -8e -78f -9g -101h + 107i + 145j + 21k -9l -10m = 37104 
11a + 3b + 4c + 71d -45e + 87f + 8g + 34h + 45i + 93j + 104k -8l -20m = 4737 
19a -3b -9c -12d + 123e + 61f + 71g + 83h + 91i + 10j + 11k -12l + 82m = 623 
Enter the values of a,b,c,d,e,f,g,h,i,j,k,l,m separated by commas without spaces in between .
'''

inputText='''
16a + 23b + 12c + 34d -2e -37f + 109g -141h + 139i + 149j + 29k + 12l + 131m = 74608 
13a + 31b -5c + 17d + 29e -67f -16g -101h -7i -201j + 32k + 17l + 171m = -4194 
81a -12b -5c + 17d -9e + 17f + 39g + 49h -67i -7j -41k + 13l + 121m = -15793 
91a -43b + 17c + 19d + 17e + 23f + 97g + 101h -101i + 173j -12k + 137l + 217m = -16661 
71a + 47b + 37c + 37d + 41e -37f -67g -17h -2i -6j + 41k -39l -12m = -6862 
61a + 13b + 101c + 7d + 117e + 111f -4g -3h -19i -2j + 13k + 17l -91m = 7802 
51a + 31b + 37c + 4d + 101e -13f -7g -13h -41i -3j + 31k + 73l -71m = 8846
21a + 12b + 67c -67d -9e -67f + 9g + 98h + 101i + 171j + 13k + 191l -54m = -4544 
41a -2b + 5c + 17d + 18e + 19f + 20g + 21h + 22i + 23j -11k + 91l -2m = -4050 
31a + 100b + 2c + 4d + 5e + 10f -10g + 20h -50i -4j + 10k -67l -4m = -4459 
17a + 67b + 2c -2d -8e -78f -9g -101h + 107i + 145j + 21k -9l -10m = 37104 
11a + 3b + 4c + 71d -45e + 87f + 8g + 34h + 45i + 93j + 104k -8l -20m = 4737 
19a -3b -9c -12d + 123e + 61f + 71g + 83h + 91i + 10j + 11k -12l + 82m = 623
'''

equationMatrix=[]
for line in inputText.split('\n'):
    line = line.strip()
    if len(line)!=0:
        equation = []
        marker = 0

        part=''
        while marker!=len(line):
            if '0'<=line[marker]<='9' or line[marker]=='-':
                part+=line[marker]
                if marker==len(line)-1:
                    equation.append(int(part))
            elif len(part)!=0:
                equation.append(int(part))
                part=''

            marker+=1
        equationMatrix.append(equation)
        
def getVariables(matrix):
    if len(matrix)==1:
        return [matrix[0][1]/matrix[0][0]]
    else:
        for i in range(len(matrix)):
            for j in range(1,len(matrix[i])):
                matrix[i][j]=matrix[i][j]/matrix[i][0]
            matrix[i][0] = 1
        nextMatrix = []
        for i in range(1,len(matrix)):
            nextMatrix.append([(matrix[0][j]-matrix[i][j]) for j in range(1,len(matrix[i]))])
        variables = getVariables(nextMatrix)
        
        newVar = matrix[0][len(matrix[0])-1]
        for i in range(len(variables)):
            newVar-=matrix[0][i+1]*variables[i]
        variables = [newVar]+variables
        return variables
import copy

print(','.join(map(lambda var: str(int(round(var))),getVariables(copy.deepcopy(equationMatrix)))))
'''
vars = getVariables(copy.deepcopy(equationMatrix))

for equ in equationMatrix:
    for i in range(len(vars)):
        print(vars[i],'x',equ[i])
    print(sum([vars[i]*equ[i] for i in range(len(vars))]))
    print(equ[len(equ)-1])
'''



