/*
Wonjohn Choi
Solved with Java
2009 Annual Berkeley Programming Contest: Problem 3
*/

import java.io.*;
import java.util.*;
public class P3{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            if(validate(in.next())){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
    
    static char[] symbols = new char[]{'N', 'C', 'D', 'E', 'I'};
    public static boolean validate(String msg){
        if(msg.length()==0) return false;
        if(msg.length()==1){
            if('p'<=msg.charAt(0) && msg.charAt(0) <= 'z') return true;
            else return false;
        }
        
        int idx = -1;
        for(int i=0;i<symbols.length;i++){
            if(msg.charAt(0)==symbols[i]){
                idx = i;
                break;
            }
        }
        
        if(idx==-1) return false;
        
        if(idx==0){ //N
            return validate(msg.substring(1));
        }else{
            for(int i=2;i<msg.length();i++){
                if(validate(msg.substring(1, i)) && validate(msg.substring(i))){
                    return true;
                }
            }
            return false;
        }
    }

}