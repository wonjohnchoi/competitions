package capcs.choi.yr20102011.round4;



import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @lang Java
 *
 */
public class Problem1 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();
    
    /**
     * main method
     */
    public static void main(String args[]){
        new Problem1(1);
    }
    
    /**
     * constructor
     */
    public Problem1(int problemNumber){
        double startTime = System.currentTimeMillis();
        debug.turnOff();
        initIO(problemNumber);
        
        for(int i=0;i<5;i++){
            int height =in.nextInt();
            int width=height;
            if(height%2==0){
                width-=1;
            }
            char map[][]=new char[height][width];
            
            for(int j=0;j<map.length;j++){
                Arrays.fill(map[j], '.');
            }
            
            int middle = (int) (Math.round(height/2.0)-1);
            int maxError = 0;
            for(int j=0;j<map.length;j++){
                for(int k=0;k<=maxError;k++){
                    map[j][middle-k]='*';
                    map[j][middle+k]='*';
                }
                
                out.println(new String(map[j]));
                
                if(j%2==1)maxError+=1;
            }
            
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
