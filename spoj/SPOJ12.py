'''
Problem
@ID 12
@NAME The Game of Master-Mind
@CODE MMIND
@URL https://www.spoj.pl/problems/MMIND/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG Python 3
@RESULT
'''

def guesses(p, c):
    '''p->#pins c->#colors'''
    guess = [1]*p
    
    while True:
        yield guess
        
        length = len(guess)
        i = length-1
        while i >= 0:
            if guess[i]<c:
                guess[i]+=1
                break
            else:
                guess[i]=1
                i-=1
        
        if i == -1:
            return

def solve():
    t = int(input())
    for i in range(t):
        #p->#pins c->#colors g->#guesses
        p,c,g=map(int,input().split(' '))
        
        for guess in guesses(p,c):
            print(guess)
            
solve()
        