package capcs.choi.yr20082009.round5;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Round5Problem4 {

	/**
	 * @param args
	 */


	public static int counter(char map[][], int comx[],int comy[],int countCom)
	{
		int countLine=0;
		int pointerx1=0, pointerx2=0, pointery1=0, pointery2=0;
		int length1=100;
		int length2=100;
		int length12=0;
		int helpChoosingSecondBest=0;
		for(int v=1;v<countCom;v++)
		{
			if((Math.abs((comx[countCom]-comx[v])+Math.abs(comy[countCom]-comy[v]))<length1))
			{
				length1=(Math.abs(comx[countCom]-comx[v])+Math.abs(comy[countCom]-comy[v]));	
				pointerx1=comx[v];
				pointery1=comy[v];
				helpChoosingSecondBest=v;
			}
		}
		for(int v=1;v<countCom ;v++)
		{
			if((Math.abs(comx[countCom]-comx[v])+Math.abs(comy[countCom]-comy[v]))<length2 && v!=helpChoosingSecondBest 
					/*&&(comx[v]!=comx[helpChoosingSecondBest]||comx[v]!=comx[countCom]||comx[helpChoosingSecondBest]!=comx[countCom])
					&&(comy[v]!=comy[helpChoosingSecondBest]||comy[v]!=comy[countCom]||comy[helpChoosingSecondBest]!=comy[countCom])*/)
			{
				length2=(Math.abs(comx[countCom]-comx[v])+Math.abs(comy[countCom]-comy[v]));	
				pointerx2=comx[v];
				pointery2=comy[v];
			}
		}
		length12=Math.abs(pointerx1-pointerx2)+Math.abs(pointery1-pointery2);
		if(countCom==2)
		{
			countLine=2*(Math.abs(comx[1]-comx[2])+Math.abs(comy[1]-comy[2]));
		}
		if(countCom==3 && (comx[1]!=comx[2]||comx[1]!=comx[3]||comx[2]!=comx[3]) && (comy[1]!=comy[2]||comy[1]!=comy[3]||comy[2]!=comy[3]))
		{
			countLine=(Math.abs(comx[1]-comx[2])+Math.abs(comy[1]-comy[2])+Math.abs(comx[1]-comx[3])+Math.abs(comy[1]-comy[3])+Math.abs(comx[3]-comx[2])+Math.abs(comy[3]-comy[2]));
		}
		if(countCom==3)
		{
			if((comx[1]==comx[2]&&comx[2]==comx[3]))
				countLine=(Math.abs(Math.max(Math.max(comx[1],comx[2]),comx[3])))-(Math.abs(Math.min(Math.min(comx[1],comx[2]),comx[3])));
			if(comy[1]==comy[2]&&comy[2]==comy[3])
				countLine=(Math.abs(Math.max(Math.max(comy[1],comy[2]),comy[3])))-(Math.abs(Math.min(Math.min(comy[1],comy[2]),comy[3])));
		}
		else
		{
			countLine=counter(map, comx, comy, countCom-1)+length1+length2-length12;
		}
		return countLine;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Scanner choi=new Scanner(new FileReader("DATA4.txt"));
		BufferedReader in=new BufferedReader(new FileReader("DATA4.txt"));
		PrintWriter out= new PrintWriter("OUT4.txt");
		String s1,s2,s3,s4,s5;
		int i1,i2,i3,i4,i5;
		Vector<Object> vector=new Vector();
		
		for(int i=0;i<5;i++)
		{
			int countCom=0;
			char map [][]=new char[11][11];
			for(int height=1;height<=10;height++)
			{
				String line=in.readLine();
			
				for(int wid=1;wid<=10;wid++)
				{
					map[height][wid]=line.charAt(wid-1);
					if(map[height][wid]=='#')
						countCom++;
				}
			}
			int[] comx=new int [countCom+100];
			int[] comy=new int [countCom+100];
			int l=1;
			for(int height=1;height<=10;height++)
				for(int wid=1;wid<=10;wid++)
				{
					if(map[height][wid]=='#')
					{
						comx[l]=wid;
						comy[l]=height;
						l++;
					}
				}
			out.println(counter(map, comx,comy,countCom));
			
			
		}
		
		out.close();
		choi.close();
		in.close();
	}

}
