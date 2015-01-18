package usaco;

/*
ID: yojo1002
LANG: JAVA
TASK: friday
*/
import java.io.*;

class friday 
{
	static boolean thirteen[];
	public static int toInt(int years)
	{
		int ans=years*365;
		for(int i=1900;i<=years+1899;i++)
		{
			if((i%4==0 && i%100!=0)||i%400==0)
			{
				ans++;
			}
		}
		return ans; 
	}
	public static int allToInt(int date, int month, int year)
	{
		int ans=(year-1900)*365+(month-1)*30+date;
		for(int i=1900;i<=year;i++)
		{
			if((i%4==0 && i%100!=0)||i%400==0)
			{
				ans++;
				if(i==year && (month==1||month==2))
				{
					ans--;
				}
			}
		}
		for(int j=1;j<=month;j++)
		{
			if(j==2 ||j==4||j==6||j==8||j==9||j==11||(j==12 && date==31))
			{
				ans++;
			}
			if(j==3)
			{
				ans-=2;
			}
		}
		return ans;
	}
	
	 
	
	public static void generate13th(int year)
	{

		for(int i=1900;i<=year+1899;i++)
			for(int j=1;j<=12;j++)
			{
				thirteen[allToInt(13,j,i)]=true;
			}
			
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		int time=Integer.parseInt(f.readLine());
		thirteen=new boolean[toInt(time)+10];
		generate13th(time);
		int numDay[]=new int[7];
		
		for(int i=1;i<=toInt(time);i++)
		{
			if(i%7==6 && thirteen[i])
			{
				numDay[0]++;
			}
			if(i%7==0 && thirteen[i])
			{
				numDay[1]++;
			}
			if(i%7==1 && thirteen[i])
			{
				numDay[2]++;
			}
			if(i%7==2 && thirteen[i])
			{
				numDay[3]++;
			}
			if(i%7==3 && thirteen[i])
			{
				numDay[4]++;
			}
			if(i%7==4 && thirteen[i])
			{
				numDay[5]++;
			}
			if(i%7==5 && thirteen[i])
			{
				numDay[6]++;
			}
		
		}
		out.println(numDay[0]+" "+numDay[1]+" "+numDay[2]+" "+numDay[3]+" "+numDay[4]+" "+numDay[5]+" "+numDay[6]);
    
		out.close();                              
		System.exit(0);                               
  }
}