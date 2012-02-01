'''
@author Wonjohn Choi
@problemTitle TVTower
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4735&pm=2260
@lang Python 31
'''

def maxDistance(centerX, centerY, x, y):
    '''
    maximum distance from the center to other points
    x, y are arrays of pointX and pointY
    '''
    return max([((centerX-x[i])**2 + (centerY-y[i])**2)**0.5 for i in range(len(x))])

def centerOf3Points(i,j,k, x, y):
    '''
    center of three points
    Use intersection of line ij and jk = center
    i, j, k is the index of points in x, y arrays
    '''
    #y = a1(x-x1)+y1
    #y = a2(x-x2)+y2

    #middle points of each line
    x1, y1 = (x[i]+x[j])/2, (y[i]+y[j])/2
    x2, y2 = (x[j]+x[k])/2, (y[j]+y[k])/2
    
    #to cover special case
    if y[i]==y[j]==y[k]: return None
    if y[i]==y[j]:
        centerX = x1
        a2 = -(x[k]-x[j])/(y[k]-y[j])
        #y = a2(x-x2)+y2
        centerY = a2*(centerX-x2)+y2
    elif y[j]==y[k]:
        centerX = x2
        a1 = -(x[j]-x[i])/(y[j]-y[i])
        #y = a1(x-x1)+y1
        centerY = a1*(centerX-x1)+y1
    else:
        a1 = (x[j]-x[i])/(y[j]-y[i])
        a2 = (x[k]-x[j])/(y[k]-y[j])
        
        if a1==a2: return None #parallel
        else:
            #(a1-a2)x-(a1x1-a2x2)+(y1-y2) = 0
            #x = ((a1x1-a2x2)-(y1-y2))/(a1-a2)
            centerX = ((a1*x1-a2*x2)-(y1-y2))/(a1-a2)
            centerY = a1*(centerX-x1)+y1
    return (centerX,centerY)

def minRadius(x,y):
    if len(x)==1: return 0.0
    
    minimumRadius = 10**10
    
    for i in range(len(x)):
        for j in range(i+1, len(x)):
            #center of two points
            centerX, centerY = (x[i]+x[j])/2, (y[i]+y[j])/2
            minimumRadius = min(minimumRadius, maxDistance(centerX, centerY, x, y))

            for k in range(j+1, len(x)):
                #center of three points
                center = centerOf3Points(i,j,k, x, y)
                if center!=None:
                    minimumRadius = min(minimumRadius, maxDistance(center[0], center[1], x, y))
    return minimumRadius

#test
if __name__ == '__main__':
    print(minRadius([1, 0, -1, 0], [0, 1, 0, -1])) #1.0
    print(minRadius([3], [299])) #0.0
    print(minRadius([5, 3, -4, 2],[0, 4, 3, 2])) #4.74341649025
                
                
