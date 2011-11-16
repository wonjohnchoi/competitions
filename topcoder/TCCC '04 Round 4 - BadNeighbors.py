'''
@author Wonjohn Choi
@lang Python 31
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=5009&pm=2402

@Problem Name:	 BadNeighbors
@Used In:	 TCCC '04 Round 4
@Used As:	 Division I Level One
@Categories:	 Dynamic Programming
'''
def maxDonations(donations):
    nPeople = len(donations)

    #LEFT -> include 1st element | RIGHT -> vice verse
    collected = [[donations[0],0]]+[[0, donations[i]] for i in range(1, nPeople)]

    for i in range(nPeople):
        #check group that does not contain the first element
        for j in range(i+2, nPeople):
            if collected[j][1]==-1 or collected[j][1]<collected[i][1]+donations[j]:
                collected[j][1] = collected[i][1]+donations[j]

        #check group that contains the first element
        for j in range(i+2, nPeople-1):
            if collected[j][0]==-1 or collected[j][0]<collected[i][0]+donations[j]:
                collected[j][0] = collected[i][0]+donations[j]

        maxCollection = -1

    for j in range(nPeople):
        maxCollection = max([maxCollection, collected[j][0], collected[j][1]])

    return maxCollection

if __name__ == '__main__':
    print(maxDonations([10,3,2,5,7,8])) #19
    print(maxDonations([11,15])) #15
    print(maxDonations([7,7,7,7,7,7,7])) #21
    print(maxDonations([1,2,3,4,5,1,2,3,4,5])) #16
    print(maxDonations([94,40,49,65,21,21,106,80,92,81,679,4,61,6,237,12,72,74,29,95,265,35,47,1,61,397,52,72,37,51,1,81,45,435,7,36,57,86,81,72])) #2926
    



