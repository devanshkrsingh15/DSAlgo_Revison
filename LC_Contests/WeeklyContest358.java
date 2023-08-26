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


    //2818. Apply Operations to Maximize Score


}
