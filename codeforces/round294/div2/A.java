import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        char[] syms = new char[] {'Q', 'R', 'B', 'N', 'P'};
        int[] vals = new int[] {9, 5, 3, 3, 1};
        int[] tots = new int[2];
        while (sc.hasNext()) {
            String line = sc.next();
            for (char c : line.toCharArray()) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < syms.length; j++) {
                        char sym = syms[j];
                        if (i == 1) { // black
                            sym = (char) (sym - 'A' + 'a');
                        }
                        if (c == sym) tots[i] += vals[j];
                    }
                }
            }
        }
        if (tots[0] > tots[1]) ans = "White";
        else if (tots[0] < tots[1]) ans = "Black";
        else ans = "Draw";
        out.println(ans);
    }
}
