/**
Wonjohn Choi
Solved with Java
2010 Annual Berkeley Programming Contest: Problem 2
*/
import java.util.*;
public class P2{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        int cnt = 0;
        while(in.hasNextInt()){
            int size = -1;
            
            int n = in.nextInt();
            
            //get data of map
            int[][] routes = new int[n][2];
            for(int i=0; i<n; i++){
                for(int j=0; j<2; j++){
                    routes[i][j] = in.nextInt();
                    size = Math.max(size, routes[i][j]);
                }
            }
            
            Matrix.size = size+1;
            Matrix init = new Matrix();
            
            //save data of map to a "Matrix" (class defined below)
            for(int i=0; i<n; i++){
                init.matrix[routes[i][0]][routes[i][1]] = 1;
            }
            
            //multiplying a matrix to itself yields a matrix that tells how many routes exist between two nodes (*mathematical)
            Matrix sum = new Matrix();
            Matrix cur = init;
            for(int i=0; i<Matrix.size; i++){
                sum = sum.add(cur);
                cur = cur.multiply(init);            
            }
            
            //array to remember intersections that will have infinite routes
            boolean[][] infinite = new boolean[Matrix.size][Matrix.size];
            for(int i=0; i<Matrix.size; i++){
                cur = cur.multiply(init);
                for(int r=0; r<Matrix.size; r++){
                    for(int c=0; c<Matrix.size; c++){
                        if(cur.matrix[r][c]>0){
                            infinite[r][c] = true;
                        }
                    }
                }
            }
            
            //for infinite routes, intersection for the two nodes becomes -1
            for(int r=0; r<Matrix.size; r++){
                for(int c=0; c<Matrix.size; c++){
                    if(infinite[r][c]){
                        sum.matrix[r][c] = -1;
                    }
                }
            }
            
            System.out.printf("matrix for city %d\n", cnt);
            System.out.println(sum);
            
            cnt+=1;
        }
    }
}

class Matrix{
    static int size;
    int[][] matrix;
    
    public Matrix(){
        matrix = new int[size][size]; //elements are zero by default
    }
    
    public Matrix multiply(Matrix m1){
        Matrix m2 = new Matrix();
        for(int r=0; r<size ;r++){
            for(int c=0; c<size; c++){
                for(int i=0; i<size; i++){
                    m2.matrix[r][c]+= matrix[r][i]*m1.matrix[i][c];
                }
            }
        }
        return m2;
    }
    
    public Matrix add(Matrix m1){
        Matrix m2 = new Matrix();
        
        for(int r=0; r<size; r++){
            for(int c=0; c<size; c++){
                m2.matrix[r][c] = matrix[r][c] + m1.matrix[r][c];
            }
        }
        
        return m2;
    }
    
    public String toString(){
        String s = "";
        for(int r=0; r<size; r++){
            for(int c=0; c<size; c++){
                s+=matrix[r][c]+"";
            }
            if(r!=size-1) s+="\n";
        }
        return s;
    }
}