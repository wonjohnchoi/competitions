package capcs.choi.yr20102011.round4;



import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @lang Java
 *
 */
public class Problem3 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();
    
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
        double startTime = System.currentTimeMillis();
        debug.turnOff();
        initIO(problemNumber);
        
        for(int i=0;i<5;i++){
            int input =in.nextInt();
            String str = Integer.toBinaryString(input);
            int counter = 0;
            for(Character c: str.toCharArray()){
                if(c=='1')
                    counter+=1;
            }
            int other = -1;
            
            while(other!=counter){
                other=0;
                input+=1;
                str = Integer.toBinaryString(input);
                for(Character c: str.toCharArray()){
                    if(c=='1')
                        other+=1;
                }
            }
            out.println(input);
        }
        
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
