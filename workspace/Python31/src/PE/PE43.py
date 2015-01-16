import time
def nextPermutation(given):
    if len(given) <=1 and given[0]!='0':
        yield given
    else:
        for permutation in nextPermutation(given[1:]):
            for i in range(len(permutation)+1):
                yield permutation[:i] + given[0:1] + permutation[i:]
def solve():
    pandigitals = '0123456789'
    primes = [2, 3, 5, 7, 11, 13, 17]

    sum = 0
    for perms in nextPermutation(pandigitals):
        for i in range(1, 8):
            if int(perms[i:(i+3)])%primes[i-1]!=0:
                break
        else:
            sum+=int(perms)
    return sum

def main():
    start = time.time()
    if __name__ == '__main__':
        print('Answer:',solve())
    end = time.time()
    print("Time elapsed: %.2f seconds" %(end-start))
    
main()

"""
@author Wonjohn Choi
@date June 17, 2010
@result
    Answer: 16695334890
    Time elapsed: 44.47 seconds
"""
