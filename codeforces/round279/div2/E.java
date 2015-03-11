import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        ArrayList<String> hist = new ArrayList<String>();
        char[] prev = new char[] {'0'};
        for (int k = 0; k < N; k++) {
            char[] s = in.next().toCharArray();
            if (prev.length < s.length) {
                for (int i = 0; i < s.length; i++) {
                    if (s[i] == '?') {
                        if (i == 0) s[i] = '1';
                        else s[i] = '0';
                    }
                }
            } else if (prev.length > s.length) {
                NO();
            } else {
                boolean big = false;
                boolean small = false;
                for (int i = 0; i < s.length; i++) {
                    if (s[i] == '?') continue;
                    if (s[i] < prev[i]) {
                        for (int j = i + 1; j < s.length; j++) {
                            if (s[j] == '?') s[j] = '0';
                        }
                        small = true;
                        break;
                    }
                    else if (s[i] > prev[i]) {
                        for (int j = i + 1; j < s.length; j++) {
                            if (s[j] == '?') s[j] = '0';
                        }
                        big = true;
                        break;
                    }
                }
                if (!big) {
                    boolean has = false;
                    for (int i = s.length - 1; i >= 0; i--) {
                        if (s[i] == '?' && prev[i] != '9') {
                            s[i] = (char) (prev[i] + 1);
                            for (int j = i + 1; j < s.length; j++) {
                                if (s[j] == '?') s[j] = '0';
                            }
                            has = true;
                            break;
                        }
                    }
                    if (!has) NO();
                }
                for (int i = 0; i < s.length; i++) {
                    if (s[i] == '?') s[i] = prev[i];
                }
            }
            prev = s;
            hist.add(new String(s));
        }
        out.println("YES");
        for (String s : hist) {
            out.println(s);
        }
    }
    static void NO() {
        out.println("NO");
        System.exit(0);
    }
}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    public InputReader(InputStream stream) {
	reader = new BufferedReader(new InputStreamReader(stream), 32768);
	tokenizer = null;
    }
    public String next() {
	while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	    try {
		tokenizer = new StringTokenizer(reader.readLine());
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	return tokenizer.nextToken();
    }
    public double nextDouble() {
	return Double.parseDouble(next());
    }
    public long nextLong() {
	return Long.parseLong(next());
    }
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
