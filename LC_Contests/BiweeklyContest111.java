import java.util.*;

public class BiweeklyContest111 {

    // 2824. Count Pairs Whose Sum is Less than Target
    public int countPairs(List<Integer> nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target)
                    ans++;
            }
        }

        return ans;
    }

    // 2825. Make String a Subsequence Using Cyclic Increments
    public boolean canMakeSubsequence(String str1, String str2) {
        if (str2.length() > str1.length())
            return false;

        int n = str1.length();
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (j >= str2.length())
                return true;
            char chi = str1.charAt(i);
            char chj = str2.charAt(j);
            if (chi == chj) {
                j++;
                continue;
            } else if (chi != chj) {
                char nc = chi == 'z' ? 'a' : (char) ((int) (chi - 'a') + 1 + 'a');
                if (nc != chj) {

                } else {
                    j++;
                    continue;
                }
            }
        }

        return j >= str2.length();

    }

    // 2826. Sorting Three Groups
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[][] dp = new int[n + 1][4];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = (int) 1e9;

        for (int c = 1; c <= 3; c++) {
            ans = Math.min(ans, minimumOperations_(nums, 0, c, dp));
        }

        return ans;
    }

    public int minimumOperations_(List<Integer> nums, int idx, int lastGroup, int[][] dp) {
        if (idx == nums.size()) {
            return 0;
        }

        if (dp[idx][lastGroup] != -1)
            return dp[idx][lastGroup];

        int ans = (int) 1e9;

        int myGroup = nums.get(idx);
        if (myGroup == lastGroup) {
            ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp));
        } else {
            if (myGroup > lastGroup) {
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, myGroup, dp));
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp) + 1);
            } else if (myGroup < lastGroup) {
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp) + 1);
            }
        }

        return dp[idx][lastGroup] = ans;
    }

    // 2827. Number of Beautiful Integers in the Range

}
