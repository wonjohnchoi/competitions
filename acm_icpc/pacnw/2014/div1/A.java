import java.util.*;
import java.io.*;
import java.util.regex.*;

// Solved.
public class A {
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(new FileReader("A.in"));
        int n = in.nextInt();
        Pattern p = Pattern.compile("(-?[0-9?]+)([*+-])(-?[0-9?]+)=(-?[0-9?]+)");
        for (int i = 0; i < n; i++) {
            String line = in.next();

            int ans = -1;
            for (int j = 0; j < 10; j++) {
                String guess = line.replace("?", j + "");
                Matcher m = p.matcher(guess);
                if (!m.matches() || m.groupCount() != 4) {
                    ans = -1;
                } else {
                    long oper1 = Long.parseLong(m.group(1));
                    long oper2 = Long.parseLong(m.group(3));
                    long res = Long.parseLong(m.group(4));

                    if ((m.group(1).length() > 1 && oper1 == 0)
                        || (m.group(3).length() > 1 && oper2 == 0)
                        || (m.group(4).length() > 1 && res == 0)) {
                        continue;
                    }

                    char op = m.group(2).charAt(0);
                    //System.out.println(oper1 + " " + oper2 + " " + res + " " + op);
                    long expectedRes;
                    if (op == '*') {
                        expectedRes = oper1 * oper2;
                    } else if (op == '-') {
                        expectedRes = oper1 - oper2;
                    } else if (op == '+') {
                        expectedRes = oper1 + oper2;
                    } else {
                        expectedRes = -1;
                    }
                    if (res == expectedRes) {
                        ans = j;
                        break;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
