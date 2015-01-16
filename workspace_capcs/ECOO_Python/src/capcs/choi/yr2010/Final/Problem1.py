'''
2010 ECOO Final Problem 1
@author: Wonjohn Choi
'''
import math
class AreaOfPolygon:
    def __init__(self):
        'open the given file'
        self.inputFile = open('data11.txt') 
        
    def getInput(self): 
        'Get the # of coordinates'
        self.numInput = int(next(self.inputFile))
        'Get the data of coordinates'
        self.coords = [list(map(int,next(self.inputFile).split())) for x in range(self.numInput)]
        
    def findCenter(self):
        'Find the center (x, y)'
        self.center=[sum([coord[i] for coord in self.coords])/self.numInput for i in [0,1]]
        
    def getRelativeAngle(self):
        'calculate the angle of each coordinates relative to the center and store the value to the coordiantes array'
        for coord in self.coords:
            coord.append(math.atan2(coord[1]-self.center[1], coord[0]-self.center[0]))
        'Using the angle calculated above, sort the whole array'
        self.coords.sort(key=lambda coord:coord[2])
        
    def getAreaOfPolygon(self):
        'Calculate the area of polygon using the coordinates and their orders using angle'
        self.area = abs(sum([self.coords[i][0]*self.coords[(i+1)%self.numInput][1]
                             -self.coords[i][1]*self.coords[(i+1)%self.numInput][0] for i in range(self.numInput)]))/2
   
    def processOneInput(self):
        'Process one Input: Call each of the functions declared above'
        self.getInput()
        self.findCenter()
        self.getRelativeAngle()
        self.getAreaOfPolygon()

'Create the object to solve the problem'
solver = AreaOfPolygon()

'Since there are five sets of data'
for i in range(5):
    'Process and output each data using AreaOfPolygon class'
    solver.processOneInput()
    print(solver.area) 
        
        