import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class P6 {
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
        // debug = true;
        
        while (in.hasNextInt()) {
            HashMap<String, HashSet<String>> isFriend = new HashMap<String, HashSet<String>>();

            int n = in.nextInt();
            String c = in.next();
            while (true) {
                String f1 = in.next();
                String f2 = in.next();
                if (f1.equals("*") && f2.equals("*")) {
                    break;
                }
                if (isFriend.get(f1) == null) {
                    isFriend.put(f1, new HashSet<String>());
                }
                isFriend.get(f1).add(f2);

                if (isFriend.get(f2) == null) {
                    isFriend.put(f2, new HashSet<String>());
                }                    
                isFriend.get(f2).add(f1);

            }
            //o(isFriend);
            HashMap<String, Integer> count = new HashMap<String, Integer>();
            for (String friend : isFriend.get(c)) {
                for (String friendOffriend : isFriend.get(friend)) {
                    if (count.get(friendOffriend) == null) {
                        count.put(friendOffriend, 0);
                    }
                    count.put(friendOffriend, count.get(friendOffriend) + 1);
                }
            }
            //o(count);
            ArrayList<Friend> good = new ArrayList<Friend>();
            ArrayList<String> goodNames = new ArrayList<String>();

            for (String friend : count.keySet()) {
                if (!isFriend.get(c).contains(friend) && !friend.equals(c)) {
                    good.add(new Friend(friend, count.get(friend)));
                }
            }
            Collections.sort(good);
            for (int i = 0; i < n; i ++) {
                goodNames.add(good.get(i).name);
            }
            Collections.sort(goodNames);
            String out = "";
            for (int i = 0; i < n; i ++) {
                out += goodNames.get(i);
                if (i != n - 1) {
                    out += " ";
                }
            }
            o(out);
            

        }
        
        in.close();
        System.exit(0);
    }
    /*
3 Goodyear
Walden Kenney Kirby Pauline Plymouth Tahiti Indochina Shinto Tahiti Kenney Pauline Walden 
Ursuline Pauline 
Allegheny Walden Haas Ypsilanti Walden Ypsilanti Allegheny Ursuline Ypsilanti Pauline Haas Walden 
Shinto Kenney Kirby Indochina Tahiti Ursuline Walden Shinto Plymouth Pauline Kirby Allegheny Kenney Allegheny Kirby Kenney Pauline Allegheny Shinto Allegheny Goodyear Pauline Shinto Goodyear Dahomey Haas Indochina Plymouth Goodyear Walden Haas Allegheny 
Haas Tahiti Haas Shinto Walden Indochina Reinhold Pauline Hobbs Shinto Plymouth Allegheny Plymouth Reinhold Haas Plymouth Goodyear Indochina Haas Kirby Goodyear Kirby Indochina Allegheny Haas Goodyear Plymouth Walden 
Reinhold Haas Goodyear Tahiti Allegheny Dahomey Haas Pauline Indochina Tahiti Kirby Shinto Walden Kirby Kenney Pauline Shinto Dahomey 
Shinto Tahiti 
Reinhold Shinto Haas Hobbs Indochina Haas Kenney Ypsilanti Goodyear Allegheny 
Ursuline Kenney Dahomey Kirby Plymouth Ypsilanti Tahiti Pauline Shinto Plymouth Plymouth Goodyear Indochina Hobbs * *

     */
    static class Friend implements Comparable<Friend> {
        String name;
        int count;
        Friend (String name, int count) {
            this.name = name;
            this.count = count;
        }
        @Override
        public int compareTo(Friend arg0) {
            int diff = arg0.count - this.count;
            if (diff == 0) {
                return name.compareTo(arg0.name);
            }
            return diff;
        }
        
    }
}
