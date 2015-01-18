package Junior2010;

import java.util.Scanner;

public class J2 {
	public static void main(String args[])
	{
		//Scanner to process input
		Scanner sc=new Scanner(System.in);
		
		//forwardN,backwardN are steps of Nikky
		//forwardB,backwardB are stops of Byron
		//time is the time till they stop.
		int forwardN,backwardN,forwardB,backwardB, time;
		
		//get input from user
		forwardN=sc.nextInt();
		backwardN=sc.nextInt();
		forwardB=sc.nextInt();
		backwardB=sc.nextInt();
		time=sc.nextInt();
		
		//winner will store the winner
		String winner="";
		
		//store the distances Nikky and Brian move (distN=Nikky, distB=Brian)
		int distN, distB;
		
		//calculate
		distN=getDist(forwardN,backwardN,time);
		distB=getDist(forwardB,backwardB,time);
		
		//decide winner
		if(distN>distB)
		{
			winner="Nikky";
		}
		else if(distB>distN)
		{
			winner="Byron";
		}
		else
		{
			winner="Tied";
		}
		
		//output the winner
		System.out.println(winner);
	}
	public static int getDist(int forward, int backward, int time)
	{
		//dist to store the distance
		int dist=0;
		
		//till time ends,
		while(time!=0)
		{
			//if time is enough to finish foward movement,
			if(forward<=time)
			{
				//subtract the time needed
				time-=forward;
				//add the forward steps to dist.
				dist+=forward;
			}
			//if time is not enough,
			else
			{
				//move as much as possible (for the amount of time, dist increases)
				dist+=time;
				//time becomes zero
				time=0;
			}
			
			//after finishing forwarding move, check the same thing with backward
			//if time is enough to finish backward movement,
			if(backward<=time)
			{
				//subtract the time needed
				time-=backward;
				//subtract the amount of backward moves from total distance
				dist-=backward;
			}
			//if time is not enough
			else
			{
				//move back as much as possible
				dist-=time;
				//time is zero left
				time=0;
			}
		}
		
		//returns the calculated distance
		return dist;
	}
}
