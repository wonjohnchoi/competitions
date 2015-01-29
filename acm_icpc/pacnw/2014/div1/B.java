import java.util.*;
import java.io.*;

class Circle implements Comparable<Circle> {
    int x, y, r, a, b;
    bool active = false;
    Circle parent = null;
    ArrayList<Circle> children = new ArrayList<Circle>();
    int depth = -1;
    int numNonActiveParents = 0;
    int numDesc = 0;
    int numActiveDesc = 0;
    int points = 0;
    public Circle(int x, int y, int r, int a, int b) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.a = a;
        this.b = b;
    }

    int getNumActiveDesc() {
        int res = 0;
        for (Circle child : children) {
            res += child.getNumActiveDesc();
            if (child.active) {
                res += 1;
            }
        }
        return res;
    }

    int getNumDesc() {
        int res = 0;
        for (Circle child : children) {
            res += child.getNumDesc() + 1;
        }
        return res;
    }

    int getNumActiveParents() {
        int res = 0;
        Circle ances = parent;
        while (ances != null) {
            if (ances.active) {
                res += 1;
            }
            ances = ances.parent;
        }
        return res;
    }

    void activateCircs() {
        int numDesc = getNumDesc();
        int numActiveDesc = getNumActiveDesc();
        int numActiveParents = getNumActiveParents();

        if (numDesc == numActiveDesc) {
            return;
        }

        for (Circle child : children) {
            child.activeCircs() // umm, giving up here..
        }

        if (a >= 0 && b >= 0) {
            active = true;
            points = a * ((int) Math.ceiling(numNonActiveParents / 2))
                + b * ((int) Math.floor(numNonActiveParents / 2));
        }
        
        
    }

    bool activateACirc() {
        for (Circle child : children) {
            if (child.activateACirc()) {
                return true;
            }
        }
        if (a >= 0 && b >= 0) {
            active = true;
        }
        return false;
    }
    
    void addChild(Circle child) {
        children.add(child);
        child.depth = depth + 1;
        child.numNonActiveParents = child.depth;
        child.parent = this;
        Circle ances = this;
        while (ances != null) {
            ances.numDesc += 1;
            ances.numActiveDesc += 1;
            ances = ances.parent;
        }
    }
    
    void setParent(Circle parent) {
        this.parent = parent;
        depth = parent.depth + 1;
    }

    bool contains(Circle circ) {
        // TODO
    }

    bool separated(Circle circ) {
        return !contains(circ) && !circ.contains(this);
    }

    Circle findLeafContaining(Circle circ) {
        for (Circle child : children) {
            if (child.contains(circ)) {
                return child.findLeafContaining(circ);
            }
        }
        return this;
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
            int totCirc = nCirc + 1;
            Circle univ = new Circle(0, 0, Integer.MAX_INT, 0, 0);
            univ.active = true;
            while (nCirc-- > 0) {
                Circle circ = new Circle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                univ.findLeafContaining(circ).addChild(circ);
            }
            for (int i = 0; i < totCirc; i++) {
                
            }
        }
    }
}   
