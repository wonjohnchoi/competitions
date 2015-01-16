package capcs.choi.yr20102011.round1;


import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 Round1 Problem1
 * @author Wonjohn Choi
 * @date Nov. 3th, 2010
 * @lang Java
 *
 */
public class Problem1 {
    private Scanner in;
    private PrintWriter out;
    
    /*
     * main method
     */
    public static void main(String args[]){
        new Problem1(1);
    }
    
    public static int getDates(int day, int month, int year){
        return day+month*30+year*12*30;
    }
    
    /**
     * constructor
     */
    public Problem1(int problemNumber){
        initIO(problemNumber);
        
        int limit = getDates(27,10,1997);
        
        for(int _it=0;_it<5;_it++){
            if(getDates(in.nextInt(),in.nextInt(),in.nextInt())<=limit){
                out.println("old enough");
            }else{
                out.println("too young");
            }
        }
   
        
        closeIO();
    }
    
    /**
     * Set up devices to do I/O
     */
    public void initIO(int problemNumber){
        try {
            in = new Scanner(new FileReader("DATA"+problemNumber+".txt"));
            out = new PrintWriter(new FileWriter("OUT"+problemNumber+".txt"));
        }catch (IOException except) {
            System.err.println("File is missing!");
        }
    }
    
    /**
     * Free memory used for I/O
     */
    public void closeIO(){
        in.close();
        out.close();
    }
}
