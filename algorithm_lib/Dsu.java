class Dsu {
    int[] p;
    int[] val;

    Dsu(int n) {
	p = new int[n];
	val = new int[n];
        init();
    }

    void init() {
	for (int i = 0; i < p.length; i++) {
	    p[i] = i;
	    val[i] = 0;
	}
    }

    int get(int x) {
	return p[x] == x ? x : (p[x] = get(p[x]));
    }

    void unite(int x, int y) {
	x = get(x);
	y = get(y);
	if (x == y) {
	    return;
	}
	p[x] = y;
	val[y] += val[x];
    }
    public static void main(String args[]) {
        Dsu dsu = new Dsu(10);
        System.out.println(dsu.get(0)); // 0
        System.out.println(dsu.get(3)); // 3
        System.out.println(dsu.get(6)); // 6
        dsu.unite(0, 3);
        System.out.println(dsu.get(0)); // 3
        System.out.println(dsu.get(3)); // 3
        dsu.unite(6, 3);
        System.out.println(dsu.get(0)); // 3
        System.out.println(dsu.get(3)); // 3
        System.out.println(dsu.get(6)); // 3
        dsu.unite(3, 8);
        System.out.println(dsu.get(3)); // 8
        System.out.println(dsu.get(8)); // 8
        System.out.println(dsu.get(0)); // 8
        System.out.println(dsu.get(6)); // 8
    }
}
