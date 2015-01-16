package capcs.choi.yr20102011.round3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Problem3 {
    public static void main(String args[]) throws IOException{
        Scanner in=new Scanner(new FileReader("DATA3.txt"));
        PrintWriter out=new PrintWriter(new FileWriter("OUT3.txt"));
        
        for(int i=0;i<5;i++){
            out.println(find(in.nextInt()));
            out.flush();
        }
        out.close();
        in.close();
    }
    
    public static int find(int n){
        if(n==0){
            return 1;
        }
        
        int ans = 0;
        
        if(n>=2){
            ans+=find(n-2);
        }
        
        while(n>=2){
            n-=2;
            ans+=find(n)*2;
        }
        
        return ans;
    }
}
