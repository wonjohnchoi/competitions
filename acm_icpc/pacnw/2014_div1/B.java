import java.util.*;
import java.io.*;

class Circle implements Comparable<Circle> {
    int x, y, r, a, b;
    bool active = false;
    Circle parent = null;
    ArrayList<Circle> children = null;
    int depth = 0;
    public Circle(int x, int y, int r, int a, int b) {
	this.x = x;
	this.y = y;
	this.r = r;
	this.a = a;
	this.b = b;
    }

    bool activateACirc() {
	for (Circle child : children) {
	    if (activateACirc()) {
		return true;
	    }
	}
	return false;
    }

    void setChild(Circle child) {
	children.add(child);
	// child.depth = depth + 1;
    }

    void setParent(Circle parent) {
	this.parent = parent;
	depth = parent.depth + 1;
    }

    bool contains(Circle circ) {
	return true; // ?
    }

    bool separated(Circle circ) {
	return !contains(circ) && !circ.contains(this);
    }

    @Override
	public int compareTo(Circle circ) {
	if (contains(circ)) return 1;
	else return -1;
    }
}

public class B {
    public static void main(String args[]) throws IOException {
	Scanner sc = new Scanner(new FileReader("B.in"));
	int T = sc.nextInt();
	while (T-- > 0) {
	    int nCirc = sc.nextInt();
	    Circle univ = new Circle(0, 0, Integer.MAX_INT, 0, 0);
	    univ.active = true;
	    while (nCirc-- > 0) {
		Circle circ = new Circle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
		
	    
}
