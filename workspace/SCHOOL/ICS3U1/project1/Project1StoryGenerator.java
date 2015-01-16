package project1;

/*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, September 25, 2009
 */
import java.util.*;

public class Project1StoryGenerator{
  public static void main(String[] args){
    //creat new Scanner, which is named choi.
    Scanner choi=new Scanner(System.in);
    //Welcome^^
    System.out.println("Welcome to Wonjohn's StoryGenerator");
    //When you use String, if you use space, it makes error
    System.out.println("Please do not use space when you give input to computer");
    //Ask user to give inputs to make a story.
    System.out.println("Provide the following input");
    //This is for distinguishing whether the main character is female or male.
    System.out.println("Put 'He' if male or put 'She' if female");
    //Store the value using String as k.
    String k=choi.next();
    //From here, there are general questions.
    System.out.print("Name: ");
    String a=choi.next();
    System.out.print("Country: ");
    String b=choi.next();
    System.out.print("(1~30)Date: ");
    int c=choi.nextInt();
    System.out.print("(1000~3000)Year: ");
    int d=choi.nextInt();
    System.out.print("City: ");
    String e=choi.next();
    System.out.print("Province: ");
    String f=choi.next();
    System.out.print("(10~18)Age: ");
    int g=choi.nextInt();
    System.out.print("SchoolName: ");
    String h=choi.next();
    System.out.print("Verb: ");
    String i=choi.next();
    System.out.print("(1~12)Time: ");
    int j=choi.nextInt();
    //Now, time to see which story you got!
    System.out.println("Here is your story:");
    System.out.println(a + " was born at January "+c+", "+d+" in "+e+ ", "+f+", "+b+".");
    System.out.println(k+" is "+g+" years old and going to a Korean school, "+h+", which is located in Seoul, Korea.");
    System.out.println(k+" went to Korea since "+k+" was interested in Korean culture and wanted to learn Korea. ");
    System.out.println(k + " likes to "+i+" and usually, goes to a park to walk around. ");
    System.out.println("One day, "+k+" went to the park at 11 am and walked around until "+j+" pm.");
    System.out.println("Then, "+k+" suddenly met a robber and decided to "+i+".");
    } 
  }

                                   