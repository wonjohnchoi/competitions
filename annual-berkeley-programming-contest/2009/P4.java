/*
Wonjohn Choi
Solved with Java
2009 Annual Berkeley Programming Contest: Problem 4
*/

import java.io.*;
import java.util.*;
public class P4{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int i = 1;
        while(in.hasNextInt()){
            int n = in.nextInt();
            Vector<double[]> speeds =  new Vector<double[]>(n);
            for(int j=0;j<n;j++) {
                speeds.add(new double[]{in.nextDouble(), in.nextDouble()});
            }
            
            System.out.printf("Set %d: ", i); 
            printCIWS(speeds);
            i+=1;
        }
    }
    
    /**
    *Get Common Interval
    **/
    public static double[] getCI(Vector<double[]> speeds){
        double[] ci = new double[2];
        ci[0] = Integer.MIN_VALUE;
        ci[1] = Integer.MAX_VALUE;
        
        for(double[] speed: speeds){
            ci[0] = Math.max(ci[0], speed[0]);
            ci[1] = Math.min(ci[1], speed[1]);
        }
        
        if(ci[0]>=ci[1]) return null; //No consensus
        return ci;
    }
    
    /**
    *Print Common Interval With Subsets (*len(subset) > N/2)
    **/
    public static void printCIWS(Vector<double[]> speeds){
        double[] bestCI = null;
        int nMeasure = 0;
        
        for(int i = (int)Math.pow(speeds.size(), 2)-1; i>=0; i--){
            String bin = Integer.toString(i, 2);
            int j = 0;
            for(char c: bin.toCharArray()){
                if(c=='1') j+=1;
            }
            
            if(j > speeds.size()/2){
                while(bin.length() != speeds.size()) bin = "0"+bin;
                Vector<double[]> speeds2 = new Vector<double[]>();
                
                for(int k=0; k<bin.length(); k++){
                    if(bin.charAt(k) == '1'){
                        speeds2.add(speeds.get(k));
                    }
                }
                
                double[] CI = getCI(speeds2);
                if(CI!=null){
                    if(bestCI==null) {
                        bestCI = CI;
                        nMeasure = speeds2.size();
                    }
                    else if(CI[0]<=bestCI[0] && speeds2.size()>=nMeasure){
                        bestCI = CI;
                        nMeasure = speeds2.size();
                    }
                }
            }
        }
        
        if(bestCI == null) System.out.println("No consensus");
        else System.out.printf("(%f, %f) from %d measurements\n", bestCI[0], bestCI[1], nMeasure);
    }
    

}