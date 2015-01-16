'''
2010 ECOO Final Problem 3
@author: Wonjohn Choi
@description: With some tricks, solved most of parts with bruteforce
            But time complexity is OK to get the answer in the given time
@lang Python 3.1
'''
from itertools import product
FILE = open('Data31.txt')

def getInput():
    return [next(FILE) for idx in range(5)]  

def extractOperators(inputs):
    'Get a set of inputs by extracting operators'
    
    opers = []
    opers+=inputs[0].strip().split('X')
    opers+=inputs[1].strip().split(' ')
    opers+=inputs[2].strip().split('X')
    opers+=inputs[3].strip().split(' ')
    opers+=inputs[4].strip().split('X')
    return list(filter(lambda o: o!='' and o!='=', opers))

def evaluate(var1, var2, operator):
    'Calculate (var1 operator var2)'
    
    return eval(str(var1)+operator+str(var2))%10

def different(vars):
    '''
    For a set of numbers (vars), check if each is different from others
    '''
    vars = list(vars)
    while len(vars)!=0:
        comparedVar = vars.pop()
        if any(comparedVar == var for var in vars):
            return False
    return True
    

def findVars(opers):
    '''
    Using loops, efficiently check every possible cases to find one 
    that works with the given operators
    '''
        
    for var00, var01, var02 in product(range(1, 10), repeat=3):
        if evaluate(var00, var01, opers[0]) == var02:
            for var10, var20 in product(range(1,10), repeat=2):
                if evaluate(var00, var10, opers[1]) == var20:
                    for var11, var21 in product(range(1,10), repeat=2):
                        if evaluate(var01, var11, opers[2]) == var21:
                            for var12, var22 in product(range(1,10), repeat=2):
                                if evaluate(var02, var12, opers[3]) == var22:
                                    if evaluate(var10, var11, opers[4]) == var12:
                                        if evaluate(var20, var21, opers[5]) == var22:
                                            found = (var00, var01, var02, var10, var11, var12, var20, var21, var22)
                                            if different(found):
                                                return found

for _it in range(5):
    inputs = getInput() #get input
    opers = extractOperators(inputs) #get operators from inputs
    answers = list(map(str,findVars(opers))) #get answers (the nine unknown numbers)
    outputs = [list(inputs[idx]) for idx in range(len(inputs))] #using inputs, build outputs' format
    
    for r in range(3):
        for c in range(3):
            outputs[r*2][c*2] = answers[r*3+c] #replace the 'X' part with the actual number
    
    outputs = [''.join(outputs[idx]) for idx in range(len(outputs))] #make output as lines
    print(''.join(outputs)) 

   

    

    
