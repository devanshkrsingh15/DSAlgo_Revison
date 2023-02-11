import java.util.*;

public class WeeklyContest330 {
    // 2549. Count Distinct Numbers on Board
    HashSet<Integer> hs = new HashSet<>();

    public int distinctIntegers_(int n) {
        hs.add(n);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 1) {
                ans += distinctIntegers(i) + 1;
            }
        }

        return ans;
    }

    public int distinctIntegers(int n) {
        distinctIntegers_(n);
        return hs.size();

    }

    public int monkeyMove(int n) {
        /*
         * total moves => 2^n (each monkey has 2 options)
         * if all moves clockwise && anticlockwise then no collisions occur
         * 
         * if n=> odd 2^n - 2 ; when collisions occur
         * if n => even 2^n - 2 -2 ; extra - 2 when adjacent pair moves clock and
         * anticlockwise ; when collisions occurP
         */
        long ans = 1;
        long mod = (long) 1e9 + 7;
        long x = 2l;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = (ans % mod * x % mod) % mod;
                n = n - 1;
            } else {
                x = (x % mod * x % mod) % mod;
                n = n / 2;
            }
        }

        return (int) (((ans % mod - 2l) % mod + mod) % mod);
    }

    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (n == k)
            return 0l;

        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            list.add((long) weights[i] + (long) weights[i - 1]);
        }

        long min = 0;
        Collections.sort(list);
        for (int i = 0; i < k - 1; i++) {
            min += list.get(i);
        }

        long max = 0;
        Collections.reverse(list);
        for (long ele : list)
            System.out.print(ele + " ");
        for (int i = 0; i < k - 1; i++) {
            max += list.get(i);
        }
        return max - min;

    }

    // 2552. Count Increasing Quadruplets
    public long countQuadruplets(int[] nums) {
        /*
         * i<j<k<l
         * nums[i] < nums[k] < nums[j] < nums[l]
         * nums[i] < nums[l]
         * only irregularity is in nums[j] > nums[k]
         * 
         * so we fix j and k , and count number of ele on left less then nums[j]
         * and count number of ele on right greater than nums[k]
         */

        int n = nums.length;
        int[][] left = new int[n + 1][n + 1];
        int[][] right = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                int ele = nums[i];
                if (ele < j)
                    left[i][j]++;
                if (i != 0)
                    left[i][j] += left[i - 1][j];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                int ele = nums[i];
                if (ele > j)
                    right[i][j]++;
                if (i != n - 1)
                    right[i][j] += right[i + 1][j];
            }
        }

        long ans = 0;
        for (int j = 1; j < n - 2; j++) {
            for (int k = n - 2; k > j; k--) {
                if (nums[j] > nums[k]) {
                    int ej = nums[j];
                    int ek = nums[k];

                    long a = (long) left[j - 1][ek];
                    long b = (long) right[k + 1][ej];

                    ans += a * b;
                }
            }
        }

        return ans;
    }

}
