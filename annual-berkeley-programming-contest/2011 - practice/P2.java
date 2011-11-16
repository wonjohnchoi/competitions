/**
Wonjohn Choi
Solved with Java
2011 Annual Berkeley Programming Contest [Practice]: Problem 2
*/
import java.util.*;
import java.io.*;
class P2{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int nCases = 0;
        while (in.hasNextLine()) {
            nCases += 1;
            String line = in.nextLine();
            String[] tokens = line.split(" ");
            Vector<Point> points = new Vector<Point>((int) (tokens.length / 2));
            for (int i = 0; i < tokens.length; i += 2) {
                points.add(new Point(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i + 1])));
            }
            double best = 0;
            
            for (int i = 0; i < points.size(); i += 1) {
                for (int j = i + 1; j < points.size(); j += 1) {
                    best = Math.max(best, points.get(i).dist(points.get(j)));
                }
            }
            
            System.out.printf("Set #%d: Diameter is %.2f\n", nCases, best);
        }
        System.exit(0);
    }
  
    static class Point {
        double x, y;
        Point(double xx, double yy) {
            x = xx;
            y = yy;
        }
        
        double dist(Point p) {
            return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
        }
    }
}

