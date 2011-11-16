'''
Double Squares
A double-square number is an integer X which can be expressed as the sum of two perfect squares. For example, 10 is a double-square because 10 = 32 + 12. Your task in this problem is, given X, determine the number of ways in which it can be written as the sum of two squares. For example, 10 can only be written as 32 + 12 (we don't count 12 + 32 as being different). On the other hand, 25 can be written as 52 + 02 or as 42 + 32.

Input
You should first read an integer N, the number of test cases. The next N lines will contain N values of X.
Constraints
0 ≤ X ≤ 2147483647
1 ≤ N ≤ 100
Output
For each value of X, you should output the number of ways to write X as the sum of two squares.

Download input file Submit answers

@website http://www.facebook.com/hackercup/problems.php?round=4
@problemFrom Facebook HackerCup Qualification Round
@problemName Double Squares
-

'''
file = open('input.txt')
n = int(next(file).strip())

xList = []
for i in range(n):
    xList.append(int(next(file).strip()))

print(xList)#
xList=[123456789]
squareList = [i*i for i in range(int(max(xList)**0.5), -1, -1)]
print(squareList)
'''
squareSet = set(squareList)

for x in xList:
    counter=0
    for square in squareList:
        if square >= x-square:
            if (x-square) in squareSet:
                counter+=1   
        else:
            break
    print(counter)
'''
