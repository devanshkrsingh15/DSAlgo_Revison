import java.util.*;

public class WeeklyContest327 {
    // 2529. Maximum Count of Positive Integer and Negative Integer
    public int maximumCount(int[] nums) {
        int n = nums.length;

        int lo = 0;
        int hi = n - 1;
        int FzeroPos = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == 0) {
                FzeroPos = mid;
                hi = mid - 1;
            } else if (nums[mid] > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (FzeroPos == -1) {
            return Math.max(hi + 1, n - lo);
        }

        lo = 0;
        hi = n - 1;
        int LzeroPos = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == 0) {
                LzeroPos = mid;
                lo = mid + 1;
            } else if (nums[mid] > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return Math.max(FzeroPos, n - 1 - LzeroPos);

    }

    // 2530. Maximal Score After Applying K Operations
    public long maxKelements(int[] nums, int k) {
        double ans = 0;

        PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> {
            return (int) (b - a);
        });

        for (int ele : nums)
            pq.add((double) ele);

        while (k-- > 0) {
            double rp = pq.remove();
            ans += rp;

            pq.add((double) Math.ceil((double) (rp) / 3));
        }

        return (long) ans;
    }

    // 2531. Make Number of Distinct Characters Equal
    public boolean isItPossible(String word1, String word2) {

        if (word1.equals(word2))
            return true;
        int[] arr1 = findFreq(word1);
        int[] arr2 = findFreq(word2);

        if (Math.abs(getCount(arr1) - getCount(arr2)) > 2)
            return false;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr1[i] == 0 || arr2[j] == 0)
                    continue;

                arr1[j]++;
                arr1[i]--;

                arr2[i]++;
                arr2[j]--;

                if (getCount(arr1) == getCount(arr2))
                    return true;

                arr1[j]--;
                arr1[i]++;

                arr2[i]--;
                arr2[j]++;

            }
        }

        return false;

    }

    private int getCount(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] >= 1)
                cnt++;
        }

        return cnt;
    }

    private int[] findFreq(String word) {
        int[] arr = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            arr[ch - 'a']++;
        }

        return arr;
    }

}
