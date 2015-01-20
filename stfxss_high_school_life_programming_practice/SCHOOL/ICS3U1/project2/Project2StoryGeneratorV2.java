package project2;

/*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, October 12, 2009
 */
import java.util.*;

public class Project2StoryGeneratorV2{
  public static void main(String[] args){
    //made a new Scanner named choi
    Scanner choi=new Scanner(System.in);
    //It prints out explanation of this program
    System.out.println("Welcome to Wonjohn's Choose your own adventure");
    System.out.println("Depends on the option you choose, your adventure will vary, so try many times! ");
    System.out.println("Also, you only need to use A or B to proceed");
    System.out.println();
    //It prints out introduction of this program.
    System.out.println("You were sleeping in your home and just got up at 8 am; however,");
    System.out.println("you still feel sleepy. Do you want to continue to sleep (A) or ");
    System.out.println("go to washroom to wake up completely (B)?");
    //made String firstOption to receive A or B from users when they choose
    String firstOption=choi.next();
    //if firstOption is stored as A, program does this (below printing out).
    if(firstOption.charAt(0)=='A')
    {
      System.out.println("When you decided to sleep again, your mom came and told you that");
      System.out.println("you have to get up now since it is not good to sleep too long time");
      System.out.println("After you ate breakfast, you wanted to go outside, ");
    }
    //or if firstOption is stored as B, program does this (below printing out).
    else
    {
      System.out.println("After you came back from washroom, you wanted to go outside, ");
    }
    //continue story (printing out story)
    System.out.println("so you told your mom that you want to go out and she asked you"); 
    System.out.println("to promise to come back before 9 pm. Will you promise to come back"); 
    System.out.println("home before 9 pm(A)? Or do you want to persuade you mom to allow"); 
    System.out.println("you to stay with your friends all night(B)?");
    //made String secondOption to receive A or B from users when they choose 
    String secondOption=choi.next();
    //if secondOption is stored as B, program does this (below printing out).
    if(secondOption.charAt(0)=='B')
    {
      System.out.println("Fortunately, she permitted you to stay outside all night with your friends");
    }
    //or if secondOption is stored as A, program does this (below printing out).
    else
    {
      System.out.println("You promised to come back home before 9 pm.");
    }
    //continue story (print out stories continueously
    System.out.println("Outside home, You want to play sports, which sports would you like ");
    System.out.println("to play? A for soccer and B for basketball:");
    //made String thirdOption to receive A or B from users when they choose
    String thirdOption=choi.next();
    //if thirdOption is stored as A, program does this (below printing out).
    if(thirdOption.charAt(0)=='A')
    {
      System.out.println("While you played soccer, you fortunately discovered a box containing $1000.");
      System.out.println("Do you want to call police to find the owner(A) or use the money with your");
      System.out.println("friends?(B)");
      //made String fourthOption to receive A or B from users when they choose
      String fourthOption=choi.next();
      //Within above "if" function, there is another if function to make users' choice more complicated.
      //if fourthOption is A, program does this (below printing out).
      if(fourthOption.charAt(0)=='A')
      {
        System.out.println("After you called the police, they found the owner and they informed this fact");
        System.out.println("to newspaper companies in order to honour you. Later, your mom heard about this and she");
        System.out.println("was so proud of you that she bought a car for you");
        //One of the ending points (adventure over).
        System.out.println("----------------------Adventure Over------------------------");
      }
      //if fourthOption is not A (so it is B), program does this (below printing out).
      else
      {
        System.out.println("You went to a club with your friends and used the money you got. However, it was");
        System.out.println("discovered that the money was a counterfeit and you were caught by police. Although");
        System.out.println("you did not make it, police scolded you since you did not call police when you found");
        System.out.println("the money.");
        //One of the ending points (adventure over)
        System.out.println("----------------------Adventure Over------------------------");
      }
    }
    //if thridOption is not stored A (so it is B), program does this (below printing out).
    else
    {
      System.out.println("After you played basketball for a long time, when you saw ");
      System.out.println("your watch, it was already 8:45 pm.");
      //Again, within this else function, there is a if function to make this story more developed. 
      //This case is special since this recalls the value of secondOption, which was determined at the beginning.
      //if it was stored as A (means that you promised to come back home before 9pm), program goes through below printing out.
      if(secondOption.charAt(0)=='A')
      {
    
        System.out.println("You promised your mom to go back home before 9 pm, so you have to go now.");
        System.out.println("Will you follow the promise(A) or break it to play around more?(B)");
        //made String fifthOption to receive A or B from users when they choose
        String fifthOption=choi.next();
        //Again, a new if function is contained in the above else funtion & if function.
        //if fifthOption is A, program does this (below printing out).
        if(fifthOption.charAt(0)=='A')
        {
          System.out.println("You safely arrived home at the right time and went to bad");
          //One of the ending points (adventure over)
          System.out.println("----------------------Adventure Over------------------------");
        }
        //if not (fifthOption is B), program does this (below printing out).
        else
        {
          System.out.println("You decided not to go back home right now and had fun with your");
          System.out.println("friends. However, when you arrived home at the late time,");
          System.out.println("Your mom was really angry. Then, She almost killed you.");
          //One of the ending points (adventure over)
          System.out.println("----------------------Adventure Over------------------------");
        }
      }
      //this else function is included in above else function.
      //if secondOption was not A (so it was B), it goes through this (below printing out).
      else
      {
        System.out.println("Your mom allowed you to stay with your friends all night");
        System.out.println("So, you played around with your friends all night.");
        //One of the ending points (adventure over)
        System.out.println("----------------------Adventure Over------------------------");
      }
    }
  }
}

