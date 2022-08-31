package DP;

import java.util.Arrays;

public class cutSet {

    // Matrix Chain Multiplication (GFG)
    // if A - p*q && B - q*r
    // A*B -> pqr multiplications and q-1 additions
    public int matrixMultiplication(int N, int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return MCM_memo(arr, 0, n - 1, dp);
    }

    private int MCM_memo(int[] arr, int st, int en, int[][] dp) {
        if (st + 1 == en) {
            return dp[st][en] = 0; // single matrix don't require any multiplication
        }

        if (dp[st][en] != -1)
            return dp[st][en];

        int ans = (int) 1e9;

        for (int cut = st + 1; cut < en; cut++) {
            int lans = MCM_memo(arr, st, cut, dp);
            int rans = MCM_memo(arr, cut, en, dp);
            int myans = arr[st] * arr[cut] * arr[en];

            ans = Math.min(ans, lans + rans + myans);
        }

        return dp[st][en] = ans;

    }

    private int MCM_tab(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st + 1 == en) {
                    dp[st][en] = 0; // single matrix don't require any multiplication
                } else {
                    int ans = (int) 1e9;

                    for (int cut = st + 1; cut < en; cut++) {
                        int lans = dp[st][cut];
                        int rans = dp[cut][en];
                        int myans = arr[st] * arr[cut] * arr[en];

                        ans = Math.min(ans, lans + rans + myans);
                    }

                    dp[st][en] = ans;

                }
            }
        }

        return dp[0][n - 1];

    }

    // Printing brackets in Matrix Chain Multiplication Problem
    class MCMPair {
        String psf;
        int cost;

        MCMPair(String psf, int cost) {
            this.psf = psf;
            this.cost = cost;
        }
    }

    public String matrixChainOrder(int p[], int n) {
        MCMPair[][] dp = new MCMPair[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st + 1 == en) {
                    String path = (char) ('A' + st) + "";
                    dp[st][en] = new MCMPair(path, 0);
                } else {
                    int min = (int) 1e9;
                    String ms = "";

                    for (int cut = st + 1; cut < en; cut++) {
                        MCMPair lpair = dp[st][cut];
                        MCMPair rpair = dp[cut][en];

                        int mycost = p[st] * p[cut] * p[en];

                        if (min > lpair.cost + mycost + rpair.cost) {
                            min = lpair.cost + mycost + rpair.cost;
                            ms = "(" + lpair.psf + rpair.psf + ")";

                        }

                    }
                }
            }

        }

        return dp[0][n - 1].psf;
    }

    //Minimum and Maximum values of an expression with * and +
    class Pair{
        int max;int min;  String maxexp; String minexp;
        Pair(int max,int min,  String maxexp, String minexp){
            this.max = max;
            this.min  = min;
            this.maxexp  = maxexp;
            this.minexp  = minexp;
        }
    }

    public void MinMaxEval(String exp){
        int n  = exp.length();
        Pair[][]dp = new Pair[n][n];

        for(int gap  = 0;gap<n;gap++){
            for(int st  = 0,en  = st+gap;en<n;st++,en++){
                if(st==en){
                    int min = exp.charAt(st)-'0';
                    int max = exp.charAt(st)-'0';
                    String maxexp = exp.charAt(st)+"";
                    String minexp = exp.charAt(st)+"";
                    dp[st][en] = new Pair(max,min,maxexp,minexp);
                }else{
                    Pair mypair  = new Pair(-(int)1e9,(int)1e9,"","");

                    for(int cut = st+1;cut<en;cut+=2){
                        Pair lpair = dp[st][cut-1];
                        Pair rpair = dp[cut+1][en];
                        mypair = eval(lpair,mypair,rpair,exp.charAt(cut));
                    }

                    dp[st][en]= mypair;
                }
            }
        }

    }

    public Pair eval( Pair lpair,Pair mypair,Pair rpair,char op){
        int max = mypair.max;
        int min = mypair.min;

        String mxS =  mypair.maxexp;
        String miS =  mypair.minexp;

        if(op=='+'){
            if(max < lpair.max + rpair.max){
                max = lpair.max + rpair.max;
                mxS = "("+lpair.maxexp +""+op +"" +rpair.maxexp +")";
            }

            if(min>lpair.min + rpair.min){
                min = lpair.min + rpair.min;
                miS = "("+lpair.minexp +""+op +"" +rpair.minexp +")";
            }
        }else if(op=='*'){
            if(max < lpair.max * rpair.max){
                max = lpair.max * rpair.max;
                mxS = "("+lpair.maxexp +""+op +"" +rpair.maxexp +")";
            }

            if(min > lpair.min * rpair.min){
                min = lpair.min * rpair.min;
                miS = "("+lpair.minexp +""+op +"" +rpair.minexp +")";
            }
        }


        return new  Pair(max,min,mxS,miS);
    }

}


// 132. Palindrome Partitioning II
class PalindromePartitioningII{
    int[][]isPal;
    public void fillisPalArray(String s) {
        int n = s.length();
        
        for(int gap = 0;gap<n;gap++){
            for(int st = 0,en=st+gap;en<n;st++,en++){
                if(st==en){
                    isPal[st][en]=1;
                }else if(st+1==en){
                    isPal[st][en]=(s.charAt(st) == s.charAt(en)) ? 2: 0 ;
                }
                else{
                    if(s.charAt(st) == s.charAt(en)  && isPal[st+1][en-1]>0){
                        isPal[st][en] =  isPal[st+1][en-1]+2;
                    }else{
                        isPal[st][en]=0;
                    }
               }
            }
        }
    
    }

    public int minCut(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[][]dp = new int[n][n];
        for(int[]d:dp) Arrays.fill(d,-1);

        return minCut_memo(s,0,n-1,dp);

    }

    //On3
    public int minCut_memo(String s,int st,int en,int[][]dp){
        if(st==en || isPal[st][en]>0) return dp[st][en] = 0;

        if(dp[st][en]!=-1) return  dp[st][en];

        int  min = (int)1e9;

        for(int cut= st;cut<en;cut++){
            if(isPal[st][cut]>0){
                min  = Math.min(min,minCut_memo(s,cut+1,en,dp)+1);
            }
        }

        return dp[st][en] = min;
    }


    //On2
    public int minCut_On2(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[]dp = new int[n];
        Arrays.fill(dp,-1);

        return minCut_memo_On2(s,0,dp);
    }

    public int minCut_memo_On2(String s,int st,int[]dp){
        int n = s.length();
        if(isPal[st][n-1]>0) return dp[st] = 0;

        if(dp[st]!=-1) return  dp[st];

        int  min = (int)1e9;

        for(int cut= st;cut<n;cut++){
            if(isPal[st][cut]>0){
                min  = Math.min(min,minCut_memo_On2(s,cut+1,dp)+1);
            }
        }

        return dp[st] = min;
    }


    public int minCut_Tan(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[]dp = new int[n];
        
        for(int i  = n-1;i>=0;i--){
            if(isPal[i][n-1]>0) dp[i] = 0;
            else{
                int min = (int)1e9;
                for(int j  = i+1;j<n;j++){
                    if(isPal[i][j]>0) min  = Math.min(min,dp[j+1]+1);
                }
                dp[i] = min;
            }
        }

        return dp[0];
    }



}