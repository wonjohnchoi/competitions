package extra;

public class fillArray {
	public static int[][] fillArray(int[][] a, int key)
	{
		int lengthI=a.length;
		int lengthJ=a[0].length;
		int i=0;
		int j=0;
		
		while(i!=lengthI)
		{
			a[i][j]=key;
			j++;
			if(j==lengthJ)
			{
				j=0;
				i++;
			}
		}
		return a;
	}
}
