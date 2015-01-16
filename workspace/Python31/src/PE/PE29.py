import time

def solve():
    group = set()
    for a in range(2, 101):
        for b in range(2, 101):
            group.add(a**b)
            
    return len(group)

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
    Answer: 9183
    Time elapsed: 0.05 seconds
"""
