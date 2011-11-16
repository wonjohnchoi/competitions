/**
Wonjohn Choi
Solved with Java
2010 Annual Berkeley Programming Contest: Problem 5
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class P5{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String equation = in.next();
            String equ[] = equation.split("=");
            HashMap<String, Integer> h1 = new HashMap<String, Integer>();
            HashMap<String, Integer> h2 = new HashMap<String, Integer>();
            
            for(String molecule: equ[0].split("\\+")){
                mergeRetainingValues(h1, getAtoms(molecule));
            }
            for(String molecule: equ[1].split("\\+")){
                mergeRetainingValues(h2, getAtoms(molecule));
            }
            
            System.out.print(equation);
            if(h1.equals(h2)){
                System.out.println(" balances");
            }else{
                System.out.println(" does not balance");
            }
            
        }
        System.exit(0);
    }
    
    public static void mergeRetainingValues(HashMap<String, Integer> h1, HashMap<String, Integer> h2){
        for(String key: h2.keySet()){
            if(!h1.containsKey(key)){
                h1.put(key, h2.get(key));
            }else{
                h1.put(key, h2.get(key)+h1.get(key));
            }
        }
    }
    
    public static HashMap<String, Integer> getAtoms(String molecule){
        char c; //temporary variable to represent characters
        
        HashMap<String, Integer> data = new HashMap<String, Integer>();
        int nMolecule = 1; //# of molecules

        c =  molecule.charAt(0);
        if(Character.isDigit(c)){
            nMolecule = (int)(c-'0');
            molecule = molecule.substring(1);
        }
        
        while(molecule.length()!=0){
            String atom = ""+molecule.charAt(0);
            int nAtom;
            
            //construct atom
            for(int i=1;i<molecule.length();i++){
                c = molecule.charAt(i);

                if(Character.isLowerCase(c)){
                    atom+=c;
                }else{
                    break;
                }
            }
            molecule = molecule.substring(atom.length(), molecule.length());
            
            if(molecule.length()==0 || Character.isUpperCase(molecule.charAt(0))){
                nAtom = 1;
            }else{
                //contruct coefficient of an atom
                String number = "";
                for(int i=0;i<molecule.length();i++){
                    c = molecule.charAt(i);
                    if(Character.isDigit(c)){
                        number+=c;
                    }else{
                        break;
                    }
                }
                
                nAtom = Integer.parseInt(number);
                molecule = molecule.substring(number.length());
                
            }
            
            data.put(atom, nAtom*nMolecule);
        }
        
        return data;

        
    }
    
}