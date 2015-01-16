package capcs.choi.yr20072008.round5;

import java.io.*;
public class Q5 {
	
	public static void rotate(char piece[][])
	{
		char temp[][]=new char[4][4];
		
		for(int k=0;k<4;k++)
		{
			for(int j=0;j<4;j++)
			{
				temp[k][j]=piece[k][j];
			}
		}
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				piece[j][i]=temp[i][3-j];
			}
		}
	}
	
	public static char[][] clean(char piece[][])
	{
		char temp[][]=new char[4][4];
		
		for(int k=0;k<4;k++)
		{
			for(int j=0;j<4;j++)
			{
				temp[k][j]=piece[k][j];
			}
		}	
		
		int cleanLeft=0;
		int cleanRight=0;
		int cleanTop=0;
		int cleanBottom=0;
		if(piece[0][0]=='.' &&  piece[0][1]=='.' && piece[0][2]=='.' && piece[0][3]=='.')
		{
			cleanLeft++;
			if(piece[1][0]=='.' &&  piece[1][1]=='.' && piece[1][2]=='.' && piece[1][3]=='.')
			{
				cleanLeft++;
				if(piece[2][0]=='.' &&  piece[2][1]=='.' && piece[2][2]=='.' && piece[2][3]=='.')
				{
					cleanLeft++;
				}
			}
		}
		
		if(piece[3][0]=='.' &&  piece[3][1]=='.' && piece[3][2]=='.' && piece[3][3]=='.')
		{
			cleanRight++;
			if(piece[2][0]=='.' &&  piece[2][1]=='.' && piece[2][2]=='.' && piece[2][3]=='.')
			{
				cleanRight++;
				if(piece[1][0]=='.' &&  piece[1][1]=='.' && piece[1][2]=='.' && piece[1][3]=='.')
				{
					cleanRight++;
				}
			}
		}
		
		if(piece[0][0]=='.' &&  piece[1][0]=='.' && piece[2][0]=='.' && piece[3][0]=='.')
		{
			cleanTop++;
			if(piece[0][1]=='.' &&  piece[1][1]=='.' && piece[2][1]=='.' && piece[3][1]=='.')
			{
				cleanTop++;
				if(piece[0][2]=='.' &&  piece[1][2]=='.' && piece[2][2]=='.' && piece[3][2]=='.')
				{
					cleanTop++;
				}
			}
		}
		
		if(piece[0][3]=='.' &&  piece[1][3]=='.' && piece[2][3]=='.' && piece[3][3]=='.')
		{
			cleanBottom++;
			if(piece[0][2]=='.' &&  piece[1][2]=='.' && piece[2][2]=='.' && piece[3][2]=='.')
			{
				cleanBottom++;
				if(piece[0][1]=='.' &&  piece[1][1]=='.' && piece[2][1]=='.' && piece[3][1]=='.')
				{
					cleanBottom++;
				}
			}
		}
		
		piece=new char[4-cleanLeft-cleanRight][4-cleanTop-cleanBottom];
		
		for(int i=0;i<4-cleanTop-cleanBottom;i++)
		{
			for(int j=0;j<4-cleanLeft-cleanRight;j++)
			{
				piece[j][i]=temp[j+cleanLeft][i+cleanTop];
			}
		}
		return piece;
		
		
	}
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));

		for(int i=0;i<5;i++)
		{
			char piece[][]=new char[4][4];
			
			for(int j=0;j<4;j++)
			{
				String line=br.readLine();
				for(int k=0;k<4;k++)
				{
					piece[k][j]=line.charAt(k);
				}
			}
			
			char map[][]=new char[10][10];

			for(int k=0;k<10;k++)
				for(int j=0;j<10;j++)
					map[k][j]='.';
			
			for(int j=0;j<6;j++)
			{
				String line=br.readLine();
				for(int k=0;k<10;k++)
				{
					map[k][j+4]=line.charAt(k);
				}
			}
			
			int max=Integer.MIN_VALUE;
			
			char pieceTemp[][]=new char[4][4];
			
			
			
			for(int v=0;v<4;v++)
			{
				pieceTemp=clean(piece);
				for(int l=0;l<=10-pieceTemp.length;l++)
				{
					boolean done=false;
					for(int m=0;m<=10-pieceTemp[0].length && !done;m++)
					{
						char mapTemp[][]=new char[10][10];
						for(int j=0;j<10;j++)
							for(int k=0;k<10;k++)
								mapTemp[j][k]=map[j][k];
						
						for(int j=0;j<pieceTemp[0].length && !done;j++)
						{
							for(int k=0;k<pieceTemp.length && !done;k++)
							{
								
								if(map[k+l][j+m]=='.')
									mapTemp[k+l][j+m]=pieceTemp[k][j];
								else if(pieceTemp[k][j]!='.')
									done=true;
							}
						}
						
						if(!done)
						{
							int ans=0;
							for(int j=0;j<10;j++)
							{
								boolean finished=true;
								for(int k=0;k<10 && finished;k++)
								{
									if(mapTemp[k][j]!='#')
									{
										finished=false;
									}
								}
								if(finished)
								{
									ans++;
								}
							}
							max=Math.max(max, ans);
						}
						
					}
				}
			
			rotate(piece);
			}
			pw.println(max);		
		}

		pw.close();
		System.exit(0);
	}
	
}

