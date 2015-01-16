package SeniorProblem;

import java.util.*;
import java.io.*;

public class S3 {
	static double slope;
	static double xR, yR, xJ, yJ;

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));

		xR = sc.nextDouble();
		yR = sc.nextDouble();
		xJ = sc.nextDouble();
		yJ = sc.nextDouble();
		double xMin,xMax, yMin, yMax;
		if(xR>xJ)
		{
			xMin=xJ;
			xMax=xR;
		}
		else
		{
			xMin=xR;
			xMax=xJ;
		}
		if(yR>yJ)
		{
			yMin=yJ;
			yMax=yR;
		}
		else
		{
			yMin=yR;
			yMax=yJ;
		}
		
	
		slope = slope(xR, xJ, yR, yJ);
		int numBuilding = sc.nextInt();
		double x1, x2, y1, y2;
		int count = 0;
		for (int i = 0; i < numBuilding; i++) {
			
			int numEdge = sc.nextInt();
			double tempX = sc.nextDouble();
			double tempY = sc.nextDouble();
			x2 = tempX;
			y2 = tempY;
			for (int j = 0; j < numEdge; j++) {
				x1 = x2;
				y1 = y2;
				if (j == numEdge - 1) {
					x2 = tempX;
					y2 = tempY;
				} else {
					x2 = sc.nextDouble();
					y2 = sc.nextDouble();
				}
				double xInter=xInter(x1, x2, y1, y2);
				
				double max, min;
				if(x2>x1)
				{
					min=x1;
					max=x2;
				}
				else
				{
					min=x2;
					max=x1;
				}
				double mmax, mmin;
				if(y2>y1)
				{
					mmin=y1;
					mmax=y2;
				}
				else
				{
					mmin=y2;
					mmax=y1;
				}
				if(xInter==Double.MIN_VALUE )
				{
					count++;
					break;
				}
				double yInter=slope * (xInter - xJ) +yJ;
			//	System.out.println(xInter+" "+yInter);
				if(Math.max(min, xMin)<=xInter && xInter<=Math.min(max, xMax))
					if(Math.max(mmin, yMin)<=yInter && yInter<=Math.min(mmax, yMax))
					{
						count++;
						break;
					}
			}
		}
		System.out.println(count);
		// y -yJ= slope * (x - xJ)
		// y - yL=slopeL * (x- xL)
		// yL-yJ +slopeL * x-slopeL*xL = slope * (x - xJ)
		// yL-yJ -slopeL*xL+slope *xJ = (slope-slopeL)x
		// (yL-yJ -slopeL*xL+slope *xJ)/(slope-slopeL) =x
		// Math.min(xJ,xL,xR, xL2)~
		// it is true
		// ->cocunt++ go to next building
	}

	public static double slope(double x1, double x2, double y1, double y2) {
//		System.out.println(x1+" "+x2);
		if(x1==x2)
			return Double.MAX_VALUE;
		return (y1 - y2) / (x1 - x2);
	}

	public static double xInter(double x1, double x2, double y1, double y2) {
		
		double slopeL = slope(x1, x2, y1, y2);
//		if(slope==slopeL)
//			if(x1==xJ) return Double.MIN_VALUE;
//			else return Double.MAX_VALUE;
//		else if(slopeL==Double.MAX_VALUE)
//		{
//			
//		}
		if(slopeL==Double.MAX_VALUE)
			if((xJ<=x1 && x1<=xR) || (xR<=x1 && x1<=xJ))
				return Double.MIN_VALUE;
		if(slope==Double.MAX_VALUE)
			if((x1<=xJ && xJ<=x2) || (x2<=xJ && xJ<=x1))
				return Double.MIN_VALUE;
		return (y1 - yJ - slopeL * x1 + slope * xJ) / (slope - slopeL);
	}
	
	
}
