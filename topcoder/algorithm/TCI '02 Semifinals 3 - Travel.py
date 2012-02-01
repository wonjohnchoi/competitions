'''
@author Wonjohn Choi
@lang Python 31
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4372&pm=996

@Problem Name:	 Travel
@Used In:	 TCI '02 Semifinals 3
@Used As:	 Division I Level One
@Categories:	 Brute Force, Geometry, Graph Theory
'''

from math import sin, cos, sqrt, acos, radians
from itertools import permutations

def convertToXYZ(city, radius):
    latitude, longitude = map(int, city.split(' '))
    x = radius*cos(radians(latitude))*cos(radians(longitude))
    y = radius*cos(radians(latitude))*sin(radians(longitude))
    z = radius*sin(radians(latitude))
    return [x,y,z]

def absolute(coord):
    return sqrt(sum([val*val for val in coord]))

def angle(coord1, coord2):
    #c*c=a*a+b*b-2abcos(angle)
    #angle = acos((a*a+b*b-c*c)/(2ab))
    #angle in radian
    a = absolute(coord1)
    b = absolute(coord2)
    c = absolute([coord1[i]-coord2[i] for i in range(len(coord1))])
    return acos((a*a+b*b-c*c)/(2*a*b))

def distCitys(coord1, coord2, radius):
    #angle (in radian)/(2*pi) * 2*pi*radius = angle * radius
    if coord1==coord2: return 0
    return angle(coord1, coord2) * radius

def distRoute(route, dists):
    #ex of route: [0, 1, 3, 2, 5, 4, 3, 0]
    return sum([dists[route[i]][route[i+1]] for i in range(len(route)-1)])

def shortest(cities, radius):
    coords = [convertToXYZ(city, radius) for city in cities]
    dists = [[distCitys(coords[i], coords[j], radius) for j in range(len(cities))] for i in range(len(cities))]
    
    minDist = 10**10
    for route in permutations(range(1,len(cities))):
        minDist = min(minDist, distRoute([0]+list(route)+[0], dists))
    return round(minDist)

if __name__ == '__main__':
    print(shortest(["0 0","0 1"], 1000)) #35
    print(shortest(["0 0","0 1","0 -1","-1 0","1 0","-1 -1","1 1","1 -1","-1 1"],1)) #0
    print(shortest(["40 -82","-27 -59","-40 48","26 -12","-31 -37","-30 42","-36 -23","-26 71","-19 83","8 63"], 698)) #4505


        
        
