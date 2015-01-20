import time
import math
import string

def getList():
    file = open('words.txt')
    line = file.readline()
    words = line.split('\",\"')
    words[0]=words[0][1:]
    words[-1]=words[-1][:-1]
    return words
    
def isTriangular(x):
    idx = (math.sqrt(1+8*x)-1)/2
    return idx == int(idx)

def solve():
    words = getList()
    letters = string.ascii_uppercase    
    count=0
    for word in words:
        sum=0
        for letter in word:
            sum+=letters.find(letter)+1
        if isTriangular(sum):
            count+=1
    return count

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:', solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 16, 2010
@result
    Answer: 162
    Time elapsed: 0.05 seconds
"""
