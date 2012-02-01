'''
@author Wonjohn Choi
@problemTitle PointInPolygon
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4755&pm=2384
@lang Python 31
'''
import math
def testPoint(vertices, testPointX, testPointY):
    vertices = list(map(lambda i: list(map(int,i.split())), vertices))
    vectors = list(map(lambda i: [i[0]-testPointX, i[1]-testPointY], vertices))

    #check boundary cases
    for i in range(len(vertices)):
        v1,v2 = vertices[i], vertices[(i+1)%len(vertices)]
        
        if v1[0]==v2[0]==testPointX and min(v1[1],v2[1])<=testPointY<=max(v1[1],v2[1]): return "BOUNDARY"
        if v1[1]==v2[1]==testPointY and min(v1[0],v2[0])<=testPointX<=max(v1[0],v2[0]): return "BOUNDARY"

    #check sum of angles from the given point to the vertices
    angleSum = 0
    for i in range(len(vectors)):
        v1,v2 = vectors[i], vectors[(i+1)%len(vectors)]       
        cross = v2[0]*v1[1] - v1[0]*v2[1]
        dot = v2[0]*v1[0]+v2[1]*v1[1]
        angle = abs(math.acos(dot/(v1[0]*v1[0]+v1[1]*v1[1])**0.5/(v2[0]*v2[0]+v2[1]*v2[1])**0.5)) #A dot B = |A||B|Cos(angle)

        
        if cross<0: angleSum-=angle #if angle is made to the opposite direction, subtract
        else: angleSum+=angle #else, add
    angleSum = abs(angleSum)
    
    #python has floating point issue
    #If angle is equal to 2*PIE, it's interior
    #If angle is equal to 0, it's exterior
    if abs(angleSum-math.pi*2)<10**(-10): return "INTERIOR"
    elif angleSum<10**(-10): return "EXTERIOR"
    else: return "ERROR"

if __name__ == '__main__':
    print(testPoint(["0 0","0 1000","1000 1000","1000 800",
     "200 800","200 600","600 600","600 400",
     "200 400","200 200","1000 200","1000 0"], 100, 50)) #INTERIOR

    print(testPoint(["0 0",
     "0 10",
     "10 10",
     "10 0"],5,5)) #INTERIOR
    
    print(testPoint(["0 0",
     "0 10",
     "10 10",
     "10 0"],10,15)) #EXTERIOR
    
    print(testPoint(["0 0",
     "0 10",
     "10 10",
     "10 0"],5,10)) #BOUNDARY
    
    print(testPoint(["500 0","500 100","400 100","400 200","300 200",
     "300 300","200 300","200 400","100 400","100 500",
     "0 500","0 400","-100 400","-100 300","-200 300",
      "-200 200","-300 200","-300 100","-400 100","-400 0",
      "-500 0","-500 -100","-400 -100","-400 -200","-300 -200",
      "-300 -300","-200 -300","-200 -400","-100 -400","-100 -500",
      "0 -500","0 -400","100 -400","100 -300","200 -300",
      "200 -200","300 -200","300 -100","400 -100","400 0"],200,200))#INTERIOR

    print(testPoint(["0 0","0 1000","1000 1000","1000 800",
     "200 800","200 600","600 600","600 400",
     "200 400","200 200","1000 200","1000 0"], 100, 500)) #INTERIOR
    
    print(testPoint(["1 0","2 0","2 1","3 1","3 0","4 0","4 -1","5 -1","5 0",
     "6 0","6 2","0 2","0 3","-1 3","-1 4","0 4","0 6","1 6",
     "1 7","0 7","0 8","-2 8","-2 2","-8 2","-8 0","-7 0",
     "-7 -1","-6 -1","-6 0","-4 0","-4 1","-3 1","-3 0",
     "-2 0","-2 -6","0 -6","0 -5","1 -5","1 -4","0 -4",
     "0 -3","-1 -3","-1 -2","0 -2","0 -1","1 -1"], 0,0)) #INTERIOR

