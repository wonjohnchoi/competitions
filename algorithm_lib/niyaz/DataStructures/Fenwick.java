package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 22.01.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class Fenwick {
    int[] a;

    public Fenwick(int n) {
        a = new int[n];
    }

    public void add(int x, int y) {
        for (int i = x; i < a.length; i |= i + 1) {
            a[i] += y;
        }
    }

    public int getSum(int x) {
        if (x >= a.length) x = a.length - 1;
        int ret = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            ret += a[i];
        }
        return ret;
    }

    public int getSum(int l, int r) {
        return getSum(r - 1) - getSum(l - 1);
    }
}
