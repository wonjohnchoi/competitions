'''
@author Wonjohn Choi
@problemTitle ConvexPolygon
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4635&pm=1660
@lang Python 31
'''

from math import atan2
def findArea(x, y):
    vectors = [(x[i]-x[0], y[i]-y[0]) for i in range(1,len(x))]
    area = 0
    for i in range(0,len(vectors)-1):
        #add cross product (area) of each part
        area+=vectors[i][0]*vectors[i+1][1] - vectors[i+1][0]*vectors[i][1]
    return abs(area/2) #trangle has half area

#test
if __name__=='__main__':
    print(findArea([0,0,1], [0,1,0])) #0.5
    print(findArea([-10000,-10000,10000,10000], [10000,-10000,-10000,10000])) #400000000.0
    print(findArea([100,80,30,-30,-80,-100,-80,-30,30,80], [0,58,95,95,58,0,-58,-95,-95,-58])) #29020.0
    print(findArea([-6010,-7937,-8782,-9506,-9654,-9852,-9854,-9998,-9999,-9996,-9901,-9811, 
    -9444,-8798,-8580,-2085,6842,8339,9827,9946,9993,9959,9940,9855,9657,
    8504,8262,7552,6326,5537,4723], [-9976,-9947,-9873,-9739,-9654,-8501,-8475,-5009,475,4926,7078,8673,9417,
    9785,9820,9974,9986,9979,9862,9211,-5070,-6599,-7121,-8624,-8912,-9710,
    -9766,-9863,-9914,-9941,-9962])) #393996063.5
