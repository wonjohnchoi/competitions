'''
@author Wonjohn Choi
@problemTitle BirthdayOdds
@problemLink http://www.topcoder.com/tc?module=ProblemDetail&rd=4675&pm=1848
@lang Python 31
'''

def minPeople(minOdds, daysInYear):
    p = 100
    count = 1
    while (100-p)<minOdds:
        p*=((daysInYear-count)/daysInYear)
        count +=1
    return count

print(minPeople(75, 5)) #4
print(minPeople(50, 365)) #23
print(minPeople(1,365)) #4
print(minPeople(84,9227)) #184