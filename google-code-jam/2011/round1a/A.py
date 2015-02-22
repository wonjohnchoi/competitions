'''
Problem
@CONTEST Google Code Jam 2011: Round 1A
@ID A
@NAME FreeCell Statistics
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted for small and large
'''


'''
D => # of games today
G => # of games total (G>=D)
P_D => percentage of winning in D games today
P_G => percentage of winning in G games total
* percentages are exact - no rounding problem
D => unknown
G => unknown
N => maximum possible D (D<=N)
N => known

INPUT
T
N P_D P_G (T lines)

1<=T<=2000
1<=N<=10^15

OUTPUT
Case #x: y (x=> index (1, 2, ...), y=> "Possible" or "Broken")

P_D:100-P_D = wtoday:ltoday
P_G:100-P_G = wtotal:ltotal

4:1 (4+1<=9)
'''

def GCD(a,b):
    if b==0: return a
    return GCD(b, a%b)

def validate(n, pd, pg):
    if pg==100: 
        if pd==100: return "Possible"
        else: return "Broken"
    if pg==0: 
        if pd==0: return "Possible"
        else: return "Broken"
    
    wtoday, ltoday = pd/GCD(pd, 100-pd), (100-pd)/GCD(pd, 100-pd)
    #wtotal, ltotal = pg/GCD(pg, 100-pg), (100-pg)/GCD(pg, 100-pg)
    
    if wtoday+ltoday>n: return "Broken"
    return "Possible"

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 1A\\A.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\source_codes\\Python\\Google Code Jam 2011 - Round 1A\\A.in','r')
    
    t = int(next(fin))
    for i in range(t):
        n, pd, pg = map(int, next(fin).split(' '))
        answer = validate(n, pd, pg)
        fout.write('Case #{0}: {1}\n'.format(i+1, answer))
        

               
solve()