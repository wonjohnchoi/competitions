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
        debug.turnOff();
        initIO(problemNumber);
        
        for(int i=0;i<5;i++){
            char[][] map = new char[10][10];
            
            for(int r=0;r<map.length;r++){
                map[r] = in.next().toCharArray();
                
                for(int c=0;c<map[r].length;c++){
                    
                }
            }
            
            int counter=0;
            boolean done = false;
            boolean fail = false;
            
            int[][] moves= {{1,0},{-1,0},{0,1},{0,-1}};
            
            while(!done && !fail){
                counter++;
                
                fail = true;
                done = true;
                
                for(int r=0;r<map.length;r++){
                    for(int c=0;c<map[r].length;c++){
                        if(map[r][c]=='F'){
                            for(int d=0;d<4;d++){
                                   int r2=r+moves[d][0];
                                   int c2=c+moves[d][1];
                                   
                                   r2 = Math.max(0, r2);
                                   c2 = Math.max(0, c2);
                                   r2 = Math.min(9, r2);
                                   c2 = Math.min(9, c2);
                                   
                                   if(map[r2][c2]=='T'){
                                       map[r2][c2]='B';
                                       fail=false;
                                   }
                                
                            }
                            
                            
                        }else if(map[r][c]=='T'){
                            done = false;
                        }
                    }
                }
                
                for(int r=0;r<map.length;r++){
                    for(int c=0;c<map[r].length;c++){
                        if(map[r][c]=='B'){
                            map[r][c]='F';
                        }
                    }
                    
                   
                } 
               
                if(fail){
                    out.println(-1);
                    
                }else if(done){
                    out.println(counter);
                }
                
                
                
                
               
            }
            
            in.next();
        }
     
        out.flush();
        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
    }
    
    static class Tree{
        public int r, c;
        
        public Tree(int row, int col){
            r = row;
            c = col;
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
