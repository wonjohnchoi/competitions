package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.SortedSet;


public class EliteClassifier6 {
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
    static boolean LOG = false;
    static void LOG(String msg) {
        if (LOG) {
            LOGGER.print(msg);
        }
    }
    
    static void LOGLN(String msg) {
        if (LOG) {
            LOGGER.println(msg);
        }
    }
    static double maxScore = Double.NEGATIVE_INFINITY;
    static double v0, v1, fv0, fv1;
    static PrintWriter LOGGER = null;
    static final int VERSION = 6;
    static final String DESCRIPTION = "Filter variety with high standard deviation";
    static String BASE = "/Users/wonjohnchoi/Dropbox/source-codes/competitions/topcoder/marathon/SoybeanMarathonMatch1/";
    public static void main(String args[]) {
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

        LOGLN("VERSION: " + VERSION);
        LOGLN("DESCRIPTION: " + DESCRIPTION);
        LOGLN("----------------------------------------");

        //evalClassify("1-3", "Sort the whole data by yield.");
        /*
        for (v0 = 0.1; v0 <= 2.0; v0 += 0.1) {
            for (v1 = 0.1; v1 <= 2.0; v1 += 0.1) {
                double score = evalClassify();
                if (score > maxScore) {
                    fv0 = v0;
                    fv1 = v1;
                    maxScore = score;
                }
            }
        }
        System.out.println("MAX SCORE: " + maxScore);
        System.out.println("FV0: " + fv0);
        System.out.println("FV1: " + fv1);*/
        System.out.println(evalClassify());
        
        //analyzeExperiments();
        if (LOG)
            LOGGER.close();
        
        /*
        for (int i = 0; i < Variety.deviations.length; i += 1) {
            System.out.println(i + ": " + Variety.deviations[i]);
        }*/
        
     }
    
    public static double evalClassify() {
        System.out.println("Accessing evalClassify...");
        final int varietySize = 84556;
        final int entrySize = 474413;
        String[] data = new String[entrySize];
        Scanner all = open(BASE + "DataTraining.csv");

        Entry[] entries = new Entry[entrySize];
        int idx = 0;
        while(all.hasNextLine()) {
            entries[idx] = new Entry(all.nextLine());
            data[idx] = entries[idx].raw;
            
            idx += 1;
        }


        boolean[] isCheck = new boolean[varietySize];
        boolean[] isElite = new boolean[varietySize];
        int nElites = 0;
        for (Entry entry : entries) {
            isElite[entry.VarietyID] = entry.IsElite;
            isCheck[entry.VarietyID] = entry.IsCheck;
            if (entry.IsElite) {
                nElites += 1;
            }
        }
        
        int[] classified = classify(data, null);
        double score = 0;
        int total = 0;
        int correct = 0;
        for (int id : classified) {
            total += 1;
            if (isElite[id]) {
                correct += 1;
                score += 1000000 / nElites * correct / total;
            }
        }
        
        
        LOGLN("SCORE: " + score);
        LOGLN("----------------------------------------");

        idx = 0;
        for (int id : classified) {
            String lineNo = idx + "";
            while (lineNo.length() < 5) {
                lineNo += " ";
            }
            String idStr = id + "";
            while (idStr.length() < 5) {
                idStr += " ";
            }
            LOG(lineNo + "ID: " + idStr);
            if (isElite[id]) {
                LOGLN(" ELITE");
            } else if (isCheck[id]){
                LOGLN(" CHECKED");
            } else {
                LOGLN(" NORMAL");
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
        
        
        
        return analyzer.analyze();
    }

    static class Analyzer {
        ArrayList<Experiment> experiments;
        public Analyzer(ListIterator<Entry> entries) {
            System.out.println("accessing analyzer...");
            experiments = new ArrayList<Experiment>();
            while (entries.hasNext()) {
                Experiment experiment = new Experiment(entries);                
                experiments.add(experiment);
                //if (experiments.size() > 1000) {
                //    break;
                //}
                //experiment.varieties.add(variety);
            }
            
        }
        
        public int[] analyze() {
            System.out.println("accessing analyze...");
            ArrayList<Variety> varieties = new ArrayList<Variety>();
            for (Experiment experiment : experiments) {
                for (int i = 0; i < experiment.sectionSize; i += 1) {
                    Variety variety = new Variety();

                    for (Section section : experiment.sections) {
                        variety.entries.add(section.entries.get(i));
                    }
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
        
        double deviation(ArrayList<Double> yields) {
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
            ExperimentId = first.ExperimentID;
            totalYield = 0.0d;
            size = 0;
            
            add(first);
            
            
            while (iter.hasNext()) {
                Entry cur = iter.next();
                if (Rep != cur.Rep || LOCCD != cur.LOCCD) {
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
            ExperimentId = first.ExperimentID;
            totalYield = 0.0d;
            size = 0;
            
            add(first);
            
            while (entries.size() < sectionSize) {
                Entry cur = iter.next();
                if (entries.contains(cur)) {
                    continue;
                }
                if (Rep != cur.Rep || LOCCD != cur.LOCCD) {
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
                if (ExperimentId != cur.ExperimentID) {
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
                } else {
                
                    value = 1.0;
                }
            }
            return value;
        }
    }
    
    static class Entry implements Comparable<Entry> {
        int ExperimentID;//, - Each experiment has a different ID.
        short Year;// - Year in which the experiment was ran.
        int LOCCD;// - You can refer to Locations.csv to obtain additional information about each location. Please note that such information is not available for all locations.
        byte Rep;// - Number of the repetition of one trial. + 1
        byte Type;// -0 conv (Conventional),1 RR1 (Roundup Ready) or 2 RR2Y (Roundup Ready to Yield).
        int VarietyID;// - ID of the variety within experiment.
        double Yield;// - Yield of a variety given in bushels per acre.
        Double MN;// - Maturity number of a variety.
        Double RM;// - Relative maturity of a variety. It's given in case it's available and the variety is a check.
        boolean IsCheck;// - 1 if a variety is a check, 0 otherwise
        Boolean IsElite;//
        String raw;
        Section secton;
        Double value;
        
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
            ExperimentID = Integer.parseInt(data[0]);
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