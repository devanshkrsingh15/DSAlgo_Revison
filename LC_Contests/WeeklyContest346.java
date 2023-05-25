import java.util.*;

public class WeeklyContest346 {

    // 2696. Minimum String Length After Removing Substrings
    public int minLength(String s) {
        Stack<Integer> st = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {

            if (st.size() != 0 && (check(s.charAt(st.peek()), s.charAt(i)))) {
                st.pop();
            } else {
                st.push(i);
            }
        }

        return st.size();
    }

    private boolean check(char a, char b) {
        return (a == 'A' && b == 'B') || (a == 'C' && b == 'D');
    }

    // 2697. Lexicographically Smallest Palindrome
    public String makeSmallestPalindrome(String s) {
        if (isPal(s))
            return s;
        int n = s.length();
        char arr[] = new char[n];
        Arrays.fill(arr, '*');
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                arr[i] = s.charAt(i);
                arr[n - i - 1] = s.charAt(i);
            } else {
                char min = (char) Math.min(s.charAt(i), s.charAt(n - i - 1));
                arr[i] = min;
                arr[n - i - 1] = min;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (arr[i] == '*')
                sb.append(s.charAt(i));
            else
                sb.append(arr[i]);
        }

        return sb.toString();
    }

    public boolean isPal(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;

        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    // 2698. Find the Punishment Number of an Integer
    public int punishmentNumber(int n) {
        boolean isPossiblePartition[] = getPartitionArray(n);
        long ans = 0l;
        for (int i = 1; i <= n; i++) {
            if (isPossiblePartition[i])
                ans += (long) i * i;
        }

        return (int) ans;
    }

    private boolean[] getPartitionArray(int n) {
        boolean[] arr = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            Boolean[][] dp = new Boolean[10][i + 1];
            arr[i] = check(i * i + "", 0, i, dp);
        }

        return arr;
    }

    public boolean check(String tmp, int idx, int tar, Boolean[][] dp) {
        if (idx == tmp.length()) {
            return tar == 0 ? true : false;
        }

        if (dp[idx][tar] != null)
            return dp[idx][tar];

        boolean res = false;
        for (int i = idx; i < tmp.length(); i++) {
            int num = Integer.parseInt(tmp.substring(idx, i + 1));
            if (tar - num  >= 0)
                res = res || check(tmp, i + 1, num, dp);
        }

        return dp[idx][tar] = res;
    }


    //2699. Modify Graph Edge Weights

}
