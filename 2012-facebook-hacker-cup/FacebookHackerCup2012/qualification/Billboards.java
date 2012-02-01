import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Billboards {
    public static void main(String args[]) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("billboards.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("billboards.out"));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int t = in.nextInt();
        in.nextLine();
        for (int i = 0; i < t; i += 1) {
            String[] line = in.nextLine().split(" ");
            int w = Integer.parseInt(line[0]);
            int h = Integer.parseInt(line[1]);
            // System.out.println(w);
            // System.out.println(Arrays.toString(line));

            for (int fs = 1000; fs >= 1; fs -= 1) {
                ArrayList<String> words = new ArrayList<String>();
                for (int j = 2; j < line.length; j += 1) {
                    words.add(line[j]);
                }

                int lenH = 0;
                while (lenH + fs <= h && words.size() != 0) {
                    lenH += fs;

                    int lenW = 0;

                    while (words.size() != 0) {
                        if (lenW != 0) {
                            lenW += fs;
                        }
                        lenW += words.get(0).length() * fs;
                        // System.out.print(words.get(0) + " ");

                        if (lenW > w) {
                            break;
                        }
                        words.remove(0);
                    }

                    if (words.size() == 0) {
                        break;
                    }
                    // System.out.println();

                }
                if (words.size() == 0) {
                    out.printf("Case #%d: %d%n", i + 1, fs);
                    break;
                }
            }

        }
        out.close();

    }
}
