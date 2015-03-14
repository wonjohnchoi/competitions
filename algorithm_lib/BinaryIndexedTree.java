public class BinaryIndexedTree {
    int length;
    int logLength;
    int[] c;
    BinaryIndexedTree(int n) {
	logLength = Integer.SIZE - Integer.numberOfLeadingZeros(n);
	length = 1 << logLength;
	c = new int[length + 1];
    }
    void add(int x, int num) {
	if (!(x >= 1 && x <= length)) throw new AssertionError();
	for (; x <= length; x += Integer.lowestOneBit(x)) {
	    c[x] += num;
	}
    }
    void insert(int x) {
	add(x, 1);
    }
    void remove(int x) {
	add(x, -1);
    }
    int sum(int x) {
	if (!(x >= 0 && x <= length)) throw new AssertionError();
	int sum = 0;
	for (; x > 0; x -= Integer.lowestOneBit(x)) {
	    sum += c[x];
	}
	return sum;
    }
    int sum(int l, int r) {
	return sum(r) - sum(l - 1);
    }
    int rank(int x) {
	return sum(x - 1) + 1;
    }
    int select(int k) {
	int ans = 0;
	for (int i = logLength; i >= 0; i--) {
	    ans += (1 << i);
	    if (c[ans] < k) {
		k -= c[ans];
	    } else {
		ans -= (1 << i);
	    }
	}
	return ans + 1;
    }
    public static void main(String args[]) {
        BinaryIndexedTree bit = new BinaryIndexedTree(5);
        bit.add(1, 0);
        bit.add(2, 9);
        bit.add(3, 9);
        bit.add(4, 19);
        bit.add(5, 100);
        System.out.println(bit.logLength); // 3
        System.out.println(bit.length); // 8
        System.out.println(bit.sum(1)); // 0
        System.out.println(bit.sum(2)); // 9
        System.out.println(bit.sum(3)); // 18
        System.out.println(bit.sum(4)); // 37
        System.out.println(bit.sum(5)); // 137
        System.out.println(bit.sum(3, 4)); // 28
        System.out.println(bit.rank(4)); // 19
        System.out.println(bit.select(19)); // 4
        System.out.println(bit.select(18)); // 3
    }
}
