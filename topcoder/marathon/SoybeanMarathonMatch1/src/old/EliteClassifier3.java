package old;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class EliteClassifier3 {
    /*
    ExperimentID - Each experiment has a different ID.
    Year - Year in which the experiment was ran.
    LOCCD - You can refer to Locations.csv to obtain additional information about each location. Please note that such information is not available for all locations.
    Rep - Number of the repetition of one trial.
    Type - conv (Conventional), RR1 (Roundup Ready) or RR2Y (Roundup Ready to Yield).
    VarietyID - ID of the variety within experiment.
    Yield - Yield of a variety given in bushels per acre.
    MN - Maturity number of a variety.
    RM - Relative maturity of a variety. It's given in case it's available and the variety is a check.
    IsCheck - 1 if a variety is a check, 0 otherwise
    IsElite - 1 if a variety is an elite, 0 otherwise. Check/Elite statuses are exclusive - only one can be true.
     */
    public static void main(String args[]) {
        Scanner all = open("DataTraining.csv.txt");
        Scanner elite = open("Elite.csv.txt");
        while(all.hasNextLine()) {
            String line = all.nextLine();
            String[] info = line.split(",");
            if (info[9].equals("1")) {
                System.out.println(line + "  CHECK");
            } else if(info[10].equals("1")) {
                System.out.println(line + "     ELITE");
            }
        }
        /*
        String[] allData = new String[10000];
        String[] allElite = new String[1856];
        int idx = 0;
        while (all.hasNextLine()) {
            if (idx >= 10000) {
                break;
            }

            allData[idx] = all.nextLine();
            idx += 1;
        }
        
        idx = 0;
        while (elite.hasNextLine()) {
            allElite[idx] = elite.nextLine();
            idx += 1;
        }
        
        int[] allResult = classify(allData,  null);
        //int[] eliteResult = classify(allElite, null);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("log1.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        for (Integer result:allResult) {
            out.println(result);
        }*/
    }
    
    public static String avg(Scanner in) {
        int ExperimentID;//, - Each experiment has a different ID.
        int Year;// - Year in which the experiment was ran.
        int LOCCD;// - You can refer to Locations.csv to obtain additional information about each location. Please note that such information is not available for all locations.
        int Rep;// - Number of the repetition of one trial.
        String Type;// - conv (Conventional), RR1 (Roundup Ready) or RR2Y (Roundup Ready to Yield).
        int VarietyID;// - ID of the variety within experiment.
        double Yield;// - Yield of a variety given in bushels per acre.
        int MN;// - Maturity number of a variety.
        int RM;// - Relative maturity of a variety. It's given in case it's available and the variety is a check.
        int IsCheck;// - 1 if a variety is a check, 0 otherwise
        int IsElite;//

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split(",");

        }
        
        return null;
    }
    
    public static void stat(Scanner in) {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split(",");
            int weight = Integer.parseInt(data[3]) + 1;
            
        }
    }
    
    public static Scanner open(String name) {
        try {
            return new Scanner(new FileReader(name));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    
    public static int[] classify(String[] data, String[] locations) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (String datum : data) {
            String[] info = datum.split(",");
            entries.add(new Entry(info));
        }
        Collections.sort(entries, new Comparator<Entry>() {

            @Override
            public int compare(Entry arg0, Entry arg1) {
                return ((Double)Double.parseDouble(arg1.info[6])).compareTo(Double.parseDouble(arg0.info[6]));
            }
        
        });
        HashSet<Integer> found = new HashSet<Integer>();
        LinkedList<Integer> varieties = new LinkedList<Integer>();
        for (Entry entry: entries) {
            int id = Integer.parseInt(entry.info[5]);
            if (found.contains(id)) continue;
            varieties.add(id);
            found.add(id);
        }
        
        
        int[] result = new int[varieties.size()];
        for (int i = 0; i < varieties.size(); i += 1) {
            result[i] = varieties.get(i);
        }
        return result;
    }
    static class Entry {
        int ExperimentID;//, - Each experiment has a different ID.
        int Year;// - Year in which the experiment was ran.
        int LOCCD;// - You can refer to Locations.csv to obtain additional information about each location. Please note that such information is not available for all locations.
        int Rep;// - Number of the repetition of one trial.
        String Type;// - conv (Conventional), RR1 (Roundup Ready) or RR2Y (Roundup Ready to Yield).
        int VarietyID;// - ID of the variety within experiment.
        double Yield;// - Yield of a variety given in bushels per acre.
        int MN;// - Maturity number of a variety.
        int RM;// - Relative maturity of a variety. It's given in case it's available and the variety is a check.
        int IsCheck;// - 1 if a variety is a check, 0 otherwise
        int IsElite;//
        String[] info;
        public Entry(String[] newInfo) {
            info = newInfo;
        }
        
        
    }
}
