/**
Wonjohn Choi
Solved with Java
2010 Annual Berkeley Programming Contest: Problem 8
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class P8{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        Vector<Point> points = new Vector<Point>();
        
        int x, y;
        while(in.hasNextInt()){
            x = in.nextInt();
            y = in.nextInt();
            points.add(new Point(x, y));
        }
        
        //special cases
        if(points.size()==0 || points.size()==1) {
            System.out.println("Not enough input!");
            System.exit(0);
        }else if(points.size()==2){
            System.out.printf("%.1f\n", points.get(0).distTo(points.get(1)));
            System.exit(0);
        }
        
        Vector<Point> boundary = getBoundary(points);

        System.out.printf("%.1f\n", getDiameter(boundary));
        System.exit(0);
        
    }
    
    //convex hull
    public static Vector<Point> getBoundary(Vector<Point> points){
        Point corner = leftmost(points);
        //System.out.println(corner);
        
        Vector<Point> boundary = new Vector<Point>();
        //boundary.add(corner);
        
        Point pre = corner;
        Point next;
        
        do{
            next = null;
            for(Point point: points){
                if(!boundary.contains(point)){
                    if(next==null){
                        next = point;
                    }else{
                        //determine which one is more left clockwise (point or next?)
                        Point v1 = pre.vector(point);
                        Point v2 = pre.vector(next);
                        if(v1.cross(v2)<0){
                            next = point;
                        }
                    }
                }
            }
            boundary.add(next);
            //System.out.println(next);
            pre = next;
        }while(pre!=corner);
        return  boundary;
    }

    
    public static Point leftmost(Vector<Point> points){
        //get a point at corner
        Point corner = null;
        for(Point point: points){
            if(corner==null){
                corner = point;
            }else if(point.x < corner.x){
                corner = point;
            }else if(point.x == corner.x && point.y < corner.y){
                corner = point;
            }
        }
        return corner;
    }
    
    
    public static double getDiameter(Vector<Point> boundary){
        double dist = -1;
        for(Point p1: boundary){
            for(Point p2: boundary){
                dist = Math.max(dist, p1.distTo(p2));
            }
        }
        return dist;
    }
}

class Point{
    int x, y;
    
    public Point(int xx, int yy){
        x=xx;
        y=yy;
    }
    
    public double distTo(Point o){
        return Math.sqrt(Math.pow(x-o.x, 2)+Math.pow(y-o.y, 2));
    }
    
    public int cross(Point o){
        return x*o.y - y*o.x;
    }
    
    public Point vector(Point o){
        return new Point(o.x-x, o.y-y);
    }
    
    public String toString(){
        return String.format("[%d, %d]",x,y);
    }
}