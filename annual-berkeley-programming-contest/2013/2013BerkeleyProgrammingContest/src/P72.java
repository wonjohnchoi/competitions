import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class P72 {
    static Scanner in = new Scanner(System.in);
    static boolean debug = false;
    public static void d(String str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(double str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(boolean str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(float str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(Object str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void o(String str) {
        System.out.println(str);
    }
    public static void o(int str) {
        System.out.println(str);
    }
    public static void o(boolean str) {
        System.out.println(str);
    }
    public static void o(float str) {
        System.out.println(str);
    }
    public static void o(long str) {
        System.out.println(str);
    }
    public static void o(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        debug = true;
        HashMap<Character, Integer> chrToVal = new HashMap<Character, Integer>();
        chrToVal.put('K', 13);
        chrToVal.put('Q', 12);
        chrToVal.put('J', 11);
        chrToVal.put('T', 10);
        chrToVal.put('A', 1);
        for (int i = 2; i <= 9; i++) {
            chrToVal.put((i+"").charAt(0), i);
        }
        d(chrToVal);
        int deal = 0;
        while (in.hasNext()) {
            deal ++;
            ArrayList<Integer>[] vals = new ArrayList[4];
            int tot = 0;
            int m = 0;
            for (int i = 0; i < 4; i++) {
                String cards = in.next();
                m = cards.length();
                vals[i] = new ArrayList<Integer>();
                for (int j = 0; j < cards.length(); j++) {
                    vals[i].add(chrToVal.get(cards.charAt(j)));
                    tot += vals[i].get(vals[i].size() - 1);
                }
            }
            int[][][][] dp = new int[m + 1][m + 1][m + 1][m + 1];
            int[][][][][] choice = new int[m + 1][m + 1][m + 1][m + 1][4];
            /*
            dp[0][0][0][0] = 0;
            for (int i = 1; i < m + 1; i++) {
                dp[i][0][0][0] = vals[0].get(i - 1) + dp[i - 2][0][0][0];
            }
            
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < m + 1; j++) {
                    for (int k = 0; k < m + 1; k++) {
                        for (int l = 0; l < m + 1; l++) {
                            
                        }
                    }
                }
                
            }*/
            d(tot);
            //dp[0][0][0][0] = 0;
           o(String.format("Deal %d: First player wins %d out of %d", deal, get(dp, m, m, m, m, vals, choice), tot));
        }

        in.close();
        System.exit(0);
    }
    static int get(int[][][][] dp, int a, int b, int c, int d, ArrayList<Integer>[] vals, int[][][][][] choice) {
        //d(a + " " + b + " " + c + " " + d);
        
        int used;
        
        if (a == 0 && b == 0 && c ==0 && d == 0) {
            return 0;
        }

        if (dp[a][b][c][d] == 0) {
            if (a + choice[a][b][c][d][0]> 0) {
                used = vals[0].remove(vals[0].size() - 1);
                get(dp, a - 1, b, c, d, vals, choice);
                int val = used+get(dp, a - 1 + choice[a - 1][b][c][d][0], b + choice[a][b][c][d][1], c + choice[a][b][c][d][2], d + choice[a][b][c][d][3], vals, choice);
                if (val > dp[a][b][c][d]) {
                    dp[a][b][c][d] = val;
                    choice[a][b][c][d] = new int[]{-1, 0, 0, 0};
                }
                vals[0].add(used);
            }
            if (b + choice[a][b][c][d][1]> 0) {
                used = vals[1].remove(vals[1].size() - 1);
                get(dp, a, b - 1, c, d, vals, choice);
                int val = used+get(dp, a + choice[a][b][c][d][0], b - 1 + choice[a][b - 1][c][d][1], c + choice[a][b][c][d][2], d + choice[a][b][c][d][3], vals, choice);
                if (val > dp[a][b][c][d]) {
                    dp[a][b][c][d] = val;
                    choice[a][b][c][d] = new int[]{0, -1, 0, 0};
                }
                vals[1].add(used);
            }
            if (c + choice[a][b][c][d][2]> 0) {
                used = vals[2].remove(vals[2].size() - 1);
                get(dp, a, b, c - 1, d, vals, choice);
                int val = used+get(dp, a + choice[a][b][c][d][0], b + choice[a][b][c][d][1], c - 1 + choice[a][b][c - 1][d][2], d + choice[a][b][c][d][3], vals, choice);
                if (val > dp[a][b][c][d]) {
                    dp[a][b][c][d] = val;
                    choice[a][b][c][d] = new int[]{0, 0, -1, 0};
                }
                vals[2].add(used);
            }
            if (d + choice[a][b][c][d][3]> 0) {
                used = vals[3].remove(vals[3].size() - 1);
                get(dp, a, b, c, d - 1, vals, choice);
                int val = used+get(dp, a + choice[a][b][c][d][0], b + choice[a][b][c][d][1], c + choice[a][b][c][d][2], d -1 + choice[a][b][c][d - 1][3], vals, choice);
                if (val > dp[a][b][c][d]) {
                    dp[a][b][c][d] = val;
                    choice[a][b][c][d] = new int[]{0, 0, 0, -1};
                }
                vals[3].add(used);
            }
        }
        return dp[a][b][c][d];
    }
    
    

}
/*

K7 2A 3T J8
K Q J T
A2 A2 A2 A2
2A 2A 2A 2A

 */
