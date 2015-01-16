package usaco;

/*
ID: yojo1002
LANG: JAVA
TASK: ride
*/
import java.io.*;

class ride {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    String s1,s2;
    s1=f.readLine();
    s2=f.readLine();
    int ans1=1,ans2=1;
    for(int i=0;i<s1.length();i++)
    {
    	ans1*=((int)s1.charAt(i)-64);
    }
    for(int i=0;i<s2.length();i++)
    {
    	ans2*=((int)s2.charAt(i)-64);
    }
    if(ans1%47==ans2%47)
    	out.println("GO");
    else
    	out.println("STAY");
    out.close();                                 
    System.exit(0);                            
  }
}

