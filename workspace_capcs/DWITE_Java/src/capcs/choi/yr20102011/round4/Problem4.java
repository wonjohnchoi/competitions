package capcs.choi.yr20102011.round4;



import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
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
        
        for(int dd=0;dd<5;dd++){
            int map[][] = new int[10][10];
            for(int i=0;i<10;i++){
                String line = in.next();
                for(int j=0;j<10;j++){
                    map[i][j] = (int)(line.charAt(j)-'0');
                debug.print(map[i][j]+"");
                }
               debug.println("");
            }
            
            Queue<Pos> searchable = new LinkedList<Pos>();
            for(int i=0;i<10;i++){
                searchable.add(new Pos(i,0,0,null));
            }
            
            while(!searchable.isEmpty()){
                Pos cur = searchable.remove();
                
                if(cur.c==9){
                    out.println(cur.cost);
                  
                    break;
                }
                
                int error[][] = {{0,-1},{0,1},{1,0},{-1,0}};
                
               
                
                for(int i=0;i<error.length;i++){
                    int newR = cur.r+error[i][0];
                    int newC =  cur.c+error[i][1];
                    if(newR>=0 && newC>=0 && newR<10 && newC<10){
                        if(Math.abs(map[newR][newC]-map[cur.r][cur.c])<=1){
                            
                            searchable.add(new Pos(newR, newC,cur.cost+1,cur));
                        }
                    }
                }
                
            }
            in.next();
        }
       
        
        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
    }
    
    class Pos{
        int r;
        int c;
        int cost;
        Pos pre;
        Pos(int rr, int cc, int ccost, Pos ppre)
        {
            r=rr;
            c=cc;
            cost=ccost;
            pre=ppre;
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
