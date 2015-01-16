'''
2010 ECOO Final Problem 2
@author Wonjohn Choi
@TODO The very huge input - too much time is used
    Time Complexity should be improved

'''

class TimeOfArrival:
    def __init__(self):
        #open the given file
        self.file = open('Data21.txt')
    
    def getInput(self):
        #get the next couple of input as Integer
        self.input = tuple(map(int,next(self.file).split()))
        
    def getOutput(self):
        #calculate the required output using 'getTime' function
        self.output = max(self.getTime(self.input[0], self.input[1], True), self.getTime(0, 0, False))#self.getTime(0, 0, False))
    
    def getTime(self, init_speed, init_pos, forX):
        '''
        Use Breadth-First-Search
        Care x and y coordinates separately
        Calculate the next days' possible position and speed
        Repeat using while loop
        '''
        destPos = (250, 105)[not forX]
        destSpeed = 0
        
        'speed, position, day, change direction'
        possibleMove = [(init_speed, init_pos, 0, 0)]
        pastMove = set()
        
        #Continue till answer is found
        while True:
            speed, pos, day, turnDir = possibleMove.pop(0)
            
            #check if the speed and position is previously visited
            if (speed, pos) in pastMove: continue
            else: pastMove.add((speed,pos))
            
            #print('Speed: ',speed,'|Position: ', pos,'|Day: ', day)
            if speed == destSpeed and pos==destPos:
                return day-1
            
            accel = [-1,0,1]                        
            possibleMove+=[[speed+delta, pos+speed+delta, day+1, turnDir] for delta in accel]           

#Use TimeOfArrival class to solve the problem
solver=TimeOfArrival()

for _it in range(5):
    #calculate input and output
    solver.getInput()
    solver.getOutput()
    
    #Output to console
    print('Estimated time of arrival is in %d days'%(solver.output))
    
