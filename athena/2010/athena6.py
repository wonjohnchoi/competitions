'''
Question 6
Points 100


There are 52735727364727372 students and 2889221271829121 teachers in a school . On children's day each teacher brings x toffees with him/her . All the toffees are collected and distributed equally to all the students . At the end it was found that there was exactly one toffee remaining .
What is the least possible value of x ?

Nt*x = Ns*I+1 (I <- the least integer that makes x an integer)
x = (Ns*I+1)//Nt
x = (Ns//Nt)*I + ((Ns%Nt)*I+1)//Nt
(Ns%Nt)*I+1 ==0 (Mod Nt)
(Ns%Nt)*I+1 = Nt*I2 (I2 <- the least integer that makes I an integer)
I=(Nt*I2-1)//(Ns%Nt)
I=(Nt//(Ns%Nt))*I2+((Nt%(Ns%Nt))*I2-1)/(Ns%Nt)
'''

Ns = 52735727364727372
Nt = 2889221271829121


def getX(Ns, Nt, error):
    if Nt==1: return 1
    I = getX(Nt, Ns%Nt, error*(-1))
    return (Ns*I+error)//Nt
print(getX(Ns,Nt,1))

