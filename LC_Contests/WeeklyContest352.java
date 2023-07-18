import java.util.*;

public class WeeklyContest352 {

    // 2760. Longest Even Odd Subarray With Threshold
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int idx = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] <= threshold && nums[i] % 2 == 0) {
                int len = 1;
                int prev = nums[i] % 2;
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] <= threshold && prev != nums[j] % 2) {
                        len++;
                        prev = nums[j] % 2;
                    } else {
                        ans = Math.max(ans, len);
                        break;
                    }

                }
                ans = Math.max(ans, len);
            }
        }

        return ans;
    }

    // 2761. Prime Pairs With Target Sum
    public List<List<Integer>> findPrimePairs(int n) {
        HashSet<Integer> listOfPrime = getPrimes(n);
        List<List<Integer>> ans = new ArrayList<>();

        for (int x = 2; x <= n / 2; x++) {
            if (listOfPrime.contains(x)) {
                int y = n - x;
                if (listOfPrime.contains(y)) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(x);
                    tmp.add(y);
                    ans.add(tmp);
                }
            }
        }

        return ans;
    }

    public HashSet<Integer> getPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        HashSet<Integer> listOfPrime = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                listOfPrime.add(i);
            }
        }

        return listOfPrime;

    }

    // 2762. Continuous Subarrays
    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int n = nums.length;
        int st = 0;
        int en = 0;

        while (en < n) {
            int ele = nums[en];
            map.put(ele, map.getOrDefault(ele, 0) + 1);
            en++;

            while (map.lastKey() - map.firstKey() > 2) {
                int sele = nums[st];
                map.put(sele, map.getOrDefault(sele, 0) - 1);
                if (map.get(sele) == 0)
                    map.remove(sele);
                st++;
            }

            ans += (long) (en - st);
        }

        return ans;
    }

}