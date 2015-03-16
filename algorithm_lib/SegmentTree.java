import java.util.*;
import java.io.*;
public class SegmentTree {
    static int EMPTY = Integer.MAX_VALUE; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
    int[] ans;
    int[] vals;
    int N;
    SegmentTree(int[] vals) {
        this.vals = vals;
        ans = new int[vals.length * 4];
        build(0, 0, vals.length - 1);
    }
    int unite(int val1, int val2) {
        return Math.min(val1, val2);
    }
    void build(int v, int l, int r) { // O(N)
        if (l == r) {
            ans[v] = vals[l];
        } else {
            int m = (l + r) / 2;
            build(v * 2 + 1, l, m);
            build(v * 2 + 2, m + 1, r);
            ans[v] = unite(ans[v * 2 + 1], ans[v * 2 + 2]);
        }
    }
    int get(int needL, int needR) {
        return get(0, 0, vals.length - 1, needL, needR);
    }
    int get(int v, int l, int r, int needL, int needR) { // O(log N)
        if (needL > r || needR < l) {
            return EMPTY;
        }
        if (needL <= l && needR >= r) {
            return ans[v];
        }
        int mid = (l + r) / 2;
        return unite(get(v * 2 + 1, l, mid, needL, needR),
                     get(v * 2 + 2, mid + 1, r, needL, needR));
    }
    void put(int i, int tl, int tr, int pos, int val) {
	if (tl == tr) {
	    ans[i] = val;
	    return;
	}
	int m = (tl + tr) / 2;
	if (pos <= m)
	    put(2 * i + 1, tl, m, pos, val);
	else
	    put(2 * i + 2, m + 1, tr, pos, val);
	ans[i] = unite(ans[2 * i + 1], ans[2 * i + 2]);
    }
    void put(int pos, int val) {
	put(0, 0, vals.length - 1, pos, val);
    }
    public static void main(String args[]) {
        int[] vals = new int[] {10, -9, 4, 5, -3, 2, 8, 1, 0};
        SegmentTree st = new SegmentTree(vals); // MINIMUM RANGE QUERY
        System.out.println(st.get(0, 3)); // -9
        System.out.println(st.get(1, 3)); // -9
        System.out.println(st.get(2, 3)); // 4
        System.out.println(st.get(3, 3)); // 5
        System.out.println(st.get(0, vals.length - 1)); // -9
        System.out.println(st.get(4, 6)); // -3
	st.put(1, 5);
        System.out.println(st.get(0, 3)); // 4
        System.out.println(st.get(1, 3)); // 4
        System.out.println(st.get(2, 3)); // 4
        System.out.println(st.get(3, 3)); // 5
        System.out.println(st.get(0, vals.length - 1)); // -3
        System.out.println(st.get(4, 6)); // -3

    }
}
