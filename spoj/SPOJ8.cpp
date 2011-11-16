/*
Problem
@ID 8
@NAME Complete the Sequence!
@CODE CMPLS
@URL https://www.spoj.pl/problems/CMPLS/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG C++ 4.3.2
@RESULT accepted (TIME: 0.97    MEM: 2.8M)
*/

#include <iostream>
#include <vector>
using namespace std;


int cache[100][100];

int buildCache(vector<int> seq, int c){
    int s = seq.size();
    
    int i;
    for(i=0;i<s;i++){
        cache[0][i] = seq[i];
    }
    
    //build
    int order,idx;
    for(order=1;order<s;order++){
        for(idx=0;idx<s-order;idx++){
            cache[order][idx] = cache[order-1][idx+1]-cache[order-1][idx];
        }
    }
    
    for(idx=1;idx<1+c;idx++){
        cache[s-1][idx] = cache[s-1][0];
    }    
        
    //add next sequences to all levels
    for(order=s-2;order>=0;order--){
        for(idx=s-order;idx<s-order+c;idx++){
            cache[order][idx] = cache[order][idx-1]+cache[order+1][idx-1];
        }
    }    
}

int main(){
    int t,s,c;
    cin>>t;
    
    int i,j,e;
    for(i=0;i<t;i++){
        cin>>s>>c;
        vector<int> seq(s);
        for(j=0;j<s;j++){
            cin>>e;
            seq[j] = e;
        }
        
        buildCache(seq, c);
        
        for(j=s;j<s+c;j++){
            if(j!=s)cout<<' ';
            cout<<cache[0][j];
        }
        
        cout<<endl;
    }
}
