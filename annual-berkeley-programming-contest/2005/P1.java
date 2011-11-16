import java.util.*;
import java.io.*;
/*
Wonjohn Choi
Solved with Java
2008 Annual Berkeley Programming Contest: Problem 1
*/
class P1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String line2 = "";
            for(Character c: line.toCharArray()) {
                if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
                    line2 += c;
                }
            }
            line2 = line2.toUpperCase();
            HashSet<String> bads = new HashSet<String>();
            ArrayList<String> pals = new ArrayList<String>();
            for (int i = 0; i < line2.length(); i += 1) {
                String pal = "" + line2.charAt(i);
                for (int j = 1; j <= Math.min(i, line2.length() - 1 - i) ; j += 1) {
                    int left = i - j; //>=0
                    int right = i  + j; //<= line2.length() - 1
                    if (line2.charAt(left) == line2.charAt(right)) {
                        bads.add(pal);
                        pal = line2.charAt(left) + pal + line2.charAt(right);
                    } else {
                        break;
                    }
                }
                
                if (!pal.isEmpty() && pal.length() >= 2) {
                    pals.add(pal);
                }
            }
            
            for (int i = 0; i < line2.length() - 1; i += 1) {
                if (line2.charAt(i) != line2.charAt(i + 1)) continue;
                String pal = "" + line2.charAt(i) + line2.charAt(i + 1);
                for (int j = 1; j <= Math.min(i, line2.length() - 2 - i); j += 1) {
                    int left = i - j; //>=0
                    int right = i + 1 + j; // <=line2.length() - 1
                    if (line2.charAt(left) == line2.charAt(right)) {
                        bads.add(pal);
                        pal = line2.charAt(left) + pal + line2.charAt(right);
                    } else {
                        break;
                    }
                }
               
                if (!pal.isEmpty() && pal.length() >= 2) {
                    pals.add(pal);
                }
            }
            
            Collections.sort(pals);
            ArrayList<String> pals2 = new ArrayList<String>(new HashSet<String>(pals));
            Collections.sort(pals2);
            for (String pal: pals2) {
                if(!bads.contains(pal)) {
                    System.out.print(pal + " ");
                }
            }
            System.out.println();
            
            
        }
    }
    
    
}