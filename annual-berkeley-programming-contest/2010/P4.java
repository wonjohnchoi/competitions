/**
Wonjohn Choi
Solved with Java
2010 Annual Berkeley Programming Contest: Problem 4
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class P4{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Vector<String> dict = new Vector<String>(83681);
        Vector<String> q = new Vector<String>();
        
        for(char c='a';c<='z';c++){
            q.add(""+c);
        }
        
        while(!q.isEmpty()){
            String cur = q.remove(0);
            dict.add(cur);
            
            if(cur.length()==5) continue;
            for(char c=(char)((int)cur.charAt(cur.length()-1)+1);c<='z';c++){
                q.add(cur+c);
            }
        }
        
        String str;
        int idx;
        while(in.hasNext()){
            str = in.next();
            idx = dict.indexOf(str);
            
            if(idx<0){
                System.out.println(0);
            }else{
                System.out.println(idx+1);
            }
        }
        
        System.exit(0);
        
    }
}