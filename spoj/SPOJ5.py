'''
Problem
@ID 5
@NAME The Next Palindrome
@CODE PALIN
@URL https://www.spoj.pl/problems/PALIN/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
'''

def makePal(s):
    '''
    To minimize the increment, the middle part should be the highest digits increasing.
    Left parts should never change.
    Right parts will change to make the number pal.
    '''
    lst = [c for c in s]
    size = len(lst)
    
    #get middle indices
    if size%2==0:
        i,j=size//2-1,size//2
    else:
        i=j=(size-1)//2
        
    #increase the middle part, making it pal
    while lst[i]=='9' and lst[j]=='9':
        lst[i] = lst[j] = '0' #6997-> 6007
        i-=1
        j+=1
        if i<0: return '1'+'0'*(size-1)+'1' #special case: 999...999
    if lst[i]>lst[j]: #51 -> 55 
        lst[j] = lst[i]
    elif lst[i]==lst[j]: 
        #useMid=True 7227 -> 7337 or 787 -> 797 (for odd # of digits)
        #useMid=False 7226 -> 7227 or 786 -> 787 (for odd # of digits)
        k,l=i,j
        useMid = False
        while lst[k]==lst[l]:
            k-=1
            l+=1
            if k<0:
                useMid = True
                break
        if not useMid:
            if lst[k]>lst[l]: lst[l]=lst[k]
            else: useMid = True
            
        if useMid:    
            lst[i]=chr(ord(lst[i])+1)
            if i!=j:
                lst[j]=chr(ord(lst[j])+1)
        else:
            for m in range(i+1,j):
                lst[m]='9' #89997 -> 80007 -> 80008 -> 89998
    else: #13 -> 22
        lst[i]=chr(ord(lst[i])+1)
        lst[j]=lst[i]
        
    #change the right part, making it pal
    while i>0:
        i-=1
        j+=1
        lst[j] = lst[i]
    
    return ''.join(lst)

def solve():
    t = int(input())
    
    for i in range(t):
        n = str(input())
        print(makePal(n))
        
solve()


def test():
    inputs=[
    '12321',
    '12322',
    '12320',
    '22321',
    '13432',
    '99999',
    '88999',
    '999999',
    '123321',
    '889987',
    '889988',
    '889989',
    '123456'
    ]
    
    outputs=[
    '12421',
    '12421',
    '12321',
    '22322',
    '13531',
    '100001',
    '89098',
    '1000001',
    '124421',
    '889988',
    '890098',
    '890098',
    '124421'
    ]
    
    for i in range(len(inputs)):
        print('Testing case #%d... '%i,end='')
        if makePal(inputs[i])==outputs[i]:
            print('good')
        else:
            print('bad: %s'%makePal(inputs[i]))
            
            
#test()