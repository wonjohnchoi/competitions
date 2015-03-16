public class ModExp {
    public static int modExp(long b, long e, long mod) { // O(log e)
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * b) % mod;
            b = (b * b) % mod;
            e >>= 1;
        }
        return (int) r;
    }
    public static void main(String args[]) {
        System.out.println(modExp(3, 5, 10)); // 3
        System.out.println(modExp(3, 5000, 10)); // 1
    }
}
