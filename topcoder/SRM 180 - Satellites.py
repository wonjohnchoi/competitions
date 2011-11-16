'''
@author Wonjohn Choi
@problemTitle Satellites
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4720&pm=1812
@lang Python 31
'''

from math import radians, cos, sin, sqrt

radius = 6400 #earth has a radius of 6400km

def unPack(data):
    '''
    @return [latitude, longitude, altitude]
    '''
    return list(map(float, data.split()))

def getCoordinates(flyingObject):
    #flyingObject = [latitude, longitude, altitude]
    d = flyingObject[2] + radius
    x = d * sin(radians(flyingObject[1])) * cos(radians(flyingObject[0]))
    y = d * cos(radians(flyingObject[1])) * cos(radians(flyingObject[0]))
    z = d * sin(radians(flyingObject[0]))
    return [x,y,z]

def absolute(*coords):
    return sqrt(sum([coord*coord for coord in coords]))

def seen(rockCoord, satCoord):
    #[x,y,z]
    x1,y1,z1=rockCoord
    x2,y2,z2=satCoord
    cross = absolute(y1*z2-y2*z1,x1*z2-x2*z1,x1*y2-x2*y1) #area of square = cross product
    RS = absolute(x1-x2,y1-y2,z1-z2) #distance between rocket and sallite
    R = absolute(x1,y1,z1) #distance between rocket and center of E
    S = absolute(x2,y2,z2) #distance between satellite and center of E

    if cross < 10**(-9): return True #they are in the same line above Earth
    elif RS*RS+R*R<=S*S: return True #rocket is closer to the center of Earth than the line between R and S
    elif RS*RS+S*S<=R*R: return True #satellite is closer to the center of Earth than the line between R and S
    elif cross/RS>radius: return True #the line is closer, but line itself is far away from the center of Earth
    return False

def detectable(rockets, satellites):
    #[latitude, longitude, altitude]
    rockCoords = [getCoordinates(unPack(rocket+" 400.00")) for rocket in rockets]
    satCoords = [getCoordinates(unPack(satellite)) for satellite in satellites]
    
    answer = []
    
    for i, rocket in enumerate(rockCoords):
        cnt = 0
        for satellite in satCoords:
            if seen(rocket, satellite): cnt+=1
        if cnt>=3: 
            answer.append(i)
    return answer

#test
if __name__ == '__main__':
    print(detectable(["+0000.000 -0000.000"],
        ["+0000.000 -0000.000 +0200.000",
        "+0000.000 -0000.000 +0400.000",
        "+0000.000 -0000.000 +1200.000"])) #[0]

    print(detectable(["-0050.000 +0045.000","+0040.000 -0135.000"],
        ["+0090.000 +0000.000 +1200.000",
         "-0090.000 +0000.000 +1200.000",
         "+0000.000 +0000.000 +1200.000",
         "+0000.000 -0090.000 +1200.000",
         "+0000.000 +0180.000 +1200.000",
         "-0000.000 -0045.000 +1200.000",
         "-0000.000 -0135.000 +1000.000",
         "-0011.000 -0136.000 +1086.828"]))#[1]

    print(detectable(["+0037.431 -0143.361",
         "+0037.443 -0143.375",
         "+0037.413 -0143.364"],
        ["+0037.470 -0143.316 +0513.143",
         "+0037.443 -0143.388 +0342.159",
         "+0037.434 -0143.361 +1034.123"]))#[0,1,2]

