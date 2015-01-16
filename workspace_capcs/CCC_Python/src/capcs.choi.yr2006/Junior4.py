'''
@author Wonjohn Choi
@lang Python3
@CCC 2006 Junior 4
'''

constraints = [[1,7],[1,4],[2,1],[3,4],[3,5]]

def getConstraint(): return map(int, (input(),input()))

first, last = getConstraint()
while not (first==0 and last==0):  
    constraints.append([first, last])
    first, last = getConstraint()

done = []
while len(done)!=7:
    ready = list(range(1,8))
    for constraint in constraints:
        if constraint[1] in ready:
            ready.remove(constraint[1])

    for constraint in done:
        if constraint in ready:
            ready.remove(constraint)
    
    if len(ready)==0:
        print('Cannot complete these tasks. Going to bed.')
        break
    
    else:
        chosen = ready[0]
        done.append(chosen)
        
        idx=0
        while idx<len(constraints):
            if constraints[idx][0]==chosen:
                constraints.pop(idx)      
            else:
                idx+=1

if len(done)==7:
    print(' '.join(map(str,done)))
