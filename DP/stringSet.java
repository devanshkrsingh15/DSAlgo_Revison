package DP;

import java.util.Arrays;

public class stringSet {

    // 516. Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int tab_ans = longestPalindromeSubseq_tab(s);
        return longestPalindromeSubseq_memo(s, 0, n - 1, dp);
    }

    // DP is pre-made
    public String longestPalindromeSubseq_printingDP(String s, int st, int en, int[][] dp) {
        if (st >= en) {
            return (st == en) ? s.charAt(st) + "" : "";
        }

        if (s.charAt(st) == s.charAt(en)) {
            return s.charAt(st) + "" + longestPalindromeSubseq_printingDP(s, st + 1, en - 1, dp) + s.charAt(en);
        }

        if (dp[st + 1][en] >= dp[st][en - 1]) {
            return longestPalindromeSubseq_printingDP(s, st + 1, en, dp);
        }

        return longestPalindromeSubseq_printingDP(s, st, en - 1, dp);
    }

    // using string DP
    public String longestPalindromeSubseq_printing(String s, int st, int en, String[][] dp) {
        if (st >= en) {
            return dp[st][en] = (st == en) ? s.charAt(st) + "" : "";
        }

        if (dp[st][en] != null)
            return dp[st][en];

        if (s.charAt(st) == s.charAt(en)) {
            return dp[st][en] = s.charAt(st) + "" + longestPalindromeSubseq_printing(s, st + 1, en - 1, dp)
                    + s.charAt(en);
        }

        String chosingEn = longestPalindromeSubseq_printing(s, st + 1, en, dp);
        String chosingSt = longestPalindromeSubseq_printing(s, st, en - 1, dp);

        return dp[st][en] = (chosingEn.length() >= chosingSt.length()) ? chosingEn : chosingSt;

    }

    private int longestPalindromeSubseq_tab(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st >= en) {
                    dp[st][en] = (st == en) ? 1 : 0;
                } else {
                    if (s.charAt(st) == s.charAt(en)) {
                        dp[st][en] = 2 + dp[st + 1][en - 1];
                    } else {
                        dp[st][en] = Math.max(dp[st + 1][en], dp[st][en - 1]);
                    }
                }
            }
        }

        return dp[0][n - 1];

    }

    private int longestPalindromeSubseq_memo(String s, int st, int en, int[][] dp) {
        if (st >= en)
            return dp[st][en] = (st == en) ? 1 : 0;

        if (dp[st][en] != -1)
            return dp[st][en];

        if (s.charAt(st) == s.charAt(en)) {
            return dp[st][en] = 2 + longestPalindromeSubseq_memo(s, st + 1, en - 1, dp);
        }

        return dp[st][en] = Math.max(longestPalindromeSubseq_memo(s, st + 1, en, dp),
                longestPalindromeSubseq_memo(s, st, en - 1, dp));
    }

    // 115. Distinct Subsequences
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct_memo(s, t, dp, n, m);
    }

    private int numDistinct_memo(String s, String t, int[][] dp, int n, int m) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (m == 0) ? 1 : 0;
        }

        if (n < m) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            return dp[n][m] = numDistinct_memo(s, t, dp, n - 1, m - 1) + numDistinct_memo(s, t, dp, n - 1, m);
        }
        return dp[n][m] = numDistinct_memo(s, t, dp, n - 1, m);

    }

    private int numDistinct_tab(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = (j == 0) ? 1 : 0;
                else if (i < j)
                    dp[i][j] = 0;
                else {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][m];
    }

    // Count Palindromic Subsequences (GFG)

    long mod = (long) 1e9 + 7;

    public long countPS(String str) {
        int n = str.length();
        long[][] dp = new long[n][n];
        for (long[] d : dp)
            Arrays.fill(d, -1l);

        return countPS_memo(str, dp, 0, n - 1);
    }

    private long countPS_memo(String str, long[][] dp, int i, int j) {
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1l : 0;
        }

        if (dp[i][j] != -1l)
            return dp[i][j];

        long bothEx = countPS_memo(str, dp, i + 1, j - 1);
        long ithInc = countPS_memo(str, dp, i, j - 1);
        long jthInc = countPS_memo(str, dp, i + 1, j);

        if (str.charAt(i) == str.charAt(j)) {
            return dp[i][j] = (ithInc % mod + jthInc % mod + 1l) % mod;
        }

        return dp[i][j] = (ithInc % mod + jthInc % mod - bothEx % mod + mod) % mod;
    }

    private long countPS_tab(String str) {
        int n = str.length();
        long[][] dp = new long[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {

                if (st >= en) {
                    dp[st][en] = (st == en) ? 1l : 0;
                } else {
                    long bothEx = dp[st + 1][en - 1];
                    long ithInc = dp[st][en - 1];
                    long jthInc = dp[st + 1][en];

                    if (str.charAt(st) == str.charAt(en))
                        dp[st][en] = (ithInc % mod + jthInc % mod + 1l) % mod;
                    else
                        dp[st][en] = (ithInc % mod + jthInc % mod - bothEx % mod + mod) % mod;

                }

            }
        }

        return dp[0][n - 1];

    }

    // 1143. Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return longestCommonSubsequence_memo(text1, text2, dp, n, m);
    }

    public int longestCommonSubsequence_memo(String text1, String text2, int[][] dp, int n, int m) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 && m == 0) ? 1 : 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (text1.charAt(n - 1) == text2.charAt(m - 1))
            return dp[n][m] = 1 + longestCommonSubsequence_memo(text1, text2, dp, n - 1, m - 1);

        return dp[n][m] = Math.max(longestCommonSubsequence_memo(text1, text2, dp, n, m - 1),
                longestCommonSubsequence_memo(text1, text2, dp, n - 1, m));
    }

    public int longestCommonSubsequence_tab(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][m];
    }

    // 1035. Uncrossed Lines
    // longest common subsequence
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    if (nums1[i - 1] == nums2[j - 1])
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][m];
    }

    // 1458. Max Dot Product of Two Subsequences
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e9);

        return maxDotProduct_memo(nums1, nums2, dp, n, m);
    }

    public int maxDotProduct_memo(int[] nums1, int[] nums2, int[][] dp, int n, int m) {
        if (n == 0 || m == 0)
            return dp[n][m] = -(int) 1e9;

        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];

        int myval = nums1[n - 1] * nums2[m - 1];
        int bothEx = maxDotProduct_memo(nums1, nums2, dp, n - 1, m - 1);
        int nthEx = maxDotProduct_memo(nums1, nums2, dp, n - 1, m);
        int mthEx = maxDotProduct_memo(nums1, nums2, dp, n, m - 1);

        return dp[n][m] = max(myval, myval + bothEx, bothEx, nthEx, mthEx);

    }

    public int maxDotProduct_tab(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = -(int) 1e9;
                } else {
                    int myval = nums1[i - 1] * nums2[j - 1];
                    int bothEx = dp[i - 1][j - 1];
                    int nthEx = dp[i - 1][j];
                    int mthEx = dp[i][j - 1];

                    dp[i][j] = max(myval, myval + bothEx, bothEx, nthEx, mthEx);
                }

            }
        }

        return dp[n][m];
    }

    public int max(int... arr) {
        int max = -(int) 1e8;
        for (int ele : arr)
            max = Math.max(max, ele);
        return max;
    }

    // 72. Edit Distance
    // fix it -> converting word1 to word2
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDistance_memo(word1, word2, dp, n, m);
    }

    public int minDistance_memo(String word1, String word2, int[][] dp, int n, int m) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 0;
            else if (n == 0)
                return dp[n][m] = m;
            else if (m == 0)
                return dp[n][m] = n;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
            return dp[n][m] = minDistance_memo(word1, word2, dp, n - 1, m - 1);
        } else {
            int replace = minDistance_memo(word1, word2, dp, n - 1, m - 1);
            int delete = minDistance_memo(word1, word2, dp, n, m - 1);
            int add = minDistance_memo(word1, word2, dp, n - 1, m);

            return 1 + Math.min(replace, Math.min(delete, add));
        }
    }

    // 44. Wildcard Matching
    /*
     * -1 -> unvisited
     * 0 -> false
     * 1 -> true
     */
    public boolean isMatch(String s, String p) {
        // remove extra *
        p = removeExtraStars(p);
        int n = s.length();
        int m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int res = isMatch_memo(s, p, dp, n, m);

        return res == 1;
    }

    private String removeExtraStars(String p) {
        if (p.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (ch != '*') {
                sb.append(ch);
            } else {
                sb.append(ch);
                if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                    while (i + 1 < p.length() && p.charAt(i + 1) == '*')
                        i++;
                }

            }
        }

        return sb.toString();
    }

    public int isMatch_memo(String s, String p, int[][] dp, int n, int m) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (n == 0)
                return dp[n][m] = (m == 1 && p.charAt(m - 1) == '*') ? 1 : 0;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char sp = s.charAt(n - 1);
        char pp = p.charAt(m - 1);

        if (sp == pp) {
            return dp[n][m] = (isMatch_memo(s, p, dp, n - 1, m - 1) == 1) ? 1 : 0;
        } else {
            if (pp == '?') {
                return dp[n][m] = (isMatch_memo(s, p, dp, n - 1, m - 1) == 1) ? 1 : 0;
            } else if (pp == '*') {
                int empty = isMatch_memo(s, p, dp, n, m - 1);
                int seq = isMatch_memo(s, p, dp, n - 1, m);
                return dp[n][m] = (empty == 1 || seq == 1) ? 1 : 0;
            } else {
                return dp[n][m] = 0;
            }
        }
    }

    public boolean isMatch_tab(String s, String p) {
        p = removeExtraStars(p);
        int n = s.length();
        int m = p.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0)
                        dp[i][j] = 1;
                    else if (i == 0)
                        dp[i][j] = (j == 1 && p.charAt(j - 1) == '*') ? 1 : 0;
                    else
                        dp[i][j] = 0;
                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = (dp[i - 1][j - 1] == 1) ? 1 : 0;
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = (dp[i - 1][j] == 1 || dp[i][j - 1] == 1) ? 1 : 0;
                    } else {
                        dp[i][j] = 0;
                    }
                }

            }
        }

        return dp[n][m] == 1;

    }

}

