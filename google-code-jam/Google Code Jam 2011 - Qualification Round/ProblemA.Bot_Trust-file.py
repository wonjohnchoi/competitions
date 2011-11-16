
class Robot:
    def __init__(self):
        self.status = 'waiting' #waiting, moving, pushing, idle (no more command)
        self.pos = 1
        self.dest = -1
        self.idx = -1


def find(c,commands, f):
    for i in range(f, len(commands)):
        if commands[i][0]==c:
            return i
    return -1

def solve():
    fout = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\A.out','w')
    fin = open('C:\\Users\\Wonjohn Choi\\Python\\Google Code Jam\\A.in','r')
    
    t = int(str(next(fin)).strip())
    
    for nCase in range(t):
        line = list(str(next(fin)).strip().split(' '))[1:]
        
        commands = []
        
        for i in range(0,len(line),2):
            if str(line[i])=='O':
                commands.append((0,int(line[i+1])))
            else:
                commands.append((1,int(line[i+1])))
                
        cnt = 0
        
        #print(find('O',commands))
        #print(find('B',commands))
        
        robots = [Robot(), Robot()] #O, B
        
        #done = False
        while not (robots[0].status == 'idle' and robots[1].status == 'idle'):
            cnt+=1
            
            for i in (0,1):
                r = robots[i]
                if r.status == 'waiting':
                    i = find(i, commands, r.idx+1)
                    if i==-1:
                        r.status = 'idle'
                        r.idx = len(commands)
                    else: 
                        r.dest = commands[i][1]
                        r.idx = i
                        
                        if r.pos==r.dest:
                            r.status = 'pushing'
                        else:
                            r.status = 'moving'
                            
            if robots[0].status == 'pushing' and robots[1].status == 'pushing':
                if robots[0].idx>robots[1].idx:
                    robots[1].status = 'waiting'
                    #print('Robot 0: sleeping.')
                    #print('Robot 1: pushed at %d'%(robots[1].pos))
                else:
                    robots[0].status = 'waiting'
                    #print('Robot 1: sleeping.')
                    #print('Robot 0: pushed at %d'%(robots[0].pos))
            
            elif robots[0].status == 'idle' and robots[1].status == 'idle':
                cnt-=1
                #print('DONE')
            else:
                for i in [0,1]:
                    if robots[i].status == 'pushing' and robots[i].idx<robots[1-i].idx:
                        robots[i].status = 'waiting'
                        #print('Robot %d: pushed at %d'%(i, robots[i].pos))
                    elif robots[i].status == 'moving':
                        #print('Robot %d: moved from %d'%(i, robots[i].pos),end='')
                        if robots[i].dest>robots[i].pos:
                            robots[i].pos+=1
                        else:
                            robots[i].pos-=1
                        #print(' to %d.'%(robots[i].pos))    
                        if robots[i].pos==robots[i].dest:
                            robots[i].status = 'pushing'
                    else:
                        #print('Robot %d: sleeping'%(i))
                        pass
            
             
        fout.write('Case #%d: %d'%(nCase+1, cnt))
        fout.write('\n')
        
solve()