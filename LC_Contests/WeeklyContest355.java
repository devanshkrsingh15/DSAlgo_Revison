import java.util.*;

public class WeeklyContest355 {

    // 2788. Split Strings by Separator
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String s : words) {
            List<String> tmp = get(s, separator);
            for (String str : tmp)
                ans.add(str);
        }

        return ans;
    }

    public List<String> get(String s, char ch) {
        List<String> ans = new ArrayList<>();
        int st = 0;
        int idx = 0;
        int n = s.length();

        while (idx < n) {
            if (s.charAt(idx) == ch) {
                if (st == idx) {
                    st = idx + 1;
                } else {
                    String str = s.substring(st, idx);
                    if (str.length() > 0) {
                        ans.add(str);
                    }
                    st = idx + 1;
                }
            }
            idx++;
        }

        if (st != idx)
            ;
        String str = s.substring(st, idx);
        if (str.length() > 0)
            ans.add(str);

        return ans;

    }

    // 2789. Largest Element in an Array after Merge Operations
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long sum = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if ((long) nums[i] <= sum) {
                sum += (long) nums[i];
            } else {
                sum = (long) nums[i];
            }
        }

        return sum;
    }

    // 808. Soup Servings
    public double soupServings(int n) {
        /*
         * ANS -> P(A==0 && B>0) + 1/2*P(A==0 && B==0)
         */

        if (n > 5000)
            return 1.0; // based on empirical formula
        // as n inc , and we never do 0ml serving of A, A will get 0 much more faster

        double[][] dp = new double[n + 1][n + 1];
        for (double[] d : dp)
            Arrays.fill(d, -1.0);

        return soupServings_(n, n, dp);

    }

    public double soupServings_(int amtA, int amtB, double[][] dp) {
        if (amtA <= 0 && amtB <= 0)
            return 0.5; // final ans needs half only
        else if (amtA <= 0)
            return 1.0; // final ans needs full
        else if (amtB <= 0)
            return 0.0; // not a part of final ans

        if (dp[amtA][amtB] != -1.0)
            return dp[amtA][amtB];

        double prob = 0.0;
        for (int servA = 1000, servB = 0; servA >= 25; servA -= 25, servB += 25) {
            prob += soupServings_(amtA - servA, amtB - servB, dp);
        }

        return dp[amtA][amtB] = prob * 0.25;
    }
}