// Leetcode 730. Count Different Palindromic Subsequences
class CountDifferentPalindromicSubsequences {
    long mod = (long) 1e9 + 7;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        long[][] dp = new long[n][n];
        for (long[] d : dp)
            Arrays.fill(d, -1l);

        return (int) countPalindromicSubsequences_memo(s, dp, 0, n - 1);

    }

    public int countPalindromicSubsequences_tab(String s) {
        int n = s.length();
        long[][] dp = new long[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st >= en)
                    dp[st][en] = (st == en) ? 1 : 0;
                else {
                    long a = dp[st + 1][en - 1];
                    long b = dp[st + 1][en];
                    long c = dp[st][en - 1];

                    if (s.charAt(st) != s.charAt(en)) {
                        dp[st][en] = (b % mod + c % mod - a % mod + mod) % mod;
                    } else {
                        int l = st + 1;
                        int r = en - 1;

                        while (l <= r && s.charAt(l) != s.charAt(st))
                            l++;
                        while (l <= r && s.charAt(r) != s.charAt(en))
                            r--;

                        if (l == r) {
                            dp[st][en] = (2 * a % mod + 1 % mod) % mod;
                        } else if (l < r) {
                            long mid = dp[l + 1][ r - 1];
                            dp[st][en] = (2 * a % mod - mid % mod + mod) % mod;
                        } else {
                            dp[st][en] = (2 * a % mod + 2 % mod) % mod;
                        }

                    }

                }
            }
        }

        return (int)(dp[0][n-1]%mod);
    }

    public long countPalindromicSubsequences_memo(String s, long[][] dp, int st, int en) {
        if (st >= en) {
            return (st == en) ? 1l : 0;
        }

        if (dp[st][en] != -1l)
            return dp[st][en];

        long a = countPalindromicSubsequences_memo(s, dp, st + 1, en - 1);
        long b = countPalindromicSubsequences_memo(s, dp, st + 1, en);
        long c = countPalindromicSubsequences_memo(s, dp, st, en - 1);

        if (s.charAt(st) != s.charAt(en)) {
            return dp[st][en] = (b % mod + c % mod - a % mod + mod) % mod;
        } else {
            int l = st + 1;
            int r = en - 1;

            while (l <= r && s.charAt(l) != s.charAt(st))
                l++;
            while (l <= r && s.charAt(r) != s.charAt(en))
                r--;

            if (l == r) {
                return dp[st][en] = (2 * a % mod + 1 % mod) % mod;
            } else if (l < r) {
                long mid = countPalindromicSubsequences_memo(s, dp, l + 1, r - 1);
                return dp[st][en] = (2 * a % mod - mid % mod + mod) % mod;
            } else {
                return dp[st][en] = (2 * a % mod + 2 % mod) % mod;
            }

        }
    }
}