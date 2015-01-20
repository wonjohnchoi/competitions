import time
coins = (1,2,5,10,20,50,100,200)
def eval(amount, level=len(coins)-1):    
    if level==0: return 1
    
    sum = 0
    while amount>=0:
        sum+=eval(amount, level-1)
        amount-=coins[level]
    return sum

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:',eval(200))
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 17, 2010
@result
    Answer: 73682
    Time elapsed: 0.09 seconds
"""
