package capcs.choi.yr20092010.round4;

/**
 * http://dwite.ca/questions/verifying_distributed_work.html
 * @author Wonjohn Choi
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Problem2 {

    public static void main(String[] args) throws IOException {

        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA2.txt"));
            pw = new PrintWriter(new FileWriter("OUT2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //for each input
        for (int it = 0; it < 5; it++) {
            int nVote = sc.nextInt();
            Vector<Integer> arg = new Vector<Integer>(nVote);
            Vector<Integer> freq = new Vector<Integer>(nVote);
            
            //process each vote
            for(int v=0;v<nVote;v++){
                int vote = sc.nextInt();
                int idx = arg.indexOf(vote); //search for new vote to see whether there was any same vote before
                
                //there wan't
                if(idx==-1){
                    //register for a new vote
                    arg.add(vote);
                    //set frequency zero
                    freq.add(1);
                }else{
                    //increment frequency for this existing vote
                    freq.set(idx, freq.get(idx)+1);
                }
            }
            
            boolean done = false; //check whether it's determined
            int maxFreq = 0; //find out max frequency
            
            //for each frequency
            for(Integer frequency: freq){
                //if one vote takes majority
                if(frequency*2>=nVote){
                    pw.println("Verified");
                    done = true;
                }
                
                //process to check if there is bigger frequency
                if(maxFreq<frequency){
                    maxFreq = frequency;
                }
            }
            
            if(done)continue;
            
            if(Collections.frequency(freq, maxFreq)<=1){
                pw.println("Unverified");
            }else{
                pw.println("Unknown");
            }
        }

        pw.close();
        sc.close();
    }
}