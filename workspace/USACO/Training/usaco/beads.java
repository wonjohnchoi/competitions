package usaco;

/*
ID: yojo1002
LANG: JAVA
TASK: beads
*/
import java.io.*;

class beads
{
	//charAt(pos)~pos~charAt(pos+1)
	public static int countL(int numBeads, String beads, char previous, char lastColor)
	{
		int ans=0;
		if(numBeads==0)
		{
			ans=0;
		}
		else if(numBeads==1)
		{
			if(beads.charAt(numBeads-1)=='w'||(beads.charAt(numBeads-1)=='r' && previous=='r')||(beads.charAt(numBeads-1)=='r' 
				&& previous=='w'&&lastColor=='r')||(beads.charAt(numBeads-1)=='b'&&previous=='b')||(beads.charAt(numBeads-1)=='b'
					&&previous=='w'&&lastColor=='b'))
				ans++;
		}
				
		else if(beads.charAt(numBeads-1)=='w')
		{
			numBeads-=1;
			ans+=(1+countL(numBeads, beads.substring(0,numBeads), 'w', lastColor));
		}
		else if(beads.charAt(numBeads-1)=='r' && previous=='r')
		{
			numBeads-=1;
			ans+=(1+countL(numBeads, beads.substring(0,numBeads), 'r','r'));
		}
		else if(beads.charAt(numBeads-1)=='r' && previous=='w' &&lastColor!='b')
		{
			numBeads-=1;
			ans+=(1+countL(numBeads, beads.substring(0,numBeads), 'w','r'));
		}
		else if(beads.charAt(numBeads-1)=='b'&&previous=='b')
		{
			numBeads-=1;
			ans+=(1+countL(numBeads, beads.substring(0,numBeads), 'b','b'));
		}
		else if(beads.charAt(numBeads-1)=='b'&&previous=='w' && lastColor!='r')
		{
			numBeads-=1;
			ans+=(1+countL(numBeads, beads.substring(0,numBeads), 'w','b'));
		}
		
		return ans;
		
	}
	
	public static int countR(int numBeads, String beads, char previous, char lastColor)
	{
		int ans=0;
		if(numBeads==0)
		{
			ans=0;
		}
		else if(numBeads==1)
		{
			if(beads.charAt(0)=='w'||(beads.charAt(0)=='r' && previous=='r')||(beads.charAt(0)=='r' && previous=='w'&&lastColor=='r')||
					(beads.charAt(0)=='b'&&previous=='b')||(beads.charAt(0)=='b'&&previous=='w'&&lastColor=='b'))
				ans++;
		}
		
		else if(beads.charAt(0)=='w')
		{
			ans+=(1+countR(numBeads-1, beads.substring(1,numBeads), 'w',lastColor));
		}
		else if(beads.charAt(0)=='r' && previous=='r')
		{
			ans+=(1+countR(numBeads-1, beads.substring(1,numBeads), 'r','r'));
		}
		else if(beads.charAt(0)=='r' && previous=='w'&&lastColor!='b')
		{
			ans+=(1+countR(numBeads-1, beads.substring(1,numBeads), 'w','r'));
		}
		else if(beads.charAt(0)=='b'&&previous=='b')
		{
			ans+=(1+countR(numBeads-1, beads.substring(1,numBeads), 'b','b'));
		}
		else if(beads.charAt(0)=='b'&&previous=='w'&&lastColor!='r')
		{
			ans+=(1+countR(numBeads-1, beads.substring(1,numBeads),'w','b'));
		}
		return ans;
		
	}
	
	public static void main (String [] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		int numBeads=Integer.parseInt(f.readLine());
		String beads=f.readLine();
		int theBest=0;
		int temp, countRep=0;
		
		while(countRep<=numBeads)
		{
			for(int pos=0;pos<numBeads-2;pos++)
			{
				temp=countL(pos+1, beads.substring(0,pos+1), 'w','w')
				+countR(numBeads-pos-1,beads.substring(pos+1,numBeads),'w','w');
				if(temp>theBest)
				{
					theBest=temp;
				}
			}
			beads=beads.substring(1,numBeads)+beads.charAt(0);
			countRep++;
		}
		if(numBeads==1)
		{
			theBest=1;
		}
		if(numBeads==2)
		{
			theBest=2;
		}
		if(numBeads==3)
		{
			theBest=3;
		}
		out.println(theBest);
    
		out.close();                                 
		System.exit(0);                               
	}
}

