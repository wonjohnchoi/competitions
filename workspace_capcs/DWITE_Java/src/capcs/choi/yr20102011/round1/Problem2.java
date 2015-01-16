package capcs.choi.yr20102011.round1;


import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 Round1 Problem2
 * @author W.A.R.
 * @boss Wonjohn Choi 
 * @left_hand Alex Ciuba 
 * @right_hand Rohit Deora
 * @date Oct. 27, 2010
 * @lang Java
 *
 */
public class Problem2 {
    private Scanner in;
    private PrintWriter out;
    
    /*
     * main method
     */
    public static void main(String args[]){
        new Problem2(2);
    }
    
    /**
     * constructor
     */
    public Problem2(int problemNumber){
        initIO(problemNumber);
        
        
        for(int _it=0;_it<5;_it++){
            String map = in.next();
            int pos = map.indexOf("*");
            
            
            for(int dir=0;dir<5;dir++){
                char RL = in.next().charAt(0);
                
                if(RL=='R'){
                    pos +=1;
                    if(pos==5){
                        pos=4;
                    }
                }else{
                    pos-=1;
                    if(pos==-1){
                        pos=0;
                    }
                }
                
            }
            String output = ".....";
            output = output.substring(0, pos)+"*"+output.substring(pos+1, 5);
            
            out.println(output);
            
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
