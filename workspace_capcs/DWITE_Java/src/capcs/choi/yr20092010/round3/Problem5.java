package capcs.choi.yr20092010.round3;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * require to use graph theory
 * @author Wonjohn Choi
 *
 */
public class Problem5 {
	public static void main(String args[]) {
	  //Input/Output
        Scanner sc = null;
        PrintWriter pw = null;
        
        try{
            sc = new Scanner(new FileReader("DATA5.txt"));
            pw = new PrintWriter(new FileWriter("OUT5.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //for each input
        for(int it=0;it<5;it++){
            int n = sc.nextInt();
            boolean[][] edge = new boolean[n][n];
            
            //for each connections
            for(int line=0;line<n;line++){
                int x = sc.nextInt()-1;
                int y = sc.nextInt()-1;
                edge[x][y] = true; //connected
                edge[y][x] = true; 
            }
            
            //variable to store # of colors used
            int maxColor = 1;
            int[] color = new int[n];
            
            //foro each node
            for(int node=0;node<n;node++){                
                //for other nodes
                for(int otherNode=0;otherNode<n;otherNode++){
                    //connected?
                    if(edge[node][otherNode] && node!=otherNode){
                        //has same color?
                        if(color[node]==color[otherNode]){
                            //then change the color of others!
                            color[otherNode]++;
                            
                            //keep track of maximum # of colors!
                            if((color[otherNode]+1)>maxColor){
                                maxColor = color[otherNode]+1;
                            }
                        }
                    }
                }
            }
            
            //too much color!
            if(maxColor>4){
                pw.println(0);
            }else{
                pw.println(maxColor);
            }
        }
        
        //finish using I/O
        sc.close();
        pw.close();
	}
}