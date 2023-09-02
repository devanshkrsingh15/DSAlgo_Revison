import java.util.*;

public class WeeklyContest358 {

    // 2815. Max Pair Sum in an Array
    public int maxSum(int[] nums) {
        int max = -1;
        int n = nums.length;

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int maxDigit = findMax(nums[i]);
            map.putIfAbsent(maxDigit, new PriorityQueue<>((a, b) -> nums[b] - nums[a]));
            map.get(maxDigit).add(i);
        }

        for (int d : map.keySet()) {
            PriorityQueue<Integer> pq = map.get(d);
            if (pq.size() >= 2) {
                int sum = nums[pq.remove()] + nums[pq.remove()];
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    public int findMax(int ele) {
        int max = -1;
        while (ele != 0) {
            max = Math.max(max, ele % 10);
            ele /= 10;
        }
        return max;
    }

    // 2817. Minimum Absolute Difference Between Elements With Constraint
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        if (x == 0)
            return 0;
        int n = nums.size();
        int ans = (int) 1e9;
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> nums.get(a) - nums.get(b));
        for (int i = x; i < n; i++) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (i + x < n) {
                if (map.ceilingKey(i) != null) {
                    ans = Math.min(ans, Math.abs(nums.get(i) - nums.get(map.ceilingKey(i))));
                }
                if (map.floorKey(i) != null) {
                    ans = Math.min(ans, Math.abs(nums.get(i) - nums.get(map.floorKey(i))));
                }
                map.put(i + x, map.get(i + x) - 1);
                if (map.get(i + x) == 0)
                    map.remove(i + x);
            }
        }

        return ans;
    }

    // 2818. Apply Operations to Maximize Score
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        HashSet<Integer> primes = generatePrimes((int) 1e5);
        int[] primeScore = generatePrimeScore(nums, primes);
        int[] ngor = getNextGreaterScore(nums, primeScore, true); // true = right
        int[] ngol = getNextGreaterScore(nums, primeScore, false); // false = left

        PriorityQueue<int[]>pq = new PriorityQueue<>((a,b)->{
            return (int)Math.pow(nums.get(b[0]),b[1]) - (int)Math.pow(nums.get(a[0]),a[1]); 
        }); // idx,cnt of subarrays


        for(int i = 0 ; i<n ; i++){
            int cnt = ngor[i] -  ngol[i] - 1;
            pq.add(new int[]{i,cnt});
        }

        long mod = (long)1e9 + 7;

        long ans = 1;

        while(pq.size()!=0 && k>0){
            int[] rarr = pq.remove();
            int idx = rarr[0];
            int cnt =  rarr[1];
            int subarrays = Math.min(k,cnt);
            ans =( ans*mod * (long)Math.pow(nums.get(idx),subarrays)*mod)%mod;
            k-= subarrays;
        }

        return (int)ans;

    }

    private int[] getNextGreaterScore(List<Integer> nums, int[] primeScore, boolean fromRight) {
        int n = nums.size();
        int[] ans = new int[n];
        Arrays.fill(ans, fromRight ? n : -1);
        Stack<Integer> st = new Stack<>();
        for (int k = 0; k < n; k++) {
            int i = fromRight ? k : n - 1 - k;
            while (st.size() != 0 && ((fromRight && primeScore[i] > primeScore[st.peek()])
                    || (!fromRight && primeScore[i] >= primeScore[st.peek()]))) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }

        return ans;

    }

    private int[] generatePrimeScore(List<Integer> nums, HashSet<Integer> primes) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int ele = nums.get(i);
            int cnt = 0;
            if (primes.contains(ele))
                cnt++;
            for (int p = 2; p <= Math.sqrt(ele) + 1; p++) {
                if (primes.contains(p) && ele % p == 0)
                    cnt++;
            }
            ans[i] = cnt;
        }

        return ans;

    }

    public HashSet<Integer> generatePrimes(int n) {
        boolean[] isPrimes = new boolean[n + 10];
        // f -> prime, t-> not a prime

        for (int i = 2; i < isPrimes.length; i++) {
            if (!isPrimes[i]) {
                for (int j = 2 * i; j < isPrimes.length; j += i) {
                    isPrimes[j] = true;
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i < isPrimes.length; i++) {
            if (!isPrimes[i])
                set.add(i);
        }
        return set;

    }

    // 403. Frog Jump
    class LC403 {
        public boolean canCross(int[] stones) {
            int n = stones.length;
            int[][] dp = new int[n + 10][n + 10];
            for (int[] d : dp)
                Arrays.fill(d, -1);
            return canCross_(stones, 0, n + 8, dp) == 1;
        }

        public int canCross_(int[] stones, int currIdx, int prevIdx, int[][] dp) {
            int n = stones.length;
            if (currIdx == n - 1)
                return 1;

            if (dp[currIdx][prevIdx] != -1)
                return dp[currIdx][prevIdx];

            long k = prevIdx == n + 8 ? 0 : (long) stones[currIdx] - (long) stones[prevIdx];

            boolean res = false;

            int kth = find(stones, currIdx + 1, (long) stones[currIdx] + k);
            int kthp1 = find(stones, currIdx + 1, (long) stones[currIdx] + k + 1l);
            int kthm1 = find(stones, currIdx + 1, (long) stones[currIdx] + k - 1l);

            if (kth != -1)
                res = res || (canCross_(stones, kth, currIdx, dp) == 1);
            if (kthp1 != -1)
                res = res || (canCross_(stones, kthp1, currIdx, dp) == 1);
            if (kthm1 != -1)
                res = res || (canCross_(stones, kthm1, currIdx, dp) == 1);

            return dp[currIdx][prevIdx] = res ? 1 : 0;
        }

        public int find(int[] arr, int st, long tar) {
            int n = arr.length;
            if (tar > (long) arr[n - 1])
                return -1;
            int lo = st;
            int hi = n - 1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if ((long) arr[mid] == tar)
                    return mid;
                else if ((long) arr[mid] > tar)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }

            return -1;
        }
    }

}
