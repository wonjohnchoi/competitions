package capcs.choi.yr20092010.round6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/bill_amendments.html
 * @author Wonjohn Choi
 * 
 */
public class Problem3 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("DATA3.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT3.txt"));

        for (int i = 0; i < 5; i++) {
            int billNum = sc.nextInt();

            String billName[] = new String[billNum];
            int billVal[] = new int[billNum];

            for (int j = 0; j < billNum; j++) {
                billName[j] = sc.next();
                billVal[j] = sc.nextInt();
            }

            int amendNum = sc.nextInt();

            String amendName[] = new String[amendNum];
            int amendVal[] = new int[amendNum];

            for (int j = 0; j < amendNum; j++) {
                amendName[j] = sc.next();
                amendVal[j] = sc.nextInt();
            }

            for (int j = 0; j < amendNum; j++) {
                for (int k = 0; k < billNum && amendVal[j] != 0; k++) {
                    if (amendName[j].equals(billName[k])) {
                        if (billVal[k] >= amendVal[j]) {
                            billVal[k] -= amendVal[j];
                            amendVal[j] = 0;
                        } else {
                            amendVal[j] -= billVal[k];
                            billVal[k] = 0;
                        }
                    }
                }
            }

            for (int j = 0; j < billNum; j++) {
                pw.printf("%s %d\n", billName[j], billVal[j]);
            }

            pw.flush();

        }

        pw.close();
        sc.close();
        System.exit(0);
    }
}
