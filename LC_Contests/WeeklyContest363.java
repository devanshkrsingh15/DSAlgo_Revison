import java.util.*;

public class WeeklyContest363 {

    // 2859. Sum of Values at Indices With K Set Bits
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (setBits(i) == k) {
                ans += nums.get(i);
            }
        }

        return ans;
    }

    public int setBits(int n) {
        int cnt = 0;
        while (n != 0) {
            n = (n & (n - 1));
            cnt++;
        }
        return cnt;
    }

    // 2860. Happy Students
    public int countWays(List<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        int ans = nums.get(0) != 0 ? 1 : 0;

        if (nums.get(n - 1) < n)
            ans++;

        for (int i = 0; i + 1 < nums.size(); i++) {
            if (nums.get(i) < i + 1 && nums.get(i + 1) > i + 1)
                ans++;
        }

        return ans;
    }

    
}
