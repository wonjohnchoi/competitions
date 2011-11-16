'''
Problem
@ID 4
@NAME Transform the Expression
@CODE ONP
@URL https://www.spoj.pl/problems/ONP/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
'''

def toRPN(tokens):
    '''
    Modified "Dijkstra's Two-Stack Algorithm for Expression Evaluation"
    '''
    stack = []
    for token in tokens:
        if token=='(': continue
        
        if token==')':
            var2 = stack.pop()
            oper = stack.pop()
            var1 = stack.pop()
            stack.append(var1+var2+oper)
        else:
            stack.append(token)
    return stack.pop()
            
def solve():
    t = int(input())
    for i in range(t):
        exp = input()
        print(toRPN([c for c in exp if c!=' ']))
        
solve()
    
    