package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class EliteClassifier7 {
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
    static boolean LOG = true;
    static void log(String msg) {
        if (LOG) {
            LOGGER.print(msg);
        }
    }
    
    static void logln(String msg) {
        if (LOG) {
            LOGGER.println(msg);
        }
    }
    
    static void setupLOGGER() {
        int subversion = 0;
        while (LOG && LOGGER == null) {
            File file = new File(BASE + "log" + VERSION + "." + subversion);
            if (!file.exists()) {
                try {
                    LOGGER = new PrintWriter(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            } else {
                subversion += 1;
            }
            
        }    
    }
    
    static PrintWriter LOGGER = null;
    static final int VERSION = 7;
    static final String DESCRIPTION = "1. Make percentage-of-being-elite database using type (3), yield (200), maturity(300), location (?)\n"
        + "2. Refacter code so that I can repeat classify to find the optimal variables.";
    static String BASE = "/Users/wonjohnchoi/Dropbox/source-codes/competitions/topcoder/marathon/SoybeanMarathonMatch1/";
    public static void main(String args[]) {
        setupLOGGER();
        Scanner all = open(BASE + "DataTraining.csv");
        System.out.println(evalAnalyzer(all));
        
        if (LOG)
            LOGGER.close();

        
     }
    
    public static double evalAnalyzer(Scanner all) {
        System.out.println("Accessing evalAnalyzer...");
        logln("VERSION: " + VERSION);
        logln("DESCRIPTION: " + DESCRIPTION);
        logln("----------------------------------------");

        LinkedList<Entry> entries = new LinkedList<Entry>();
        HashSet<Integer> varietyId = new HashSet<Integer>();
        while (all.hasNextLine()) {
            Entry entry = new Entry(all.nextLine());
            entries.add(entry);
            varietyId.add(entry.VarietyID);
        }
        Analyzer analyzer = new Analyzer(entries.listIterator());
        int[] classified = analyzer.classifyBeta();
        /*
        analyzer.percentages = new Percentage[2][2][2];
        for (int i = 0; i < 2; i += 1) {
            for (int j = 0; j < 2; j += 1) {
                for (int k = 0; k < 2; k += 1) {
                    analyzer.percentages[i][j][k] = new Percentage();
                }
            }
        }*/
        logln(analyzer.toString());
        
        //LOGGER.close();
        if (classified.length != varietyId.size()) {
            System.err.println("Analyzer.classify did not output correct number of variety ID's");
            System.err.println("classifed.length: " + classified.length);
            System.err.println("varietyId.size: " + varietyId.size());
            for (int i = 0; i < classified.length; i += 1) {
                for (int j = i + 1; j < classified.length; j += 1) {
                    if (classified[i] == classified[j]) {
                        System.err.println("Multiple id exists: " + classified[i]);
                    }
                }
            }
            System.exit(1);
        }

        HashSet<Integer> elites = new HashSet<Integer>();
        HashSet<Integer> checks = new HashSet<Integer>();
        for (Entry entry : entries) {
            if (entry.IsElite) {
                elites.add(entry.VarietyID);
            } else if (entry.IsCheck) {
                checks.add(entry.VarietyID);
            }
        }
        
        
        double score = 0;
        int total = 0;
        int correct = 0;
        //System.out.println(elites.size());
        for (int id : classified) {
            total += 1;
            if (elites.contains(id)) {
                correct += 1;
                score += 1000000 / elites.size() * correct / total;
                //System.out.println("id : " + id + ", score: " + 1000000 / elites.size() * correct / total);
            }
        }
        
        
        logln("SCORE: " + score);
        logln("----------------------------------------");

        int idx = 0;
        for (int id : classified) {
            String lineNo = idx + "";
            while (lineNo.length() < 5) {
                lineNo += " ";
            }
            String idStr = id + "";
            while (idStr.length() < 5) {
                idStr += " ";
            }
            log(lineNo + "ID: " + idStr);
            if (elites.contains(id)) {
                logln(" ELITE");
            } else if (checks.contains(id)){
                logln(" CHECKED");
            } else {
                logln(" NORMAL");
            }
            idx += 1;
        }
        return score;
        
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
            entries.add(new Entry(datum));
        }
        Analyzer analyzer = new Analyzer(entries.listIterator());
        
        
        
        return analyzer.classify();
    }

    
    static class Percentage {
        int nElite = 0;
        int nCheck = 0;
        int nNormal = 0;
        int tot() {
            return nElite + nCheck + nNormal;
        }
        public String toString() {
            return "{"+nElite+","+nCheck+","+nNormal+"}";
        }
    }
    
    static class Analyzer {
        ArrayList<Experiment> experiments;
        static Percentage[][][] percentages = new Percentage[4][200][300];
        public Analyzer(ListIterator<Entry> entries) {
            int[][][][] percentageData = null;
            for (int i = 0; i < percentages.length; i += 1) {
                for (int j = 0; j < percentages[i].length; j += 1) {
                    for (int k = 0; k < percentages[i][j].length; k += 1) {
                        percentages[i][j][k] = new Percentage();
                        percentages[i][j][k].nElite = percentageData[i][j][k][0];
                        percentages[i][j][k].nCheck = percentageData[i][j][k][1];
                        percentages[i][j][k].nNormal = percentageData[i][j][k][2];
                        if (percentages[i][j][k].nElite + percentages[i][j][k].nNormal == 0) {
                            percentages[i][j][k].nNormal = 1;
                        }
                    }
                }
            }
            
            /*
            while (entries.hasNext()) {
                Entry entry = entries.next();
                if (entry.Type == null) {
                    entry.Type = 3; // unknown
                }
                if (entry.Yield != null && entry.Yield >= 199.0) {
                    entry.Yield = 198.0;
                }
                
                if (entry.Yield == null || entry.Yield < 0) {
                    entry.Yield = 199.0; //unknown
                }
                if (entry.MN != null && entry.MN >= 299.0) { //reserved for unknown
                    entry.MN = 298.0; 
                }

                if (entry.MN == null || entry.MN < 0){
                    entry.MN = 299.0; //unknown 
                }
                
                
                int type = entry.Type;
                int yield = (int) entry.Yield.doubleValue();
                int mn = (int) entry.MN.doubleValue();
                if (entry.IsCheck) {
                    percentages[type][yield][mn].nCheck += 1;
                } else if (entry.IsElite) {
                    percentages[type][yield][mn].nElite += 1;
                } else {
                    percentages[type][yield][mn].nNormal += 1;                    
                }
                
            }
            while (entries.hasPrevious()) {
                entries.previous();
            
            }*/
            
            System.out.println("accessing Analyzer...");
            experiments = new ArrayList<Experiment>();
            while (entries.hasNext()) {
                Experiment experiment = new Experiment(entries);                
                experiments.add(experiment);
            }
            /*
            int cnt = 0;
            for (int i = 0; i < percentages.length; i += 1) {
                for (int j = 0; j < percentages[i].length; j += 1) {
                    for (int k = 0; k < percentages[i][j].length; k += 1) {
                        Percentage p =percentages[i][j][k];
                        if (p.nCheck == 0 && p.nElite == 0 && p.nNormal == 0) {
                            System.out.println(i+" "+j+ " "+k);
                            cnt += 1;
                        }
                    }
                }
            }
            System.out.println(cnt);
            */
        }
        public String toString() {
            System.out.println("generating code form percentages...");
            StringBuilder code = new StringBuilder();
            code.append("{");
            for (int i = 0; i < percentages.length; i += 1) {
                if (i != 0) {
                    code.append(",");
                }

                code.append("{");
                for (int j = 0; j < percentages[i].length; j += 1) {
                    if (j != 0) {
                        code.append(",");
                    }

                    code.append("{");
                    for (int k = 0; k < percentages[i][j].length; k += 1) {
                        if (k != 0) {
                            code.append(",");
                        }
                        code.append(percentages[i][j][k]);
                    }
                    code.append("}");
                }
                
                code.append("}");
            }
            code.append("}");
            return code.toString();
        }
        
        public int[] classifyBeta() {
            System.out.println("accessing Analyzer.classifyBeta...");
            ArrayList<Variety> varieties = new ArrayList<Variety>();
            for (Experiment experiment : experiments) {
                for (int i = 0; i < experiment.sectionSize; i += 1) {

                    LinkedList<Entry> entries = new LinkedList<Entry>();
                    for (Section section : experiment.sections) {
                        /* Data Validation
                        if (variety.entries.size() != 0) {
                            if (variety.entries.get(0).VarietyID != section.entries.get(i).VarietyID) {
                                System.err.println("VarietyID of a corresponding position of a section is different from that of previous section");
                                System.exit(1);
                            }
                        }*/
                        
                        entries.add(section.entries.get(i));

                    }
                    Variety variety = new Variety(entries);

                    /* Data Validation
                    for (Variety elem : varieties) {
                        if (elem.entries.get(0).VarietyID == variety.entries.get(0).VarietyID) {
                            System.err.println("Same VarietyID used in two different experiments");
                            System.err.println("Experiment 1: " + elem.entries.get(0).secton.experiment);
                            System.err.println("Experiment 2: " + variety.entries.get(0).secton.experiment);
                            System.err.println("Variety ID: " + elem.entries.get(0).VarietyID);

                            System.exit(1);
                        }
                    }*/
                    varieties.add(variety);
                    
                }
            }

            Collections.sort(varieties, new Comparator<Variety>() {

                @Override
                public int compare(Variety arg0, Variety arg1) {
                    
                    return new Double(arg1.percentage).compareTo(new Double(arg0.percentage)) ;
                }
                
            });
            
            int[] result = new int[varieties.size()];
            for (int i = 0; i < varieties.size(); i += 1) {
                result[i] = varieties.get(i).entries.get(0).VarietyID;
                //System.out.println(varieties.get(i).value());
            }
            
            
            
            return result;

        }
        
        public int[] classify() {
            System.out.println("accessing Analyzer.classify...");
            ArrayList<Variety> varieties = new ArrayList<Variety>();
            for (Experiment experiment : experiments) {
                for (int i = 0; i < experiment.sectionSize; i += 1) {

                    LinkedList<Entry> entries = new LinkedList<Entry>();

                    for (Section section : experiment.sections) {
                        /* Data Validation
                        if (variety.entries.size() != 0) {
                            if (variety.entries.get(0).VarietyID != section.entries.get(i).VarietyID) {
                                System.err.println("VarietyID of a corresponding position of a section is different from that of previous section");
                                System.exit(1);
                            }
                        }*/
                        entries.add(section.entries.get(i));

                    }
                    Variety variety = new Variety(entries);

                    /* Data Validation
                    for (Variety elem : varieties) {
                        if (elem.entries.get(0).VarietyID == variety.entries.get(0).VarietyID) {
                            System.err.println("Same VarietyID used in two different experiments");
                            System.err.println("Experiment 1: " + elem.entries.get(0).secton.experiment);
                            System.err.println("Experiment 2: " + variety.entries.get(0).secton.experiment);
                            System.err.println("Variety ID: " + elem.entries.get(0).VarietyID);

                            System.exit(1);
                        }
                    }*/
                    varieties.add(variety);
                    
                }
            }
            
            Collections.sort(varieties);
            int[] result = new int[varieties.size()];
            for (int i = 0; i < varieties.size(); i += 1) {
                result[i] = varieties.get(i).entries.get(0).VarietyID;
                //System.out.println(varieties.get(i).value());
            }
            
            
            
            return result;
        }
    }
    
    static class Variety implements Comparable<Variety> {
        LinkedList<Entry> entries = new LinkedList<Entry>();
        Double totalScore = 0.0d;
        int nScore = 0;
        Double value = null;
        static int[] deviations = new int[200];
        double percentage = 1.0;
        Variety(LinkedList<Entry> newEntries) {
            entries = newEntries;
            for (Entry entry : entries) {
                Percentage p = Analyzer.percentages[entry.Type][(int)entry.Yield.doubleValue()][(int)entry.MN.doubleValue()];
                percentage += (double) p.nElite / (p.nElite + p.nNormal);
            }
            if (entries.get(0).IsCheck) {
                percentage = 0;
            }
        }
        Double value() {
            if (value == null) {
                if (entries.get(0).IsCheck) {
                    value = Double.NEGATIVE_INFINITY;
                } else {
                    ArrayList<Double> yields = new ArrayList<Double>();
                    for (Entry entry : entries) {
                        //System.out.println(entry.secton.avg());
                        totalScore += entry.Yield - entry.secton.avg();
                        nScore += 1;
                        yields.add(entry.Yield);
                        
                    }
                    value = totalScore / nScore * entries.get(0).secton.experiment.value() + 1000;
                    //System.out.println(value);
                    //System.out.println(deviation(yields));
                    //deviations[(int)deviation(yields)] += 1;

                    
                    if (deviation(yields) > 65) {
                        value -= 1000; 
                    }
                }
            }
            return value;
        }
        
        static double deviation(ArrayList<Double> yields) {
            double tot = 0;
            int cnt = 0;
            for (Double yield : yields) {
                tot += yield;
                cnt += 1;
            }
            double avg = tot / cnt;
            
            double dev = 0;
            for (Double yield : yields) {
                dev += Math.pow((yield - avg), 2);
            }
            dev = Math.sqrt(dev);
            return dev;
        }

        @Override
        public int compareTo(Variety arg0) {
            return arg0.value().compareTo(value());
        }
    }
    
    static class Section {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        int Rep, LOCCD, ExperimentId;
        double totalYield;
        int size;
        Experiment experiment;
        public Section (ListIterator<Entry> iter, Experiment experiment) {
            this.experiment = experiment;
            Entry first = iter.next();
            Rep = first.Rep;
            LOCCD = first.LOCCD;
            ExperimentId = first.ExperimentId;
            totalYield = 0.0d;
            size = 0;
            
            add(first);
            
            
            while (iter.hasNext()) {
                Entry cur = iter.next();
                if (Rep != cur.Rep || LOCCD != cur.LOCCD || ExperimentId != cur.ExperimentId) {
                    iter.previous();
                    break;
                } else {
                    add(cur);
                }
            }
        }

        public Section (ListIterator<Entry> iter, int sectionSize) {
            Entry first = iter.next();
            Rep = first.Rep;
            LOCCD = first.LOCCD;
            ExperimentId = first.ExperimentId;
            totalYield = 0.0d;
            size = 0;
            
            add(first);
            
            while (entries.size() < sectionSize) {
                Entry cur = iter.next();
                if (entries.contains(cur)) {
                    continue;
                }
                if (Rep != cur.Rep || LOCCD != cur.LOCCD || ExperimentId != cur.ExperimentId) {
                    System.err.println("Data inconsistency!");
                    iter.previous();
                    break;
                } else {
                    add(cur);
                }
            }
        }
        
        private void add(Entry entry) {
            entries.add(entry);
            entry.secton = this;
            totalYield += entry.Yield;
            size += 1;
        }
        
        public double avg() {
            return totalYield / size;
        }
        

    }
    
    static class Experiment {
        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<Variety> varieties = new ArrayList<Variety>();
        int ExperimentId;
        int sectionSize;
        Double value = null;
        public Experiment(ListIterator<Entry> iter) {
            Section first = new Section(iter, this);
            sectionSize = first.entries.size();
            
            ExperimentId = first.ExperimentId;
            sections.add(first);
            
            while (iter.hasNext()) {
                Entry cur = iter.next();
                if (ExperimentId != cur.ExperimentId) {
                    iter.previous();
                    break;
                } else {
                    iter.previous();
                    sections.add(new Section(iter, sectionSize));
                }   
            }
        }
        
        public double value() {
            if (value == null) {
                if (sections.size() == 1) {
                    value = 0.2;
                } else if (sections.size() == 2) {
                    value = 0.2;
                } else if (sections.size() == 3) {
                    value = 0.4;
                } else if (sections.size() == 4) {
                    value = 0.5;
                } else if (sections.size() == 5) {
                    value = 0.8;
                } else if (sections.size() == 6) {
                    value = 0.9;
                } else if (sections.size() == 7) {
                    value = 1.1;
                } else {
                    value = 1.2;
                }
            }
            return value;
        }
        
        public String toString() {
            return "Section size: " + sectionSize + "\n"
                + "Experiment Id: " + ExperimentId;
        }
    }
    
    static class Entry implements Comparable<Entry> {
        int ExperimentId;//, - Each experiment has a different ID.
        short Year;// - Year in which the experiment was ran.
        int LOCCD;// - You can refer to Locations.csv to obtain additional information about each location. Please note that such information is not available for all locations.
        byte Rep;// - Number of the repetition of one trial. + 1
        Byte Type;// -0 conv (Conventional),1 RR1 (Roundup Ready) or 2 RR2Y (Roundup Ready to Yield).
        int VarietyID;// - ID of the variety within experiment.
        Double Yield;// - Yield of a variety given in bushels per acre.
        Double MN;// - Maturity number of a variety.
        Double RM;// - Relative maturity of a variety. It's given in case it's available and the variety is a check.
        boolean IsCheck;// - 1 if a variety is a check, 0 otherwise
        Boolean IsElite;//
        String raw;
        Section secton;
        
        /*
        Double score;
        
        public double eval() {
            if (score == null) {
                
            }

            return score.doubleValue();
        }*/
        
        public Entry(String newData) {
            raw = newData;
            String[] data = newData.split(",");
            ExperimentId = Integer.parseInt(data[0]);
            Year = Short.parseShort(data[1]);
            LOCCD = Integer.parseInt(data[2]);
            Rep = Byte.parseByte(data[3]);
            String[] types = new String[] {"conv", "RR1", "RR2Y"};
            for (int i = 0; i < types.length; i += 1) {
                if (types[i].equalsIgnoreCase(data[4])) {
                    Type = (byte) i;
                }
            }
            VarietyID = Integer.parseInt(data[5]);
            Yield = Double.parseDouble(data[6]);
         
            MN = data[7].equals("NULL") ? null : Double.parseDouble(data[7]);
            RM = data[8].equals("NULL") ? null : Double.parseDouble(data[8]);
            IsCheck = data[9].equals("1");
            IsElite = data.length < 11 ? null : data[10].equals("1");
        }
        
        public String toString() {
            return raw;
        }

        @Override
        public int compareTo(Entry arg0) {
            return new Double(arg0.Yield).compareTo(new Double(Yield));
        }
        
        public int hashCode() {
            return VarietyID;
            
        }
        public boolean equals(Object entry) {
            return VarietyID == ((Entry)entry).VarietyID;
        }
    }
    
}