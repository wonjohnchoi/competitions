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
public class Problem1 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();
    
    /*
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
        
        
        for(int _it=0;_it<5;_it++){
            double x1,x2,y1,y2;
            x1 = in.nextDouble();
            y1 = in.nextDouble();
            x2 = in.nextDouble();
            y2 = in.nextDouble();
            
            double slope = (y2-y1)/(x2-x1);
            int counter=0;
            for(double x=x1+1;x<x2;x++){
                double y = equation(slope,x1,y1,x);
                
                if(y==(int)y){
                    counter++;   
                }
            }
            
            System.out.println(counter);
            
            //out.println(GCD(Math.abs(x2-x1), Math.abs(y2-y1))-1);
        }
        
        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
    }
    
    public double equation(double slope, double x1, double y1, double x2){
        return slope*(x2-x1)+y1;
    }
    
    public int GCD (int x, int y){
        if(x>y){
            return GCD(x-y,y);
        }else if(x<y){
            return GCD(x,y-x);
        }
        return x;
        
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
