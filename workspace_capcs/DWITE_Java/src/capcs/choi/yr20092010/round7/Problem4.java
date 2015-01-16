package capcs.choi.yr20092010.round7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/what_is_this_algebra.html
 * @author Wonjohn Choi
 *
 */
public class Problem4 {
    public static void main(String args[]) throws IOException {
        //I/O
        Scanner sc = new Scanner(new FileReader("DATA4.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT4.txt"));
    
        //for each input
        for(int _it=0;_it<5;_it++){
            pw.println(eval(sc.nextLine().replace(" ", "")));
        }

        //End I/O
        sc.close();
        pw.close();
    }
    
    /**
     * evaluate an expression with the order (Brackets, Exponents, Multiplication/Division, Addition/Subtraction)
     * The operations work as expected, with the exception of exponents. Exponents will concatenate the two sides as if they were strings.
     * If the expression is evaluated as a number, return itself
     * @param expression to compute
     * @return
     */
    public static String eval(String expression){        
        //if expr is an integer, return itself
        try{
            Integer.parseInt(expression);
            return expression;
        }catch(Exception e){}
        
        StringBuilder expr = new StringBuilder(expression);
        
        
       /*process to compute all of brackets*/
        int bIdx1, bIdx2; //bracket index       
        while(true){
            bIdx1 = bIdx2 = expr.indexOf(")"); 
            while((--bIdx1)>=0 && expr.charAt(bIdx1)!='(');
            //if bracket exists
            if(bIdx1>=0 && bIdx2>=0){
                expr.replace(bIdx1, bIdx2+1, eval(expr.substring(bIdx1+1, bIdx2)));
            }else{
                break;
            }
        }
        
         int idx;
         
        /*process to compute all of Addition/Subtraction*/
        while(true){
            idx = expr.length();
            while((--idx)>=1 && !(isPlusMinus(expr.charAt(idx)) && Character.isDigit(expr.charAt(idx-1))));
            //if plus or minus is found
            if(idx>=1){
                if(expr.charAt(idx)=='+'){ 
                    expr = new StringBuilder(""+
                            (Integer.parseInt(eval(expr.substring(0, idx)))
                            +Integer.parseInt(eval(expr.substring(idx+1, expr.length())))));
                }else{
                    expr = new StringBuilder(""+
                            (Integer.parseInt(eval(expr.substring(0, idx)))
                            -Integer.parseInt(eval(expr.substring(idx+1, expr.length()))))); 
                }
            }else{
                break;
            }
        }

        /*process to compute all of Multiplication/Division*/
        while(true){
            idx = expr.length();
            while((--idx)>=1 && !isMultiplyDivide(expr.charAt(idx)));
            
            //if multiply or divide is found
            if(idx>=1){
                if(expr.charAt(idx)=='*'){ 
                    expr = new StringBuilder(""+
                            (Integer.parseInt(eval(expr.substring(0, idx)))
                            *Integer.parseInt(eval(expr.substring(idx+1, expr.length())))));
                }else{
                    expr = new StringBuilder(""+
                            (Integer.parseInt(eval(expr.substring(0, idx)))
                            /Integer.parseInt(eval(expr.substring(idx+1, expr.length()))))); 
                }
            }else{
                break;
            }
        }
        
        
        /*process to compute all of ^*/
        while(true){
            idx = expr.length();
            while((--idx)>=1 && expr.charAt(idx)!='^');
            
            //if ^ is found
            if(idx>=1){
                expr = new StringBuilder(""+
                        Math.abs(Integer.parseInt(eval(expr.substring(0, idx))))
                             +Math.abs(Integer.parseInt(eval(expr.substring(idx+1, expr.length())))));
                
            }else{
                break;
            }
        }
        
        
        return expr.toString();
    }
    
  
    
    /**
     * check if an operator is plus or minus
     */
    public static boolean isPlusMinus(char oper){
        return oper=='+' || oper=='-';
    }
    
    /**
     * check if an operator is multiply or divide
     */
    public static boolean isMultiplyDivide(char oper){
        return oper=='*' || oper=='/';
    }
    
}
