/*
Wonjohn Choi
Solved with Java
2009 Annual Berkeley Programming Contest: Problem 1
*/

import java.io.*;
import java.util.*;
public class P1{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String letterSet, figureSet, msg;
        letterSet = in.nextLine();
        figureSet = in.nextLine();
        
        while(in.hasNextLine()){
            msg = in.nextLine();
            System.out.println(encode(msg, letterSet, figureSet));
        }
    }
    
    public static String encode(String msg, String letterSet, String figureSet){
        boolean downShifted = true; 
        String out = "";
        
        for(int i=0; i<msg.length(); i+=5){
            int val = Integer.valueOf(msg.substring(i, i+5), 2);
            
            if(val==31){
                downShifted = false;
            }else if(val==27){
                downShifted = true;
            }else if(downShifted){
                out += letterSet.charAt(val);
            }else{
                out += figureSet.charAt(val);
            }
        }
        
        return out;
    }

}