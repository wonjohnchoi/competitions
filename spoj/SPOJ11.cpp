/*
Problem
@ID 11
@NAME Factorial
@CODE FCTRL
@URL https://www.spoj.pl/problems/FCTRL/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG C++ 4.3.2
@RESULT accepted (TIME: 1.49,  MEM: 2.6M)
*/

#include <iostream>
using namespace std;

int getPowerOf(int fac, int n){
    if(fac==0) return 0;
    int q = (int)fac/n;
    return getPowerOf(q, n)+q;
}

int main(){
    int t, i, fac;
    
    cin>>t;
    
    for(i=0;i<t;i++){
        cin>>fac;
        cout<<getPowerOf(fac, 5)<<endl;
    }
}