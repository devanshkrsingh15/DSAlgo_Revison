import java.util.*;

public class WeeklyContest344 {
    // 2670. Find the Distinct Difference Array
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        int[] prefixDis = getDistintCountArray(nums, true);
        int[] suffixDis = getDistintCountArray(nums, false);

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = prefixDis[i] - (i + 1 < n ? suffixDis[i + 1] : 0);
        }

        return ans;
    }

    public int[] getDistintCountArray(int[] nums, boolean leftToRight) {
        int n = nums.length;
        int[] freq = new int[100];
        int[] ans = new int[n];
        freq[nums[leftToRight ? 0 : n - 1]] = 1;
        ans[leftToRight ? 0 : n - 1] = 1;

        for (int i = 1; i < n; i++) {
            int curr = leftToRight ? i : n - 1 - i;
            int nxt = leftToRight ? curr - 1 : curr + 1;
            freq[nums[curr]]++;
            ans[curr] += ans[nxt] + (freq[nums[curr]] == 1 ? 1 : 0);
        }

        return ans;

    }

    // 2671. Frequency Tracker
    class FrequencyTracker {
        int[] nums;
        int[] freq;

        public FrequencyTracker() {
            nums = new int[(int) 1e5 + 10];
            freq = new int[(int) 1e5 + 10];
        }

        public void add(int number) {
            int oldFreq = nums[number];
            int newFreq = oldFreq + 1;
            nums[number]++;
            if (oldFreq > 0)
                freq[oldFreq]--;
            freq[newFreq]++;
        }

        public void deleteOne(int number) {
            int oldFreq = nums[number];
            if (oldFreq == 0)
                return;
            int newFreq = oldFreq - 1;
            nums[number]--;
            freq[oldFreq]--;
            if (newFreq > 0)
                freq[newFreq]++;

        }

        public boolean hasFrequency(int frequency) {
            return freq[frequency] > 0;
        }
    }

    // 2672. Number of Adjacent Elements With the Same Color
    public int[] colorTheArray(int n, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] arr = new int[n];

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        int prs = 0;
        int ptr = 0;

        for (int[] q : queries) {
            int idx = q[0];
            int ncol = q[1];
            int ocol = arr[idx];
            if (ncol != ocol) {
                map.putIfAbsent(ncol, new TreeSet<>());
                if (map.get(ncol).ceiling(idx) != null)
                    prs += map.get(ncol).ceiling(idx) == idx + 1 ? 1 : 0;
                if (map.get(ncol).floor(idx) != null)
                    prs += map.get(ncol).floor(idx) == idx - 1 ? 1 : 0;
                map.get(ncol).add(idx);

                if (ocol != 0) {
                    map.get(ocol).remove(idx);
                    if (map.get(ocol).ceiling(idx) != null)
                        prs -= map.get(ocol).ceiling(idx) == idx + 1 ? 1 : 0;
                    if (map.get(ocol).floor(idx) != null)
                        prs -= map.get(ocol).floor(idx) == idx - 1 ? 1 : 0;
                }

            }

            arr[idx] = ncol;

            ans[ptr++] = prs;

        }

        return ans;
    }

    //2673. Make Costs of Paths Equal in a Binary Tree
    class L2673 {
        int maxPathSum = 0;
        int ops = 0;
        public int minIncrements(int n, int[] cost) {
            maxCost(1,cost,0);
            calCulateInc(1,cost,0);
            return ops; 
        }
    
        //will return ops that can be done at each level to get the max path sum
        public int calCulateInc(int idx,int[]cost,int sof){
            sof += cost[idx-1];
             if( 2*idx>=cost.length && (2*idx+1)>=cost.length ){
                ops +=  maxPathSum - sof;
                return maxPathSum - sof;
            }
        
            int left = calCulateInc(2*idx,cost,sof);
            int right = calCulateInc(2*idx+1,cost,sof);
    
            int commonOps = Math.min(left,right);
            ops -= commonOps;
            return commonOps;
        }
    
    
        public void maxCost(int idx,int[]cost,int sof){
            if( 2*idx>=cost.length && (2*idx+1)>=cost.length ){
                maxPathSum = Math.max(maxPathSum,sof +cost[idx-1]);
                return;
            }
    
            maxCost(2*idx,cost,sof + cost[idx-1]);
            maxCost(2*idx + 1 ,cost,sof + cost[idx-1]);
        }
    }
}
