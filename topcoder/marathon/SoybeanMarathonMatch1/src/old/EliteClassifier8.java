package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class EliteClassifier8 {
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
    static boolean VALIDATE = false;
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
        logln("VERSION: " + VERSION);
        logln("DESCRIPTION: " + DESCRIPTION);
        logln("----------------------------------------");

    }
    
    static PrintWriter LOGGER = null;
    static final int VERSION = 8;
    static final String DESCRIPTION = "Refacter code so that I can repeat classify to find the optimal variables.";
    static String BASE = "/Users/wonjohnchoi/Dropbox/source-codes/competitions/topcoder/marathon/SoybeanMarathonMatch1/";
    public static void main(String args[]) {
        if (args.length == 0) {
            LOG = false;
        } else if (args[0].equals("log")) {
            LOG = true;
        }
        setupLOGGER();
        Scanner all = open(BASE + "DataTraining.csv");
        System.out.println(evalAnalyzer(createAnalyzer(all), new Constants()));
       // System.out.println(getOptimalConstants(all));
        if (LOG)
            LOGGER.close();

        /*
        for (int i = 0; i < Analyzer.deviations.length; i += 1) {
            System.out.println(i + ": " + Analyzer.deviations[i]);
        }*/
        
        /*
        System.out.println("Unknown: " + Analyzer.mns[299] + ", " + (double) Analyzer.mns[299].nElite / (Analyzer.mns[299].nElite + Analyzer.mns[299].nNormal));
        for (int i = 0; i < Analyzer.mns.length - 1; i += 1) {
            System.out.println(i+": "+Analyzer.mns[i] + ", " + (double) Analyzer.mns[i].nElite / (Analyzer.mns[i].nElite + Analyzer.mns[i].nNormal));
        }*/
     }
    
    public static Constants getOptimalConstants(Scanner all) {
        //0.1, 0.0, 0.4, 0.5, 0.8, 0.9, 1.1, 1.0

        Analyzer analyzer = createAnalyzer(all);
        Constants bestConstants = new Constants();
        double bestValue = evalAnalyzer(analyzer, bestConstants);
        for (double dr = 0.000000001; dr < 10000; dr *= 10) {
            

                Constants constants = new Constants();
                constants.deviationRatio = dr;
                double value = evalAnalyzer(analyzer, constants);
                System.out.println("value: " + value);
                System.out.println(constants.deviationRatio);

                if (value > bestValue) {
                    bestValue = value;
                    bestConstants = constants;
                }
                
            
        }
        System.out.println("best of best: " + bestValue);
        System.out.println("best constants: " + bestConstants.deviationRatio);
        return bestConstants;
    }
    
    public static Analyzer createAnalyzer(Scanner all) {
        System.out.println("accessing createAnalyzer...");
        LinkedList<Entry> entries = new LinkedList<Entry>();
        while (all.hasNextLine()) {
            Entry entry = new Entry(all.nextLine());
            entries.add(entry);
        }
        return new Analyzer(entries);
        
    }
    
    public static double evalAnalyzer(Analyzer analyzer, Constants constants) {
        System.out.println("accessing evalAnalyzer...");
        HashSet<Integer> varietyId = null;
        if (VALIDATE) {
            varietyId = new HashSet<Integer>();
    
            for (Entry entry : analyzer.entries) {
                varietyId.add(entry.VarietyID);
            }
        }
        int[] classified = analyzer.classify(constants);
        /*
        analyzer.percentages = new Percentage[2][2][2];
        for (int i = 0; i < 2; i += 1) {
            for (int j = 0; j < 2; j += 1) {
                for (int k = 0; k < 2; k += 1) {
                    analyzer.percentages[i][j][k] = new Percentage();
                }
            }
        }*/
        //logln(analyzer.toString());
        
        //LOGGER.close();
        if (VALIDATE) {
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
        }
        
        HashSet<Integer> elites = new HashSet<Integer>();
        HashSet<Integer> checks = new HashSet<Integer>();
        for (Entry entry : analyzer.entries) {
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
        
        if (LOG) {
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
        Analyzer analyzer = new Analyzer(entries);
        
        
        
        return analyzer.classify(new Constants());
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
    
    static class Constants {
        double numTrialRatio0, numTrialRatio1, numTrialRatio2, numTrialRatio3, numTrialRatio4, numTrialRatio5, numTrialRatio6, numTrialRatio7, numTrialRatio8;
        double deviationRatio = 1.0;
        Constants() {
            numTrialRatio1 = 0.2;
            numTrialRatio2 = 0.2;
            numTrialRatio3 = 0.4;
            numTrialRatio4 = 0.5;
            numTrialRatio5 = 0.8;
            numTrialRatio6 = 0.9;
            numTrialRatio7 = 1.1;
            numTrialRatio8 = 1.2;
            numTrialRatio0 = 1.3;
            deviationRatio = 1.0;
            
        }
        
        public String toString() {
            return
                + numTrialRatio1 + ", "
                + numTrialRatio2 + ", "
                + numTrialRatio3 + ", "
                + numTrialRatio4 + ", "
                + numTrialRatio5 + ", "
                + numTrialRatio6 + ", "
                + numTrialRatio7 + ", "
                + numTrialRatio0;
        }
    }
    
    static class Analyzer {
        List<Entry> entries;
        ArrayList<Experiment> experiments;
        //static Percentage[] deviations = new Percentage[200];
        //static Percentage[] mns = new Percentage[300];
        
        public Analyzer(List<Entry> entries) {
            System.out.println("accessing Analyzer...");
            this.entries = entries;
            ListIterator<Entry> iter = entries.listIterator();
            experiments = new ArrayList<Experiment>();
            while (iter.hasNext()) {
                Experiment experiment = new Experiment(iter);                
                experiments.add(experiment);
            }
            
            /*
            for (int i = 0; i < deviations.length; i += 1) {
                deviations[i] = new Percentage();
            }
            
            for (int i = 0; i < mns.length; i += 1) {
                mns[i] = new Percentage();
            }
            
            for (Entry entry : entries) {
                int mn = entry.MN == null? 299 : entry.MN >= 299? 298 : entry.MN < 0? 299 : (int) entry.MN.doubleValue();
                
                if (entry.IsElite)
                    mns[mn].nElite += 1;
                else if (entry.IsCheck)
                    mns[mn].nCheck += 1;
                else
                    mns[mn].nNormal += 1;
            }*/
            
            
           
        }
        
        
        
        public int[] classify(Constants constants) {
            System.out.println("accessing Analyzer.classify...");
            ArrayList<Variety> varieties = new ArrayList<Variety>();
            for (Experiment experiment : experiments) {
                for (int i = 0; i < experiment.sectionSize; i += 1) {

                    LinkedList<Entry> entries = new LinkedList<Entry>();

                    for (Section section : experiment.sections) {
                        if (EliteClassifier8.VALIDATE) {
                            /* Data Validation */
                            if (entries.size() != 0) {
                                if (entries.get(0).VarietyID != section.entries.get(i).VarietyID) {
                                    System.err.println("VarietyID of a corresponding position of a section is different from that of previous section");
                                    System.exit(1);
                                }
                            }
                        }
                        entries.add(section.entries.get(i));

                    }
                    Variety variety = new Variety(entries, constants);

                    if (EliteClassifier8.VALIDATE) {
                        /* Data Validation */
                        for (Variety elem : varieties) {
                            if (elem.entries.get(0).VarietyID == variety.entries.get(0).VarietyID) {
                                System.err.println("Same VarietyID used in two different experiments");
                                System.err.println("Experiment 1: " + elem.entries.get(0).section.experiment);
                                System.err.println("Experiment 2: " + variety.entries.get(0).section.experiment);
                                System.err.println("Variety ID: " + elem.entries.get(0).VarietyID);
    
                                System.exit(1);
                            }
                        }
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
        //static int[] deviations = new int[200];
        //double percentage = 1.0;
        Constants constants;
        Variety(LinkedList<Entry> newEntries, Constants constants) {
            this.constants = constants;
            entries = newEntries;
            /*
            for (Entry entry : entries) {
                Percentage p = Analyzer.percentages[entry.Type][(int)entry.Yield.doubleValue()][(int)entry.MN.doubleValue()];
                percentage += (double) p.nElite / (p.nElite + p.nNormal);
            }
            if (entries.get(0).IsCheck) {
                percentage = 0;
            }*/
            ArrayList<Double> yields = new ArrayList<Double>();

            for (Entry entry : entries) {
                yields.add(entry.Yield);
            }
            /*
            for (Entry entry : entries) {
                if (entry.IsElite) {
                    Analyzer.deviations[(int)deviation(yields)].nElite += 1;
                } else if (entry.IsCheck) {
                    Analyzer.deviations[(int)deviation(yields)].nCheck += 1;
                } else {
                    Analyzer.deviations[(int)deviation(yields)].nNormal += 1;
    
                }
            }*/
            if (entries.get(0).IsCheck) {
                value = Double.NEGATIVE_INFINITY;
            } else {
                for (Entry entry : entries) {
                    //System.out.println(entry.secton.avg());
                    totalScore += entry.Yield - entry.section.avgYield();
                    nScore += 1;
                    /*
                    if (entry.MN != null && entry.section.avgMN() != null) {
                        totalScore += entry.section.avgMN() - entry.MN;
                        nScore += 1;
                    }*/
                    
                }
                value = totalScore / nScore;
                
                Entry entry = entries.get(0);
                
                
                
                double experimentValue = 0.0;
                
                if (entry.section.experiment.sections.size() == 1) {
                    experimentValue = constants.numTrialRatio1;
                } else if (entry.section.experiment.sections.size() == 2) {
                    experimentValue = constants.numTrialRatio2;
                } else if (entry.section.experiment.sections.size() == 3) {
                    experimentValue = constants.numTrialRatio3;
                } else if (entry.section.experiment.sections.size() == 4) {
                    experimentValue = constants.numTrialRatio4;
                } else if (entry.section.experiment.sections.size() == 5) {
                    experimentValue = constants.numTrialRatio5;
                } else if (entry.section.experiment.sections.size() == 6) {
                    experimentValue = constants.numTrialRatio6;
                } else if (entry.section.experiment.sections.size() == 7) {
                    experimentValue = constants.numTrialRatio7;
                } else if (entry.section.experiment.sections.size() == 8) {
                    experimentValue = constants.numTrialRatio8;
                } else {
                    experimentValue = constants.numTrialRatio0;
                }
                
                value *= experimentValue;
                //System.out.println(value);
                //System.out.println(deviation(yields));*/
                   
                
                double dev = deviation(yields);
                if (dev > 65) {
                    value -= 1000;
                } 
                //value *= 1/deviation(yields)*constants.deviationRatio;
                
            }
        }
        
        static double avg(ArrayList<Double> yields) {
            double tot = 0;
            int cnt = 0;
            for (Double yield : yields) {
                tot += yield;
                cnt += 1;
            }
            return tot / cnt;
        }
        
        static double deviation(ArrayList<Double> yields) {
            double avg = avg(yields);
            double dev = 0;
            for (Double yield : yields) {
                dev += Math.pow((yield - avg), 2);
            }
            dev = Math.sqrt(dev);
            return dev;
        }

        @Override
        public int compareTo(Variety arg0) {
            return arg0.value.compareTo(value);
        }
    }
    
    static class Section {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        int Rep, LOCCD, ExperimentId;
        double totalYield;
        int size, MNSize;
        Experiment experiment;
        double totalMN;
        
        void setup(ListIterator<Entry> iter) {
            Entry first = iter.next();
            Rep = first.Rep;
            LOCCD = first.LOCCD;
            ExperimentId = first.ExperimentId;
            totalYield = 0.0d;
            size = 0;
            MNSize = 0;
            totalMN = 0.0d;
            add(first);

        }
        public Section (ListIterator<Entry> iter, Experiment experiment) {
            setup(iter);
            this.experiment = experiment;
            
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

        public Section (ListIterator<Entry> iter, Experiment experiment, int sectionSize) {
            setup(iter);
            this.experiment = experiment;


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
            entry.section = this;
            totalYield += entry.Yield;
            size += 1;
            if (entry.MN != null) {
                MNSize += 1;
                totalMN += entry.MN;

            }

        }
        
        public double avgYield() {
            return totalYield / size;
        }
        public Double avgMN() {
            if (MNSize == 0) return null;
            return totalMN / MNSize;
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
                    sections.add(new Section(iter, this, sectionSize));
                }   
            }
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
        Section section;
        
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