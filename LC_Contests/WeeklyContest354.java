import java.util.*;

public class WeeklyContest354 {

    // 2778. Sum of Squares of Special Elements
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                ans += Math.pow(nums[i], 2);
            }
        }

        return ans;
    }

    // 2779. Maximum Beauty of an Array After Applying Operation
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        // end - start <= 2*k
        // a[start] + k >= a[end] - k => a[end] - a[start] <= 2*k
        int n = nums.length;
        int st = 0;
        int en = 0;
        boolean flag = false;
        int max = 0;

        while (en < n) {
            if (nums[en] - nums[st] > 2 * k)
                flag = true;

            while (flag) {
                st++;
                if (nums[en] - nums[st] <= 2 * k)
                    flag = false;
            }

            en++;
            max = Math.max(max, en - st);
        }

        return max;

    }

    // 2780. Minimum Index of a Valid Split
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        int[] lmax = getMaxArray(nums, 0); // 0,from left
        int[] rmax = getMaxArray(nums, 1); // 1, from right

        for (int i = 0; i < n - 1; i++) {
            if (lmax[i] != -1 && rmax[i + 1] != -1 && lmax[i] == rmax[i])
                return i;
        }

        return -1;

    }

    private int[] getMaxArray(List<Integer> nums, int fromLeft) {
        int n = nums.size();
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        HashMap<Integer, Integer> freqCount = new HashMap<>();
        int max = -1;
        int maxFreq = 0;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int idx = (fromLeft == 0) ? i : n - i - 1;
            int ele = nums.get(idx);
            freqMap.put(ele, freqMap.getOrDefault(ele, 0) + 1);
            int myFreq = freqMap.get(ele);
            freqCount.put(myFreq, freqCount.getOrDefault(myFreq, 0) + 1);

            if (maxFreq < myFreq) {
                maxFreq = myFreq;
                max = ele;
            }

            arr[idx] = freqCount.get(maxFreq) == 1 && maxFreq * 2 > (i + 1) ? max : -1;
        }

        return arr;
    }

    // 2781. Length of the Longest Valid Substring
    public int longestValidSubstring(String word, List<String> forbidden) {
        HashSet<String> set = new HashSet<>();
        int max = 0;
        for (String s : forbidden) {
            max = Math.max(max, s.length());
            set.add(s);
        }
        int[] startPtsLimit = new int[word.length() + 1];
        Arrays.fill(startPtsLimit, -1);

        for (int i = 0; i < word.length(); i++) {
            for (int j = 1; j <= max; j++) {
                int st = i;
                int en = st + j;
                if (en <= word.length()) {
                    String tmp = word.substring(st, en);
                    if (set.contains(tmp)) {
                        startPtsLimit[en - 1] = st;
                    }

                }
            }
        }

        int st = 0;
        int en = 0;
        int ans = 0;

        int n = word.length();

        while (en < n) {
            while (startPtsLimit[en] >= st) {
                st++;
            }
            if (st <= en)
                ans = Math.max(ans, en - st + 1);
            en++;
        }

        return ans;
    }

}
