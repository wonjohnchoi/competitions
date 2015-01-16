package capcs.choi.yr2010;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @problemFrom http://cemc.math.uwaterloo.ca/contests/computing/2010/stage1/seniorEn.pdf
 * @lang Java (6)
 * @date 2010-12-22
 * @author Wonjohn Choi
 *
 */
public class Senior5 {
    public static void main(String args[]) throws IOException{
        String file = "s5."+8;
        
        Scanner sc = new Scanner(new FileReader(file+".in"));        
        String treeDetail = sc.nextLine();
        int growthAgents = Integer.parseInt(sc.nextLine());
        
        Tree.nGrowth = growthAgents;
        Tree root = new Tree(treeDetail);
        
        System.out.println(root.values[Tree.nGrowth]);
        sc.close();
    }
}


class Tree {
    static int nGrowth;
    Tree left, right;
    int[] values, flows;
    int value = -1;

    Tree(String input){
        extract(input);
        getValues();
        getFlows();
        
    }

    
    void getValues(){
        values = new int[nGrowth+1];
        if(value!=-1){
            for(int i=0;i<=nGrowth;i++){
                values[i] = i+value;
            }
        }else{
            for(int i=0;i<=nGrowth;i++){
                for(int j=0;j<=nGrowth-i;j++){
                    values[i+j] = Math.max(values[i+j], left.flows[i] + right.flows[j]);
                }
            }
        }
    }
    
    void getFlows(){
        flows = new int[nGrowth+1];
        for(int i=0;i<=nGrowth;i++){
            for(int j=0;j<=nGrowth-i;j++){
                flows[i+j] = Math.max(flows[i+j], Math.min(values[i], (1+j)*(1+j)));
            }
        }
    }
    
    void extract(String input){
        if(input.contains("(")){
            input = input.trim().substring(1, input.length()-1).trim();
            
            int splitPos = 0;
            int balance = 0;
            
            while(true){
                char curChar = input.charAt(splitPos);
                if(curChar==')') balance++;
                else if(curChar=='(') balance--;
                else if(curChar==' ' && balance==0) break;
                splitPos++;
            }
            
            left = new Tree(input.substring(0,splitPos));
            right =  new Tree(input.substring(splitPos+1));
        }else{
            value = Integer.parseInt(input);
        }
    }
}