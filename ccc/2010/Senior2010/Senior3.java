package capcs.choi.yr2010;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @problemFrom http://cemc.math.uwaterloo.ca/contests/computing/2010/stage1/seniorEn.pdf
 * @lang Java (6)
 * @date 2010-12-22
 * @author Wonjohn Choi
 * @workOn case #1-4
 *
 */
public class Senior3 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(new FileReader("s3.5.in"));
        
        int nHouse = sc.nextInt();
        
        CircularArray houses =  new CircularArray(nHouse);
        
        for(int i=0;i<nHouse;i++){
            houses.addHouse(new House(sc.nextInt()));
        }
        
        houses.sort();
        
        int nFireHose = sc.nextInt();
        
        getMinHoseLength(nFireHose, houses, -1);
        
        System.out.println(minHoseLength);
    }
    
    static int minHoseLength = 1000000;
    
    public static void getMinHoseLength(int nFireHoseLeft, CircularArray houses, int maxLength){
        if(houses.isAllUsed()){
            if(minHoseLength>maxLength){
                minHoseLength = maxLength;
            }
        }else if(nFireHoseLeft == 0 ){
            
        }else{
            for(int i=0;i<houses.length();i++){
                if(!houses.getHouse(i).isUsed){
                    for(int j=i;j<houses.length();j++){
                        if(!houses.getHouse(j).isUsed){
                            //System.out.println(houses.getHouse(i).pos +" "+houses.getHouse(j).pos);
                            boolean inOrder = houses.useHouseInRange(i, j, false);
                            int length = 0;
                            if(inOrder){
                                length = (int) Math.ceil((houses.getHouse(j).pos - houses.getHouse(i).pos)/2.0);
                            }else{
                                length = (int)Math.ceil((1000000-houses.getHouse(j).pos + houses.getHouse(i).pos)/2.0);
                            }
                            
                            getMinHoseLength(nFireHoseLeft-1, houses, Math.max(maxLength, length));
                            
                            houses.useHouseInRange(i, j, true);
                        }
                    }
                }
            }
        }
    
    }
}

class CircularArray{
    House[] array;
    int next = 0;
    
    CircularArray(int nHouse){
        array = new House[nHouse];
    }
    
    void addHouse(House newHouse){
        array[next] = newHouse;
        next++;
    }
    
    House getHouse(int idx){
        idx = idx%array.length;
        if(idx<0) idx+=array.length;
        return array[idx];
    }
    
    boolean useHouseInRange(int idx1, int idx2, boolean reverse){
        if((array[idx2].pos-array[idx1].pos)<=500000){ //POSSIBLE PROBLEM on '='
            for(int i=idx1;i<=idx2;i++){
                array[i].isUsed = !reverse;
            }
            
            return true;
        }else{
            for(int i=idx2;i<array.length;i++){
                array[i].isUsed = !reverse;
            }
            for(int i=0;i<=idx1;i++){
                array[i].isUsed = !reverse;
            }
            
            return false;
        }
    }
    
    boolean isAllUsed(){
        for(int i=0;i<array.length;i++){
            if(!array[i].isUsed){
                return false;
            }
        }
        
        return true;
    }
    
    void sort(){
        Arrays.sort(array);
    }
    
    int length(){
       return array.length; 
    }
}

class House implements Comparable<House>{
    int pos;
    boolean isUsed;
    House(int position){
        pos = position;
        isUsed = false;
    }
    @Override
    public int compareTo(House o) {
        return pos-o.pos;
    }
}
