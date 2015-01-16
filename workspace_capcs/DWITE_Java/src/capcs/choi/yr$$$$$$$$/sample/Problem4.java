package capcs.choi.yr$$$$$$$$.sample;


import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @date 
 * @lang Java
 *
 */
public class Problem4 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();
    
    /*
     * main method
     */
    public static void main(String args[]){
        new Problem4(4);
    }
        
    /**
     * constructor
     */
    public Problem4(int problemNumber){
        double startTime = System.currentTimeMillis();
        debug.turnOn();
        initIO(problemNumber);
        
        
   
        
        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
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

    static class Debug{
        private boolean debugFlag;

        public void turnOn(){
            debugFlag = true;
        }
        
        public void turnOff(){
            debugFlag = false;
        }
        
        public void print(String str){
            if(debugFlag){
                System.out.print(str);
            }
        }
        
        public void println(String str){
            if(debugFlag){
                System.out.println(str);
            }
        }
    }
}
