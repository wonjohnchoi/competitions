'''
Problem
@CONTEST Google Code Jam 2011: Round 1B
@ID A
@NAME RPI
@URL http://code.google.com/

Solution
@AUTHOR Wonjohn Choi (http://capcs.wordpress.com/)
@LANG Python 3
@RESULT accepted (small input, large input)
'''

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\A.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam 2011 - Round 1B\\A.in','r')

    t = int(next(fin).strip())
    for i in range(t):
        n = int(next(fin).strip())
        data = []
        for j in range(n):
            data.append(next(fin).strip())
            
            #print(data[j])
        WP = []
        OWP = []
        OOWP = []
        
        #WP
        for j in range(n):
            win = 0
            lose = 0
            for k in range(n):
                if data[j][k]=='.':
                    pass
                elif data[j][k]=='1':
                    win+=1
                else:
                    lose+=1
            WP.append(win/(win+lose))
        
        #OWP
        for j in range(n):
            owp = 0
            nn = 0
            for k in range(n):
                if k!=j and data[k][j]!='.':
                    win = 0
                    lose = 0
                    for l in range(n):
                        if l!=j:
                            if data[k][l]=='.':
                                pass
                            elif data[k][l]=='1':
                                win+=1
                            else:
                                lose+=1
                    owp+=win/(win+lose)
                    nn+=1
            OWP.append(owp/nn)
        
        #OOWP
        for j in range(n):
            oowp = 0
            nn = 0
            for k in range(n):
                if k!=j and data[k][j]!='.':
                    oowp+=OWP[k]
                    nn+=1
            OOWP.append(oowp/nn)
        
        fout.write('Case #%d:\n'%(i+1))
        #final
        for j in range(n):
            fout.write(str(0.25 * WP[j] + 0.50 * OWP[j] + 0.25 * OOWP[j]))
            fout.write('\n')
                            
solve()