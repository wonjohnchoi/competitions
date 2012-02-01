'''
Created on Jan 6, 2012

@author: wonjohnchoi
'''
def tokenize():
    while True:
        for token in raw_input().split(' '):
            if token.strip() != '':
                yield token
tokens = tokenize()
INT = lambda : int(next(tokens))
FLOAT = lambda : float(next(tokens))
STR = lambda : next(tokens)

t = INT()
for _ in range(t):
    n, m = INT(), INT()
    print 'n:',n, 'm:',m
    print 'r1:%.2f'%pow(2, n + 1)
    print 'r2:%.2f'%pow(2, m + 1)
    print 'r3:%.2f'%(pow(2, n + 1) - pow(2, m + 1))