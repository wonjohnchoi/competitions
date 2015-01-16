package capcs.choi.yr20092010.round3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Wonjohn Choi
 * 
 */
public class Problem4 {
    static final int SIZE = 5;
    
    public static void main(String[] args) {
        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA4.txt"));
            pw = new PrintWriter(new FileWriter("OUT4.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //for each input
        for(int it=0;it<5;it++){
            //the map
            String[][] spiral={
                    {"20","19","18","17","16"},
                    {"21","6","5","4","15"},
                    {"22","7","0","3","14"},
                    {"23","8","1","2","13"},
                    {"24","9","10","11","12"}
                    
            };
            
            
            int num = sc.nextInt(); //get input
            
            //Customizing by filling "." for spots with too high value
            for(int row=0;row<SIZE;row++){
                for(int col=0;col<SIZE;col++){
                    if(Integer.parseInt(spiral[row][col])>num){
                        spiral[row][col] = "."; 
                    }
                }
            }
            
            int minRow=-1, maxRow=SIZE, minCol=-1, maxCol=SIZE;
            
            //to find min/max row and column where it is not empty
            while(isEmptyRow(spiral, ++minRow));
            while(isEmptyRow(spiral, --maxRow));
            while(isEmptyColumn(spiral, ++minCol));
            while(isEmptyColumn(spiral, --maxCol));
            
            //print output
            for(int row=minRow;row<=maxRow;row++){
                for(int col=minCol;col<=maxCol;col++){
                    pw.print(spiral[row][col]);
                }
                pw.println();
            }
            
   
        }
        
        sc.close();
        pw.close();

    }
    
    /**
     * check if a row is empty
     */
    public static boolean isEmptyRow(String newSpiral[][], int row){
        for(int col=0;col<SIZE;col++){
            if(!newSpiral[row][col].equals(".")){
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * check if a column is empty
     */
    public static boolean isEmptyColumn(String newSpiral[][],int col){
        for(int row=0;row<SIZE;row++){
            if(!newSpiral[row][col].equals(".")){
                return false;
            }
        }
        
        return true;
    }
}