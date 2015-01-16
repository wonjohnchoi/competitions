package capcs.choi.yr20102011.round4;



import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @lang Java
 *
 */
public class Problem2 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();
    
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
        double startTime = System.currentTimeMillis();
        debug.turnOff();
        initIO(problemNumber);
        
        boolean primes[] = new boolean[50000];
        Arrays.fill(primes,true);
        for(int i=2;i<(int)Math.sqrt(primes.length);i++){
            if(primes[i]){
                for(int j=i*i;j<(int)primes.length/i;j+=i){
                    primes[j]=false;
                }
            }
        }
        
        for(int i=0;i<5;i++){
            int input = in.nextInt();
            int cur = 2;
            
            while(true){
                if(primes[cur]){
                    input-=1;
                    if(input==0){
                        break;
                    }
                }
                cur+=1;
            }
            
            input = cur;
            cur=2;
            while(true){
                if(primes[cur]){
                    input-=1;
                    if(input==0){
                        break;
                    }
                }
                cur+=1;
            }
            
            out.println(cur);
            
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
