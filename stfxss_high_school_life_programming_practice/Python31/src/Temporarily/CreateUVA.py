'''
Created on 2010. 6. 27.

@author: user
'''

for x in range(1, 2000):
    y=str(x)
    while len(y)!=4:
        y='0'+y
    print('[[[SPOJ %s]]]' %y)