package choi.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * http://www.contest/api
 * @author Wonjohn Choi
 *
 */

public class Handler2 {
    static final boolean debugOn = true;
    static PrintWriter pw;
    static {
        if (debugOn) {
            try {
                pw = new PrintWriter(new File("log.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void reportln(String in) {
        if (debugOn) {
            pw.println(in);
            pw.flush();
        }
    }
    
    public static void report(String in) {
        if (debugOn) {
            pw.print(in);
            pw.flush();
    
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public static void report(HashMap<String, Object> map) {
        if (debugOn) {
            for(String s: map.keySet()) {
                Object o = map.get(s);
                report("{" + s + ":");
                if (o instanceof HashMap<?, ?>) {
                    report((HashMap<String, Object>) o);
                    reportln("}");
                } else if(o instanceof Object[]) {
                    report(Arrays.deepToString((Object[])o));
                    reportln("}");
                } else {
                    report(o.toString());
                    reportln("}");
                }
            }
        }
    }

    public String ping(String input) {
        reportln("Entering ping...");
        reportln("Input");
        reportln(input);
        
        String output = "pong";
        
        
        reportln("Output:");
        reportln(output);
        reportln("Exiting ping...\n");
        
        return "pong";
    }
    

    public String start_game(HashMap<String, Object> data) {
        //int game_id, int player_id, int initial_discard, int other_player_id) {
        reportln("Entering start_game...");
        reportln("Input:");
        report(data);
        
        reportln("Output:");
        reportln("");
        reportln("Exiting start_game...\n");
        return "";
    }
    
    public HashMap<String, Object> get_move(HashMap<String, Object> data) {  
        //int game_id, int[] rack, int discard, int remaining_microseconds, int[] other_player_moves) {
        reportln("Entering get_move...");
        reportln("Input:");
        report(data);
        
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("move", "request_deck");
        reportln("Output:");
        report(output);
        reportln("");
        reportln("Exiting get_move...\n");

        return output;
    }
    
    public int get_deck_exchange(HashMap<String, Object> data) {
        // {int game_id, int remaining_microseconds, int[] rack, int card) {
        reportln("Entering get_deck_exchange...");
        reportln("Input:");
        report(data);
        
        reportln("Output:");
        reportln("0");
        reportln("Exiting get_deck_exchange...\n");
        return 0;
    }
    
    public String move_result(HashMap<String, Object> data) {//int game_id, String move) {
        reportln("Entering move_result...");
        reportln("Input:");
        report(data);
        
        reportln("Output:");
        reportln("");
        reportln("Exiting move_result...\n");
        
        return "";
        
    }
    
    public String game_result(HashMap<String, Object> data) {//int game_id, int your_score, int other_score, String reason) {
        reportln("Entering game_result...");
        reportln("Input:");
        report(data);
        
        reportln("Output:");
        reportln("");
        reportln("Exiting game_result...\n");
        
        return "";
    }
}