/*
Problem
@ID 10
@NAME Complicated Expressions
@CODE CMEXPR
@URL https://www.spoj.pl/problems/CMEXPR/

Solution
@AUTHOR Wonjohn Choi (https://www.spoj.pl/users/wonjohnchoi/)
@LANG C++ 4.3.2
@RESULT accepted (TIME: 1.91,    MEM: 9.1M)
*/

#include <iostream>
#include <string>

using namespace std;

struct Tree{
    static string exp;
    Tree *left, *right;
    char op;    
        
    Tree(int l, int r){
        while(true){
            if(l==r){
                op = exp[l];
                left = right = NULL;
                return;
            }
            
            int pre, br, i;
            
            for(pre=0;pre<=1;pre+=1){
                br=0;
                for(i=r;i>=l;i-=1){
                    if(exp[i]=='(')br-=1;
                    else if(exp[i]==')') br+=1;
                    else if(br==0 && precedence(exp[i])==pre){
                        left = new Tree(l, i-1);
                        right = new Tree(i+1, r);
                        op = exp[i];
                        return;
                    }
                }
            }
            
            //If nothing detected, there must be an enclosing bracket at the very left and right sides
            l+=1;
            r-=1;
        }
    }
    
    ~Tree(){
        if(left) delete left;
        if(right) delete right;
    }
    
    string condense(){
        if(isalpha(op)) return string(1,op);
        string lexp, rexp;
        lexp = left->condense();
        //cout<<lexp<<endl;
        rexp = right->condense();
        //cout<<rexp<<endl;
        int pre, lpre, rpre;
        pre = precedence(op);
        lpre = precedence(left->op);
        rpre = precedence(right->op);
        
        if(pre>rpre) rexp = "("+rexp+")";
        if(pre>lpre) lexp = "("+lexp+")";
        if(pre==rpre && (op=='-' || op=='/')) rexp = "("+rexp+")";
        
        return lexp+op+rexp;
    }
    
    int precedence(char op){
        if(op=='+' || op=='-')return 0;
        if(op=='*' || op=='/')return 1;
        if(isalpha(op))return 2;
        return -1;
    }
};

string Tree::exp = "";


int main(){
    int t, i;
    cin>>t;
    
    string exp;
    for(i=0;i<t;i++){
        cin>>exp;
        Tree::exp = exp;
        Tree *root = new Tree(0, exp.size()-1);
        cout<<root->condense()<<endl;
    }
    return 0;
}