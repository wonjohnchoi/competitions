package capcs.choi.yr20102011.round3;



import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * DWITE 2010-2011 
 * @author Wonjohn Choi
 * @date 
 * @lang Java
 *
 */
public class Problem5 {
    private Scanner in;
    private PrintWriter out;
    private static Debug debug = new Debug();

    /*
     * main method
     */
    public static void main(String args[]){
        new Problem5(5);
    }

    /**
     * constructor
     */
    public Problem5(int problemNumber){
        double startTime = System.currentTimeMillis();
        debug.turnOff();
        initIO(problemNumber);

        for(int i=0;i<5;i++){
           int n= in.nextInt();
           
           String[] dic = new String[n];
           
           
           for(int j=0;j<n;j++){
               dic[j] = in.next();
           }
           
           for(int j=0;j<5;j++){
               String ans = "";
               String pattern = in.next().replace('?', '.').replace("*", ".*");
               Pattern p = Pattern.compile(pattern);
               for(String word: dic){
                   if(p.matcher(word).matches()){
                       ans+=word+", ";
                   }
               }
               
               
               if(ans.equals("")){
                   out.println("NO MATCH");
               }else{
                   out.println(ans.substring(0, ans.length()-2));
               }
           }
            
            

          
        }


        closeIO();
        debug.println(""+(System.currentTimeMillis()-startTime)/1000);
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
