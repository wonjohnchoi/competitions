'''
Created on 2010. 6. 15.

@author: Wonjohn
'''
import math

print math.__name__
print math.__package__
print math.degrees(math.acos(1.0/2))
print math.degrees(math.asin(1.0/2))
print math.degrees(math.asin(math.sqrt(3)/2))
print math.degrees(math.atan2(10, 0))
print math.degrees(math.atan2(-10, 0))
print math.copysign(3, -9.23)
print math.trunc(23.323215325324)
print math.floor(23.23232323)
print math.sin(math.pi/6)
print math.modf(-3.33)
print math.log1p(math.e ** 3 -1)
print math.log(math.e ** 3)
print math.log10(1000)
print math.log(64, 2)
print int(False)
print int(True)
print math.hypot((5-2), (7-3))

array = []
for x in range(11):
    array.append(x)

print math.fsum(array)
print 0 ** 0
print math.frexp(48)
print abs(-2) 
