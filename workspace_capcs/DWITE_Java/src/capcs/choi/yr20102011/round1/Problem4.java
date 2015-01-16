package capcs.choi.yr20102011.round1;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 * DWITE 2010-2011 Round1 Problem4
 * @author W.A.R.
 * @boss Wonjohn Choi 
 * @left_hand Alex Ciuba 
 * @right_hand Rohit Deora
 * @date Oct. 27, 2010
 * @lang Java
 *
 */
public class Problem4 {
    private Scanner in;
    private PrintWriter out;
    
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
        initIO(problemNumber);
        
        
        for(int _it=0;_it<5;_it++){
            int n = in.nextInt();
            
            Vector<Position> group = new Vector<Position>(n);
            
            for(int x=0;x<n;x++){
                group.add(new Position(in.nextInt(), in.nextInt()));
            }
            
            Collections.sort(group);
            
            int count=0;
            for(int i=0;i<group.size();i++){
                for(int j=i+1;j<group.size();j++){
                    for(int k=j+1;k<group.size();k++){
                        Position[] cur = new Position[3];
                        cur[0] = group.get(i);
                        cur[1] = group.get(j);
                        cur[2] = group.get(k);
                        
                        boolean fail = false;
                        boolean top,down,left,right;
                        top=down=left=right=false;
                        
                        for(int eq = 0; eq<3;eq++){
                            Position one = cur[eq];
                            Position two = cur[(eq+1)%3];
                            
                            if(one.x==two.x){
                                if(one.y<=0 && 0<=two.y){
                                    if(one.x>0){
                                        right=true;
                                    }else if(one.x<0){
                                        left=true;
                                    }else{
                                        fail=true;
                                    }
                                }
                            }else if(one.y==two.y){
                                if(one.x<=0 && 0<=two.x){
                                    if(one.y>0){
                                        top=true;
                                    }else if(one.y<0){
                                        down=true;
                                    }else{
                                        fail=true;
                                    }
                                }
                                
                            }else{
                            
                                
                                double yInter = getY(0, one, two);
                                double xInter = getX(0, one, two);
                                
                                if(Math.min(one.y, two.y)<=yInter && yInter <=Math.max(one.y, two.y)){
                                    if(yInter==0){
                                        fail = true;
                                    }else if(yInter>0){
                                        top=true;
                                    }else{
                                        down=true;
                                    }
                                }
                                if(Math.min(one.x, two.x)<=xInter && xInter <=Math.max(one.x, two.x)){
                                    if(xInter==0){
                                        fail = true;
                                    }else if(xInter>0){
                                        right=true;
                                    }else{
                                        left=true;
                                    }
                                }
                            }

                            
                        }
                        
                        if(!fail){
                            if(top && down && left && right){
                                count++;
                            }
                        }
                        
                    }
                }
            }
            
            out.println(count+"");
            out.flush();
        }
   
        
        closeIO();
    }
    
    public double getY(int x, Position one, Position two){
        return ((double)x-(double)one.x)*((double)two.y-(double)one.y)/((double)two.x-(double)one.x)+(double)one.y;
    }
    
    public double getX(int y, Position one, Position two){
        return ((double)two.x-(double)one.x)/((double)two.y-(double)one.y) * ((double)y-(double)one.y)+(double)one.x;
    }
    
    static class Position implements Comparable<Position>{
        int x,y;
        
        Position(int newX, int newY){
            x=newX;
            y=newY;
        }
        

        @Override
        public int compareTo(Position o) {           
            if(x!=o.x){
                return o.x-x;
            }else if(y!=o.y){
                return o.y-y;
            }
            return 0;
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
