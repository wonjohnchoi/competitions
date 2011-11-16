/*
Wonjohn Choi
Solved with Java
2009 Annual Berkeley Programming Contest: Problem 2
*/

import java.io.*;
import java.util.*;
public class P2{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] score = new int[n][n];
        
        while(in.hasNext()){
            int[] rank = new int[n];
            
            for(int idx=0;idx<4;idx++){
                String input = in.next();
                if(input.equals("-")){
                    rank[idx] = Integer.MAX_VALUE;
                }else{
                    rank[idx] = Integer.parseInt(input);
                }
            }
            
            update(score, rank);
        }
        
        int winner = -1;
        for(int i=0;i<n;i++){
            winner = i+1; //assume "i" is winner.
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(score[i][j] < score[j][i]){
                    winner = -1; //"i" is beaten by "j", so "i" is not winner.
                    break;
                }
            }
            
            if(winner!=-1) break; //winner is found.
        }
        
        if(winner==-1){
            System.out.println("Election goes to the House.");
        }else{
            System.out.printf("Candidate #%d wins.\n", winner);
        }
    }
    
    public static void update(int[][] score, int[] rank){
        for(int i=0;i<rank.length;i++){
            for(int j=0;j<rank.length;j++){
                if(rank[i]<rank[j]){ //if "i" has higher rank than "j"
                    score[i][j]+=1;
                }
            }
        }
    }

}