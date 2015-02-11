import java.util.*;
import java.io.*;

public class C {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("C.in"));
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            ArrayList<Event> events = new ArrayList<Event>();
            ArrayList<Event> inside = new ArrayList<Event>();
            ArrayList<Event> outside = new ArrayList<Event>();
            
            int N = sc.nextInt();
            int numE = 0;
            int numL = 0;
            for (int n = 0; n < N; n++) {
                Event e = new Event(sc.nextChar(), sc.nextInt());
                events.add(e);
                if (e.enter) {
                    numE += 1;
                } else {
                    numL += 1;
                }
            }
            int minNumS = Math.max(numL - numE, 0);
            int maxNumS = N;
            int[] ss = new int[maxNumS - minNumS + 1];
            for (int si = 0; si < ss.length; si++) {
                ss[si] = si + minNumS;
            }
            Collections.binarySearch(ss, T key, Comparator<? super T> c)
            valid(0, new ArrayList<Event>(events))
        }
    }

    boolean static valid(int s, ArrayList<Event> events) {
        
    }

    public static class Event {
        boolean enter;
        int id;
        public Event(char s, int id) {
            this.enter = s == 'E';
            this.id = id;
        }
    }
}
