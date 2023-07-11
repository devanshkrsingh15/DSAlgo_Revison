import java.util.*;

public class WeeklyContest349 {

    // 2733. Neither Minimum nor Maximum
    public int findNonMinOrMax(int[] nums) {
        int maxIdx = -1;
        int max = -1;
        int minIdx = -1;
        int min = (int) 1e9;

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (max < nums[i]) {
                maxIdx = i;
                max = nums[i];
            }

            if (min > nums[i]) {
                minIdx = i;
                min = nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != minIdx && i != maxIdx)
                return nums[i];
        }

        return -1;
    }

    // 2734. Lexicographically Smallest String After Substring Operation
    public String smallestString(String s) {
        int n = s.length();
        ArrayList<Character> list = new ArrayList<>();

        int idx = 0;
        while (idx < n && s.charAt(idx) == 'a') {
            list.add((char) (s.charAt(idx)));
            idx++;
        }

        if (idx == n) {
            list.set(idx - 1, 'z');
        }

        while (idx < n && s.charAt(idx) != 'a') {
            list.add((char) (s.charAt(idx) - 1));
            idx++;
        }

        while (idx < n) {
            list.add((char) (s.charAt(idx)));
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : list)
            sb.append(ch);
        return sb.toString();
    }

    // 2735. Collecting Chocolates
    public long minCost(int[] nums, int x) {
        // each element to be considered at a specific rotation
        int n = nums.length;
        long[] minCosts = new long[n];
        long ans = 0l;

        for (int i = 0; i < n; i++) {
            minCosts[i] = (long) nums[i];
            ans += (long) nums[i];
        }

        for (int rot = 1; rot < n; rot++) {
            long cost = 0l;
            for (int i = 0; i < n; i++) {
                minCosts[i] = Math.min(minCosts[i], nums[(i + rot) % n]);
                cost += minCosts[i];
            }
            cost += 1l * x * rot;
            ans = Math.min(ans, cost);
        }

        return ans;
    }
}