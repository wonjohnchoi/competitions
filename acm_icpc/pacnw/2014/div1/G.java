import java.util.*;
import java.io.*;

// incorrect
public class G {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("G.in"));
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] nums = new int[N];
            int oneIdx = -1;
            for (int i = 0; i < N; i++) {
                nums[i] = sc.nextInt();
                if (nums[i] == 1) {
                    oneIdx = i;
                }
            }

            int[] nums2 = new int[N];
            System.arraycopy(nums, 0, nums2, 0, nums.length);
            boolean canFirstWin =
                numSteps(nums, oneIdx, true) % 2 == 1
                || numSteps(nums2, oneIdx, false) % 2 == 1;
            System.out.println(canFirstWin ? "Alice" : "Bob");
        }
    }

    public static int numSteps(int[] nums, int oneIdx, boolean reverse) {
        int tot = 0;
        while (true) {
            ArrayList<Integer> highNums = highNumsNearOne(nums, oneIdx);
            if (highNums.size() == 0) {
                tot += 1;
                break;
            }

            int numLoop = 0;
            boolean found = false;
            while (!found) {
                for (int i = 0; i < nums.length; i++) {
                    if (i == oneIdx) continue;
                    if (highNums.size() == 1 && highNums.contains(i) && numLoop == 0) continue;
                    if (canRemove(nums, i)) {
                        if (reverse
                            && i + 1 == oneIdx
                            && canRemove(nums, oneIdx + 1)) continue;
                        tot += 1;
                        nums[i] = -1;
                        found = true;
                        // System.out.print(i + " ");
                        break;
                    }
                }
                numLoop += 1;
            }
        }
        // System.out.println("\nTot:" + tot);
        return tot;
    }

    public static boolean canRemove(int[] nums, int idx) {
        if (!(0 <= idx && idx < nums.length)) return false;
        boolean left = (idx == 0) || (nums[idx - 1] < nums[idx]);
        boolean right = (idx == nums.length - 1) || (nums[idx] > nums[idx + 1]);
        return left && right;
    }
    
    public static ArrayList<Integer> highNumsNearOne(int[] nums, int oneIdx) {
        ArrayList<Integer> highNums = new ArrayList<Integer>();
        if (oneIdx >= 1 && nums[oneIdx - 1] != -1) {
            highNums.add(oneIdx - 1);
        }
        if (oneIdx <= nums.length - 2 && nums[oneIdx + 1] != -1) {
            highNums.add(oneIdx + 1);
        }
        return highNums;
    }
}
