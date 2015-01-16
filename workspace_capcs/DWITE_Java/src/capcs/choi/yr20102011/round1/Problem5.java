package capcs.choi.yr20102011.round1;


import java.io.*;
import java.util.*;

/**
 * DWITE 2010-2011 Round1 Problem5
 * @author W.A.R.
 * @boss Wonjohn Choi 
 * @left_hand Alex Ciuba 
 * @right_hand Rohit Deora
 * @date Oct. 27, 2010
 * @lang Java
 *
 */
public class Problem5 {
    private Scanner in;
    private PrintWriter out;
    
    /**
     * main method
     */
    public static void main(String args[]){
        new Problem5(5);
    }
    
    /**
     * constructor
     */
    public Problem5(int problemNumber){
        initIO(problemNumber);
        
        
        
        for(int _it=0;_it<5;_it++){
            Queue<Robot> robots = new LinkedList<Robot>();
            char map[][] = new char[10][10];
            int endRow = 0, endCol = 0;
            
            
            for(int r=0;r<10;r++){
                map[r] = in.next().toCharArray();
                
                for(int c=0;c<10;c++){
                    if(map[r][c]=='A'){
                        robots.add(new Robot(r,c,0));
                    }else if(map[r][c]=='B'){
                        endRow=r;
                        endCol=c;
                    }
                    
                }
            }
            
            while(!robots.isEmpty()){
                Robot cur = robots.remove();
                
                if(cur.row==endRow && cur.col==endCol){
                    out.println(cur.count+"");
                    break;
                }
                
                int wallR, wallL, wallU, wallD;
                wallR=wallL=cur.col;
                wallU=wallD=cur.row;
                while(wallR<=9 && map[cur.row][wallR]!='#')wallR++;
                while(0<=wallL && map[cur.row][wallL]!='#')wallL--;
                while(0<=wallU && map[wallU][cur.col]!='#')wallU--;
                while(wallD<=9 && map[wallD][cur.col]!='#')wallD++;
                
                
                robots.add(new Robot(cur.row, wallR-1, cur.count+1));
                robots.add(new Robot(cur.row, wallL+1, cur.count+1));
                robots.add(new Robot(wallU+1, cur.col, cur.count+1));
                robots.add(new Robot(wallD-1, cur.col, cur.count+1));
               
                
            }
            
            in.next();
        }
        
   
        
        closeIO();
    }
    
    static class Robot{
        int row, col, count;
        
        Robot(int r, int c, int count){
            this.count=count;
            row=r;
            col=c;
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
