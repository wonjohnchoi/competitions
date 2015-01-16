package capcs.choi.yr20102011.round1;


import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 Round1 Problem3
 * @author W.A.R.
 * @boss Wonjohn Choi 
 * @left_hand Alex Ciuba 
 * @right_hand Rohit Deora
 * @date Oct. 27, 2010
 * @lang Java
 *
 */
public class Problem3 {
    private Scanner in;
    private PrintWriter out;
    
    /*
     * main method
     */
    public static void main(String args[]){
        new Problem3(3);
    }
    
    /**
     * constructor
     */
    public Problem3(int problemNumber){
        initIO(problemNumber);
        
        
        for(int _it=0;_it<5;_it++){
            out.println(countSquare(in.nextInt(), in.nextInt())+"");
        }
   
        closeIO();
    }
    
    public int countSquare(int x, int y){
        if(x==0 || y==0){
            return 0;
        }else if(x==1){
            return y;
        }else if(y==1){
            return x;
        }else{
            int small = Math.min(x, y);
            int len =1;
            
            while(len*2<=small){
                len*=2;
            }
            return 1+countSquare(x-len, len)+countSquare(len,y-len)+countSquare(x-len, y-len);
        }
        
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
