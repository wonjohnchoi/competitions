package choi.server;

import java.util.Arrays;


public class Engine3 {
    boolean[] fixed;
    int[] rack;
    int nMoves = 0;
    int totalScore = 0;
    int totalGame = 0;
    int wins = 0;
    int ties = 0;
    int loses = 0;
    static int version = 3;

    public Engine3() {
        fixed = new boolean[20];
    }

    public void reset() {
        fixed = new boolean[20];
        rack = null;
    }
    public void initRack(Object[] rackO) {
        if (rack == null) {
            rack = new int[20];
            for (int i = 0; i < rack.length; i += 1) {
                int val = (Integer) rackO[i];
                int idx = insert(val);
                if(idx == i) fixed[i] = true;
                rack[i] = val;

            }
        }
    }
    
    public int insert(int val) {
        int[] b = getBorders(val);
        int sections = b[1] - b[0] + 1;
        if(b[1] == -1) return 0;
        if(b[0] == 20) return 19;
        int left = 1;
        if (b[0] != 0) {
            left = rack[b[0] - 1] + 1;
        }
        int right = 80;
        if (b[1] != 19) {
            right = rack[b[1] + 1] - 1;
        }
//        System.out.println("\n"+val);
  //      System.out.println(Arrays.toString(fixed));
   //     System.out.println(Arrays.toString(rack));
        
     //   System.out.println(b[0] +" "+b[1]);
      //  System.out.println(left +" "+right);
        //adjustiable
        if(sections == 0) return b[0];
        /*double gap = (right - left) / sections;
        double start = left;
        for (int i = b[0]; i <= b[1]; i += 1) {
            double end = start + gap;
            System.out.println(start+" "+end);
            if (start <= val && val <= end) {
                return i;
            }
            start = end;
            
        }*/
        int idx = b[0] + (int)Math.round((b[1] - b[0]) * ((val - left + 1)/(double)(right - left + 1)));
        System.out.println("IDX: "+idx);
        return idx;
        //System.out.println("Index Not Assigned!\n\n\n\n\n\n\n");
       // System.exit(1);
        
       // return -1;
    }
    
    public static void main(String args[]) {
        Engine e = new Engine();
        e.initRack(new Object[]{54, 49, 58, 55, 48, 36, 52, 21, 77, 38, 40, 23, 75, 62, 43, 57, 4, 68, 28, 10});
        System.out.println(e.insert(45));
    }
    
    public int[] getBorders(int val) {
        int left = 0;
        for (int i = 0; i < rack.length; i += 1) {
            if (fixed[i]) {
                if (rack[i] < val) {
                    left = i + 1;
                } else {
                    break;
                }
            }
        }
        
        int right = 19;
        for (int i = 19; i >= 0; i -= 1) {
            if (fixed[i]) {
                if (val < rack[i]) {
                    right = i - 1;
                } else {
                    break;
                }
            }
        }
        return new int[]{left, right};
    }

    public int getIdx(int val) {/*
        int off = 4;
        for (int i = 0; i < rack.length; i += 1) {
           // if (!fixed[i]) {
                double[] range = getRange(off, i);
                if (range[0] <= val && val <= range[1]) {
                    fixed[i] = true;
                    rack[i] = val;
                    return i;
                }
           // } else {
                
           // }
        }
        return -1;*/
        int idx = insert(val);
        fixed[idx] = true;
        rack[idx] = val;
        return idx;
    }
/*
    public double[] getRange(double off, int idx) {
        double min, max;
        double center = center(idx);
        min = Math.max(1, center - off);
        max = Math.min(80, center + off);
        if (idx == 0) {
            if (fixed[idx + 1]) {
                max = rack[idx + 1];
            }
        } else if (idx == 19) {
            if (fixed[idx - 1]) {
                min = rack[idx - 1];
            }
        } else {
            if (fixed[idx + 1]) {
                max = rack[idx + 1];
            }
            if (fixed[idx - 1]) {
                min = rack[idx - 1];
            }
        }
        return new double[] { min, max };
    }*/
}
