'''
Created on 2010. 6. 15.

@author: user
'''
array = []
for x in range(1000):
    array.append(True)
    
print all(array)
print any(array)

array[0] = False

print all(array)
print any(array)

for x in range(1000):
    array[x] = False

print all(array)
print any(array)
