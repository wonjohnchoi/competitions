package capcs.choi.yr20092010.round6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/rot13_encryption.html
 * @author Wonjohn Choi
 * 
 */
public class Problem1 {
    public static void main(String args[]) throws IOException {
        //I/O
        Scanner sc = new Scanner(new FileReader("DATA1.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            char letter[] = sc.nextLine().toCharArray(); //get input
            
            //for each character
            for (int j = 0; j < letter.length; j++) {
                //if the position is a letter
                if (Character.isLetter(letter[j])) {
                    letter[j] = (char) ((letter[j] - 'A' + 13) % 26 + 'A');
                }
            }

            //output
            pw.println(new String(letter));
        }

        //finish using I/O
        pw.close();
        sc.close();
    }
}
