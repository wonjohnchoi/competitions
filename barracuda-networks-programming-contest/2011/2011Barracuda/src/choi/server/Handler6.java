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

public class Handler6 {
    static final boolean debugOn = true;
    static PrintWriter pw;
    static Engine engine;
    static {
        if (debugOn) {
            try {
                for(int i = 0; ;i+=1) {
                    String name = "log_engine_"+Engine.version+"-"+i+".txt";
                    if(! new File(name).getAbsoluteFile().exists()) {
                        pw = new PrintWriter(new File(name));
                        break;
                    }
                }                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        engine = new Engine();
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
        

    static int preDiscard;
    
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> get_move(HashMap<String, Object> data) {  
        //int game_id, int[] rack, int discard, int remaining_microseconds, int[] other_player_moves) {
        reportln("Entering get_move...");
        reportln("Input:");
        report(data);
        
        
        int discard = (Integer) data.get("discard");
        
        Object[] rackO = (Object[]) data.get("rack");
        Object[] o = (Object[]) data.get("other_player_moves");

        if (o.length != 0) {
            HashMap<String, String> theirMoves = (HashMap<String, String>)((Object[])o[0])[1] ;
            String theirMove = theirMoves.get("move");
            if(theirMove.equals("take_discard")) {
                engine.theirs.add(engine.discard);
            } else if(theirMove.equals("take_deck")) {
                engine.garbage.add(engine.discard);
            } 
            engine.discard = discard;
            engine.theirs.remove(new Integer(discard));
        } else {
            engine.discard = discard;
        }

        engine.initRack(rackO);
        engine.nMoves += 1;
        int idx = engine.getIdx(discard, false);        
       
        
        HashMap<String, Object> output = new HashMap<String, Object>();

        if(idx != -1) {
            output.put("idx", idx);
            output.put("move", "request_discard");
            engine.discard = engine.rack[idx];
        } else {
            output.put("move", "request_deck");
        }
        
        
        reportln("Output:");
        report(output);
        reportln("Exiting get_move...\n");

        return output;
    }
    
    public int get_deck_exchange(HashMap<String, Object> data) {
        // {int game_id, int remaining_microseconds, int[] rack, int card) {
        reportln("Entering get_deck_exchange...");
        reportln("Input:");
        report(data);
        int card = (Integer) data.get("card");
        //int idx = (int) Math.floor((card - 1) / 4);
      
        int idx = engine.getIdx(card, true);
        
        if (idx == -1) {
            idx = 19;
        }
        
        engine.garbage.add(engine.discard);
        engine.discard = engine.rack[idx];
        reportln("Output:");
        reportln(""+idx);
        reportln("Exiting get_deck_exchange...\n");
        
        return idx;
    }
    
    public String move_result(HashMap<String, Object> data) {
        //int game_id, String move) {
        reportln("Entering move_result...");
        reportln("Input:");
        report(data);
        
        reportln("Output:");
        reportln("");
        reportln("Exiting move_result...\n");
        
        return "";
        
    }
    
    public String game_result(HashMap<String, Object> data) {
        //int game_id, int your_score, int other_score, String reason) {
        reportln("Entering game_result...");
        reportln("Input:");
        report(data);
        int your_score = (Integer) data.get("your_score");
        int other_score = (Integer) data.get("other_score");
        if (your_score > other_score) {
            engine.wins += 1;
            
        } else if(your_score == other_score) {
            engine.ties += 1;
        } else {
            engine.loses += 1;
        }
        engine.totalGame += 1;
        engine.totalScore += your_score;
        
        engine.reset();
        reportln("Output:");
        reportln("");
        reportln("Exiting game_result...\n");
        reportln("Game #: " + engine.totalGame);
        reportln("Score: " + your_score);
        reportln("Total Score: " + engine.totalScore);
        reportln("Avg. Score: " + engine.totalScore / engine.totalGame);
        reportln("Wins: " + engine.wins);
        reportln("Ties: " + engine.ties);
        reportln("Loses: " + engine.loses);
        return "";
    }
}