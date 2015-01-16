package capcs.choi.yr20102011.round3;



import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @date 
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
        
        for(int i=0;i<5;i++){
            int N = in.nextInt();
            int counter= 0;
            for(int a=1;a*a<=N;a++){
                counter+= Math.max(0, N/a-a+1);
            }
            out.println(counter);
        }
   
        
        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
    }
    
    public int fillOne(char[] seats){
        int destSeat = 0;
        int maxDest = Integer.MIN_VALUE;
        
        if(seats[0]=='E'){
            destSeat = 1;
        }else{
            for(int n = seats.length-1;n>=0;n--){
                if(seats[n]=='E'){
                    int tmp = minDist(seats, n);
                    if(maxDest<=tmp){
                        maxDest = tmp;
                        destSeat = n+1;
                    }
                }
            }
        }
        
        seats[destSeat-1]='F';
        
        return destSeat;
    }
    
    public int minDist(char[] seats, int currentSeat){
        int dist = Integer.MAX_VALUE;
        
        for(int i=0;i<seats.length;i++){
            if(seats[i]=='F'){
                dist = Math.min(Math.abs(currentSeat-i), dist);
            }
        }
        
        return dist;
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
