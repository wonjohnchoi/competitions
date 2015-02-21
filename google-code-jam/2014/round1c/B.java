import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            List<String> cars = new ArrayList<String>();
            boolean fail = false;
            for (int i = 0; i < N; i++) {
                String car = dedup(sc.next());
                cars.add(car);
                for (int j = 0; j < car.length(); j++) {
                    for (int k = j + 1; k < car.length(); k++) {
                        if (car.charAt(j) == car.charAt(k)) {
                            fail = true;
                        }
                    }
                }
            }
            // if (tc == 74)
            //    out.println("cars : " + cars);
            long cnt = 1;
            for (char c = 'a'; c <= 'z' && !fail; c++) {
                int i = 0;
                List<String> both = new ArrayList<String>();
                List<String> start = new ArrayList<String>();
                List<String> end = new ArrayList<String>();
                boolean inMid = false;
                while (i < cars.size() && !fail) {
                    String car = cars.get(i);
                    if (car.indexOf(c) >= 0) {
                        boolean startB = car.charAt(0) == c;
                        boolean endB = car.charAt(car.length() - 1) == c;
                        if (startB || endB) cars.remove(i);
                        if (startB && endB) {
                            both.add(car);
                        } else if (startB) start.add(car);
                        else if (endB) end.add(car);
                        else {
                            inMid = true;
                            i++;
                        }
                    } else {
                        i++;
                    }
                }
                // out.println("C: " + c);
                // out.println("both: " + both + " start: " + start + " end: " + end);
                if (start.size() >= 2 || end.size() >= 2) fail = true;
                else if (inMid && (start.size() > 0 || end.size() > 0 || both.size() > 0)) fail = true;
                else {
                    String newCar = end.size() == 1 ? end.get(0) : "";
                    for (String car : both) newCar += car;
                    newCar += start.size() == 1 ? start.get(0) : "";
                    cnt = (cnt * fac(both.size())) % MOD;
                    if (!newCar.equals("")) cars.add(dedup(newCar));
                }
            }
            cnt = (cnt * fac(cars.size())) % MOD;
            if (fail) cnt = 0;
            out.printf("Case #%d: %s\n", tc, cnt + "");
        }
    }
    public static long MOD =  1000000007;
    public static long fac(int i) {
        if (i <= 1) return 1;
        return (fac(i - 1) * i) % MOD;
    }
    public static String dedup(String s) {
        String ret = "";
        for (char c : s.toCharArray()) {
            if (ret.equals("")) {
                ret += c;
            } else if (ret.charAt(ret.length() - 1) != c) {
                ret += c;
            }
        }
        return ret;
    }
}
