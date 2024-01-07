import java.util.*;

public class WeeklyContest367 {

    // 2903. Find Indices With Index and Value Difference I
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int ith = -1;
        int jth = -1;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((int) Math.abs(i - j) >= indexDifference && (int) Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    ith = i;
                    jth = j;
                    break;
                }
            }
        }

        return new int[] { ith, jth };
    }

    // 2904. Shortest and Lexicographically Smallest Beautiful String
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int n = s.length();
        int minLen = n;

        int st = 0;
        int en = 0;

        int cnt = 0;

        while (en < n) {
            char ch = s.charAt(en++);
            cnt += ch - '0';

            while (cnt >= k) {
                if (cnt == k) {
                    String tmp = s.substring(st, en);
                    if (ans.equals("")) {
                        ans = tmp;
                        minLen = tmp.length();
                    } else if (tmp.length() < minLen || (tmp.length() == minLen && ans.compareTo(tmp) > 0)) {
                        ans = tmp;
                        minLen = tmp.length();
                    }
                }

                char sch = s.charAt(st++);
                cnt -= sch - '0';

            }
        }
        while (cnt > k) {
            if (cnt == k) {
                String tmp = s.substring(st, en);
                if (ans.equals("")) {
                    ans = tmp;
                    minLen = tmp.length();
                } else if (tmp.length() < minLen || (tmp.length() == minLen && ans.compareTo(tmp) > 0)) {
                    ans = tmp;
                    minLen = tmp.length();
                }
            }

            char sch = s.charAt(st++);
            cnt -= sch - '0';

        }

        return ans;
    }

    // 2905. Find Indices With Index and Value Difference II

    

}