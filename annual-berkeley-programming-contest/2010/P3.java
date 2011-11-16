/**
Wonjohn Choi
Solved with Java
2010 Annual Berkeley Programming Contest: Problem 3
*/
import java.util.*;
public class P3{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        //collection of possible answers
        Vector<Vector<Integer>> poss = new Vector<Vector<Integer>>(1296);
        generate(poss);      
        
        //up to 4 numbers that MUST be on the correct solution based on guesses
        Vector<Integer> has = new Vector<Integer>();
        
        //we will combine elements of 'has' Vector and several i's to make up a 4-letter guess
        int i = 1;
        
        //simply, take a guess. Using c and d, filter impossible (illogical) guesses from the 'poss' Vector
        //it will be repeated until guess is correct or one guess remains
        while(!poss.isEmpty()){
            //choose what to guess next
            Vector<Integer> next;
            if(has.size()!=4){
                next = new Vector<Integer>(has);
                for(int j=0;j<4-has.size();j++){
                    next.add(i);
                }
    
                poss.remove(next);
            }else{
                next = poss.remove(0);
            }
            
            printVector(next);
            
            int c = in.nextInt();
            int d = in.nextInt();
            
            //guess is right -> done
            if(c==4) {
                System.exit(0);
            }
            
            if(has.size()!=4){
                while(c+d!=has.size()){
                    has.add(i);
                }
                i+=1;
            }
            
            //filter the illogical possibilities
            Vector<Vector<Integer>> illogical = new Vector<Vector<Integer>>();
            
            for(Vector<Integer> p: poss){
                if(!isLogical(next, p, c, d)){
                    illogical.add(p);
                }
            }
            
            poss.removeAll(illogical);
        }
        
        System.out.println(poss);
    }
    
    public static void generate(Vector<Vector<Integer>> poss){
        for(int i=1; i<=6; i++){
            for(int j=1; j<=6; j++){
                for(int k=1; k<=6; k++){
                    for(int l=1; l<=6; l++){
                        poss.add(new Vector<Integer>(Arrays.asList(i,j,k,l)));
                    }
                }
            }
        }  
    }
    
    public static boolean isLogical(Vector<Integer> guess, Vector<Integer> solution, int c, int d){
        int[] result = check(guess, solution);
        return result[0]==c && result[1]==d;
    }
    
    public static int[] check(Vector<Integer> guess, Vector<Integer> solution){
        int c = 0;
        int d = 0;
        
        boolean matched[] = new boolean[4];
        for(int i=0;i<4;i++){
            if(guess.get(i)==solution.get(i)){
                matched[i] = true;
                c+=1;
            }
        }
        
       for(int i=0;i<4;i++){
            if(guess.get(i)!=solution.get(i)){
                for(int j=0;j<4;j++){
                    if(!matched[j]){
                        if(guess.get(i)==solution.get(j)){
                            matched[j] = true;
                            d+=1;
                        }
                    }
                }
            }
       }
       
       return new int[]{c,d};
    }
    
    public static void printVector(Vector<Integer> v){
        System.out.printf("%d%d%d%d\n", v.get(0), v.get(1), v.get(2), v.get(3));
    }
}