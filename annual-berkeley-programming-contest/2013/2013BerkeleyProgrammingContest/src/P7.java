import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


class P7 {
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
        //debug = true;
        HashMap<Character, Integer> chrToVal = new HashMap<Character, Integer>();
        chrToVal.put('K', 13);
        chrToVal.put('Q', 12);
        chrToVal.put('J', 11);
        chrToVal.put('T', 10);
        chrToVal.put('A', 1);
        for (int i = 2; i <= 9; i++) {
            chrToVal.put((i+"").charAt(0), i);
        }
        //d(chrToVal);
        int deal = 0;
        while (in.hasNext()) {
            deal ++;
            int[][] vals = new int[4][];
            int tot = 0;
            int m = 0;
            for (int i = 0; i < 4; i++) {
                String cards = in.next();
                m = cards.length();
                vals[i] = new int[m];
                for (int j = 0; j < m; j++) {
                    vals[i][j] = chrToVal.get(cards.charAt(j));
                    tot += vals[i][j];
                }
            }
            int[][][][] dp = new int[m + 1][m + 1][m + 1][m + 1];
            int[][][][][] choice = new int[m + 1][m + 1][m + 1][m + 1][4];
            
            dp[0][0][0][0] = 0;
            
            
            for (int i = 2; i < m + 1; i++) {
                dp[i][0][0][0] = dp[i - 2][0][0][0] + vals[0][i - 1];
                dp[0][i][0][0] = dp[0][i - 2][0][0] + vals[1][i - 1];
                dp[0][0][i][0] = dp[0][0][i - 2][0] + vals[2][i - 1];
                dp[0][0][0][i] = dp[0][0][0][i - 2] + vals[3][i - 1];
                
                choice[i][0][0][0] = new int[]{1, 0, 0, 0};
                choice[0][i][0][0] = new int[]{0, 1, 0, 0};
                choice[0][0][i][0] = new int[]{0, 0, 1, 0};
                choice[0][0][0][i] = new int[]{0, 0, 0, 1};
            }
            dp[1][0][0][0] = vals[0][0];
            dp[0][1][0][0] = vals[1][0];
            dp[0][0][1][0] = vals[2][0];
            dp[0][0][0][1] = vals[3][0];

            choice[1][0][0][0] = new int[]{1, 0, 0, 0};
            choice[0][1][0][0] = new int[]{0, 1, 0, 0};
            choice[0][0][1][0] = new int[]{0, 0, 1, 0};
            choice[0][0][0][1] = new int[]{0, 0, 0, 1};
            
            for (int s = 0; s <= 4 * m; s++)
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < m + 1; j++) {
                    for (int k = 0; k < m + 1; k++) {
                        for (int l = 0; l < m + 1; l++) {
                            if (i+j+k+l != s) continue;
                            if (i + j + k == 0 || i + j + l == 0 || i + k + l == 0 || j + k + l == 0 ) continue;
                            int max = Integer.MIN_VALUE;
                            int choiceIdx = -1;
                            if (i > 0) {
                                int[] c = choice[i - 1][j][k][l];
                                int val = dp[i - 1 - c[0]][j - c[1]][k -c[2]][l - c[3]] + vals[0][i - 1];
                                if (val > max) {
                                    max = val;
                                    choiceIdx = 0;
                                }
                            }
                            if (j > 0) {
                                int[] c = choice[i][j - 1][k][l];
                                
                                int val = dp[i - c[0]][j - 1 - c[1]][k -c[2]][l - c[3]] + vals[1][j - 1];
                                if (val > max) {
                                    max = val;
                                    choiceIdx = 1;
                                }
                            }
                            if (k > 0) {
                                int[] c = choice[i][j][k - 1][l];
                                int val = dp[i - c[0]][j - c[1]][k - 1 -c[2]][l - c[3]] + vals[2][k - 1];
                                if (val > max) {
                                    max = val;
                                    choiceIdx = 2;
                                }
                            }
                            if (l > 0) {
                                int[] c = choice[i][j][k][l - 1];
                                int val = dp[i - c[0]][j - c[1]][k -c[2]][l - 1 - c[3]] + vals[3][l - 1];
                                if (val > max) {
                                    max = val;
                                    choiceIdx = 3;
                                }
                            }
                            choice[i][j][k][l][choiceIdx] = 1;
                            dp[i][j][k][l] = max;
                            //o(choiceIdx + " " + max);

                        }
                    }
                }
            }
                

            //d(tot);
           //o();
            //dp[0][0][0][0] = 0;
            //o(Arrays.toString(choice[5][3][5][5]));
            //o(dp[1][1][1][1]);
            //o(dp[2][2][2][2]);
            /*
            int a,b,c,d;
            a=b=c=d=m;
            while (a+b+c+d!=0) {
                o("BEFORE: " +a+" "+b+" "+c+" "+d);
                o(Arrays.toString(choice[a][b][c][d]));
                a = a - choice[a][b][c][d][0];
                b = b - choice[a][b][c][d][1];

                c = c - choice[a][b][c][d][2];
                d = d - choice[a][b][c][d][3];
                o("AFTER: " +a+" "+b+" "+c+" "+d);

            }*/
           o(String.format("Deal %d: First player wins %d out of %d", deal, dp[m][m][m][m], tot));
        }

        in.close();
        System.exit(0);
    }
    
    

}
/*

K7 2A 3T J8
K Q J T
A2 A2 A2 A2
2A 2A 2A 2A

 */
