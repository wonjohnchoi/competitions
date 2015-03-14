import java.util.*;
import java.io.*;
public class DecipherabilityEasy {
    public static String good = "Possible";
    public static String bad = "Impossible";
    public static String check(String before, String after) {
        if (before.length() - 1 != after.length()) return bad;
        int idx = 0;
        boolean chance = false;
        for (char c : before.toCharArray()) {
            if (after.length() == idx) {
                break;
            }
            if (after.charAt(idx) != c) {
                if (!chance) {
                    chance = true;
                } else {
                    return bad;
                }
            } else {
                idx++;
            }
        }
        return good;
    }
    public static void main(String args[]) {
        System.out.println(check("sunuke", "snuke"));
        System.out.println(check("sunukeabcd", "snukeabc"));
        System.out.println(check("sunukeabcd", "snukeabce"));
    }
}
