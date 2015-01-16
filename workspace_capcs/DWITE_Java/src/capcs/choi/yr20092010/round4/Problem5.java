package capcs.choi.yr20092010.round4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/**
 * http://dwite.ca/questions/ice_maze.html
 * 
 * Used breadth first search to avoid a huge loop
 * @author Wonjohn Choi
 * 
 */
public class Problem5 {
    /**
     * class to store location (row, col)
     * @author Wonjohn Choi
     *
     */
    static class Item{
        protected int r,c;
        public Item(int row, int col){
           r=row; c=col;
        }
        
        public boolean equals(Object other){
            if(other instanceof Item){
                return r==((Item)other).r && c==((Item)other).c;
            }
            return false;
        }
        
        public Item clone(){
            Item i = new Item(r,c);
            i.dist = dist;
            return i;
        }
        
        //for bread-first search
        protected int dist=0;
    }
    
    static enum Direction{
        NORTH, SOUTH, EAST, WEST;
        
        public static final Direction[] directions = Direction.values();
        
        public static Direction getDirection(int pos){
            return directions[pos];
        }
    }
    
    static int SIZE = 10;
    static char[][] map = new char[SIZE][SIZE];
    static Item[] interest = new Item[6];
    
    public static void main(String args[]) {
        
        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA5.txt"));
            pw = new PrintWriter(new FileWriter("OUT5.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
        //get input
        for(int r=0;r<SIZE;r++){
            map[r] = sc.next().toCharArray();
            for(int c=0;c<SIZE;c++){
                if(map[r][c]!='.' && map[r][c]!='#'){
                    interest[(int)(map[r][c]-'A')] = new Item(r,c);
                }
            }
        }
        
        //for each point of interest, call findDist function
        for(int it=0;it<5;it++){
            pw.println(findDist(interest[it], interest[it+1]));
        }
        
        sc.close();
        pw.close();
    }

    /**
     * find the next letter and return the distance
     * Use breadth-first search
     */
    private static int findDist(Item from, Item to) {
        Vector<Item> visited = new Vector<Item>(); //to break loop
        Queue<Item> q =new LinkedList<Item>(); //to store the next search spot
        q.add(from); //add the initial search spot
        visited.add(from);
        
        //variable to store the minimum distance
        int min = Integer.MAX_VALUE;
        
        //while no more search is required
        while(!q.isEmpty()){
            Item cur = q.remove();
            
            //if found
            if(cur.equals(to)){
                
                //check if the current distance is shorter
                if(min>cur.dist){
                    min=cur.dist;
                }
            }else{
                //for each direction
                for(int dir=0;dir<4;dir++){
                    Item i = cur.clone();
                    move(i, Direction.getDirection(dir));
                    
                    int idx = visited.indexOf(i);
                    if(idx==-1){
                        q.add(i);
                        visited.add(i);
                    }else{
                        if(visited.get(idx).dist>i.dist){
                            q.add(i);
                            visited.remove(idx);
                            visited.add(i);
                        }
                    }
                }
            }
         
        }
        
        return min;
    }
    
    /**
     * move to a direction and return distance
     */
    private static void move(Item item, Direction dir){
        int r=0,c=0;
        switch (dir){
        case NORTH: r=-1;break;
        case SOUTH: r=1;break;
        case EAST:c=1;break;
        case WEST:c=-1;
        }
                
        while(inRange(item) && map[item.r][item.c]!='#'){
            item.r+=r;
            item.c+=c;
            item.dist++;
        }
        
        item.r-=r;
        item.c-=c;
        item.dist--;
        
    }
    
    /**
     * check if a value is out of range in map
     */
    private static boolean inRange(Item item){
        return 0<=item.r && item.r<SIZE && 0<=item.c && item.c<SIZE;
    }
}