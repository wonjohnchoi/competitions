#include <iostream>
using namespace std;

int main() {
    int N,x1,x2,y1,y2;

    cin>> N;
    
    for(int i=0; i<N; i++){
        cin >> x1>>y1>>x2>>y2;
        
        int level1 = x1+y1;
        int level2 = x2+y2;
        int steps=0;
        
        if(level1<level2){
            steps = (level2)*(level2+1)/2 - (level1)*(level1+1)/2;
            steps += y2-y1;
        }
        else if( level1 >level2){
            steps = (level1)*(level1+1)/2  - (level2)*(level2+1)/2 ;
            steps += y1-y2;
        }
        else if( level1 == level2){
            steps = y1-y2;
            if( steps<0)
                steps = -steps;
        }
        
        cout <<steps<<endl;
    }
    return 0;
}