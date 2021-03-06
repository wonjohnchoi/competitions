import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class FraudPrevention {
    static class Deal {
        String email;
        String addr;
        long card;
        long dealID;
        long orderID;
        public Deal(String nemail, String naddr, long ncard, long ndealID, long norderID) {
            email = nemail;
            addr = naddr;
            card = ncard;
            dealID = ndealID;
            orderID = norderID;
        }
    }
    public static void main(String args[]) {
        HashSet<Long> detected = new HashSet<Long>();
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        HashMap<String, Deal> addrs = new HashMap<String, Deal>(n);
        HashMap<String, Deal> emails = new HashMap<String, Deal>(n);

        in.nextLine();
        for (int i = 0; i < n; i += 1) {
            String next = in.nextLine();
            //System.out.println(next);
            String[] info = next.split(",");
            //for(String inf : info) {
            //    System.out.println(inf);
            //}
            long orderID = Long.parseLong(info[0]);
            long dealID = Long.parseLong(info[1]);
            long card = Long.parseLong(info[7]);
            String addr = processAddress(info[3], info[4], info[5], info[6]);
            String email = processEmail(info[2]);
            if (addrs.containsKey(addr)) {
                Deal deal = addrs.get(addr);
                if (deal.dealID == dealID && card != deal.card) {
                    detected.add(orderID);
                    detected.add(deal.orderID);
                }
                
            }
            Deal ndeal = new Deal(email, addr, card, dealID, orderID);
            addrs.put(addr, ndeal);
            
            
            if (emails.containsKey(email)) {
                Deal deal = emails.get(email);
                if (deal.dealID == dealID && card != deal.card) {
                    detected.add(orderID);
                    detected.add(deal.orderID);
                }
                
            }
            
            emails.put(email, ndeal);

        }
        ArrayList<Long> result = new ArrayList<Long>(detected);
        Collections.sort(result);
        for (int i = 0; i < result.size(); i += 1) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(result.get(i));
           
        }
        System.out.println();
    }
    
    
    /*
    private static boolean isAddressEquals(String s1, String s2) {
        s1 = s1.toUpperCase();
        s2 = s2.toUpperCase();
        String[] abbre = new String[]{"ST.", "RD.", "IL", "CA", "NY"};
        String[] full = new String[]{"STREET", "ROAD", "ILLINOIS", "CALIFORNIA", "NEW YORK"};
        for (int i = 0; i < abbre.length; i += 1) {
            s1 = s1.replace(abbre[i], full[i]);
            s2 = s2.replace(abbre[i], full[i]);
        }
        return s1.equals(s2);
    }*/
    private static String processAddress(String street, String city, String state, String zipcode) {
        String[] abbre = new String[]{"ST.", "RD.", "IL", "CA", "NY"};
        String[] full = new String[]{"STREET", "ROAD", "Illinois", "California", "New York"};
        for (int i = 0; i < 2; i += 1) {
            street = street.replace(full[i], abbre[i]);
        }
        for (int i = 2; i < abbre.length; i += 1) {
            state = state.replace(full[i], abbre[i]);
        }

        return street.toUpperCase()+"@"+city+"@"+state+"@"+zipcode;
    }
    
    /*
    private static boolean isEmailEquals(String s1, String s2) {
        String[] email1 = processEmail(s1);
        String[] email2 = processEmail(s2);
        if (email1 == null || email2 == null) {
            return false;
        }
        return email1[0].equalsIgnoreCase(email1[0])
            && email1[1].equalsIgnoreCase(email2[1]);
    }*/
    
    private static String processEmail(String email) {
        int atIdx = email.indexOf("@");
        String user = email.substring(0, atIdx);
        String rest = email.substring(atIdx + 1);
        int plusIdx = user.indexOf("+");
        
        if (plusIdx != -1) {
            user = user.substring(0, plusIdx);
        }
        user = user.replace(".", "");
        return user + "@" + rest;
    }
    

}

