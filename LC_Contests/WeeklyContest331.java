import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class WeeklyContest331 {

    // 2558. Take Gifts From the Richest Pile
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int ele : gifts) {
            pq.add(ele);
        }

        long ans = 0;

        while (k-- > 0 && pq.size() != 0) {
            int ridx = pq.remove();
            int nridx = (int) Math.floor(Math.sqrt(ridx));

            if (nridx == 1) {
                ans++;
            } else {
                pq.add(nridx);
            }
        }

        while (pq.size() != 0) {
            ans += pq.remove();
        }

        return ans;
    }

    // 2559. Count Vowel Strings in Ranges
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] psum = new int[n];
        for (int i = 0; i < n; i++) {
            psum[i] += (isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1))) ? 1 : 0;
            if (i > 0)
                psum[i] += psum[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            ans[i] = psum[r] - (l == 0 ? 0 : psum[l - 1]);
        }

        return ans;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // 2560. House Robber IV

    public int minCapability(int[] nums, int k) {
        int lo = nums[0];
        int hi = nums[0];
        for (int ele : nums) {
            lo = Math.min(lo, ele);
            hi = Math.max(hi, ele);
        }

        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid, k)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;

    }

    public boolean check(int[] arr, int cele, int k) {
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, -1);
        return k <= check_(arr, 0, cele, dp);

    }

    public int check_(int[] arr, int idx, int cele, int[] dp) {
        if (idx >= arr.length) {
            return 0;
        }

        if (dp[idx] != -1)
            return dp[idx];

        int ans = 0;

        if (arr[idx] <= cele) {
            ans = Math.max(ans, check_(arr, idx + 2, cele, dp) + 1);
        }

        ans = Math.max(ans, check_(arr, idx + 1, cele, dp));

        return dp[idx] = ans;

    }

    // 2561. Rearranging Fruits

    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        TreeSet<Integer> uniqueVals = new TreeSet<>();
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < n; i++) {
            uniqueVals.add(basket1[i]);
            uniqueVals.add(basket2[i]);
            map1.put(basket1[i], map1.getOrDefault(basket1[i], 0) + 1);
            map2.put(basket2[i], map2.getOrDefault(basket2[i], 0) + 1);
        }

        ArrayList<Integer> listOfNumberToSwap = new ArrayList<>();

        for (int ele : uniqueVals) {
            if ((map1.getOrDefault(ele, 0) + map2.getOrDefault(ele, 0)) % 2 == 1)
                return -1l; //we need even to make each array same
            int numberTaken = Math.abs(map1.getOrDefault(ele, 0) - map2.getOrDefault(ele, 0)); 
            //diff needs to be done 
            for (int j = 0; j < numberTaken / 2; j++) {
                listOfNumberToSwap.add(ele);
            }
        }

        long ans = 0l;
        long min = (long) uniqueVals.first();

        for (int i = 0; i < listOfNumberToSwap.size() / 2; i++) {
            // we only need to swap half, remaining will be automatically done
            // 2 options directly swap min with max or use min as intermediate and do two swaps
            int ele = listOfNumberToSwap.get(i);
            ans += Math.min(ele, 2 * min);
        }
        return ans;

    }

}