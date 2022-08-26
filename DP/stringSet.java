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

    //DP is pre-made
    public String longestPalindromeSubseq_printingDP(String s,int st,int en,int[][]dp){
        if(st>=en){
            return (st==en) ? s.charAt(st) + "" : "";
        }

        if(s.charAt(st)==s.charAt(en)){
            return  s.charAt(st) + "" + longestPalindromeSubseq_printingDP(s,st+1,en-1,dp) +s.charAt(en);
        }

        if(dp[st+1][en]>=dp[st][en-1]){
            return  longestPalindromeSubseq_printingDP(s,st+1,en,dp) ;
        }

        return  longestPalindromeSubseq_printingDP(s,st,en-1,dp) ;
    }
    
    //using string DP
    public String longestPalindromeSubseq_printing(String s,int st,int en,String[][]dp){
        if(st>=en){
            return dp[st][en] = (st==en) ? s.charAt(st) + "": "";
        }

        if(dp[st][en]!=null) return dp[st][en];


        if(s.charAt(st)==s.charAt(en)){
            return dp[st][en]  = s.charAt(st) + "" + longestPalindromeSubseq_printing(s,st+1,en-1,dp) +s.charAt(en);
        }

        String chosingEn = longestPalindromeSubseq_printing(s,st+1,en,dp);
        String chosingSt = longestPalindromeSubseq_printing(s,st,en-1,dp);

        return dp[st][en]  =  (chosingEn.length() >=chosingSt.length() ) ? chosingEn : chosingSt;

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

}
