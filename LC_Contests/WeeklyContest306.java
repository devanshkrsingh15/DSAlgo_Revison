import java.util.*;

public class WeeklyContest306 {

    // 2373. Largest Local Values in a Matrix
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;

        int[][] ans = new int[n - 2][n - 2];
        int m = ans[0].length;
        int idx = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {

                int max = findMax(grid, i, j);
                ans[idx / m][idx % m] = max;
                idx++;

            }
        }

        return ans;
    }

    private int findMax(int[][] grid, int r, int c) {
        int max = 0;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }

        return max;
    }

    // 2374. Node With Highest Edge Score
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] escore = new long[n];
        for (int i = 0; i < n; i++) {
            int u = i;
            int v = edges[i];
            escore[v] += (long) u;
        }

        ArrayList<long[]> list = new ArrayList<>(); // node,score

        for (int i = 0; i < n; i++) {
            list.add(new long[] { (long) i, escore[i] });
        }

        Collections.sort(list, (a, b) -> {
            if (a[1] == b[1])
                return (int) a[0] - (int) b[0];
            if (a[1] > b[1])
                return -1;
            else
                return 1;
        });

        return (int) list.get(0)[0];
    }

    // 2375. Construct Smallest Number From DI String
    String ans = "";

    public String smallestNumber(String pattern) {
        smallestNumber_(pattern, -1, new boolean[10], "");
        return ans;

    }

    public void smallestNumber_(String pattern, int idx, boolean[] used, String psf) {
        if (idx == pattern.length()) {
            if (ans.equals(""))
                ans = psf;
            else {
                int cval = Integer.parseInt(psf);
                int oval = Integer.parseInt(ans);

                if (oval > cval)
                    ans = psf;
            }

            return;
        }

        if (idx == -1) {
            for (int i = 1; i <= 9; i++) {
                if (used[i] == false) {
                    used[i] = true;
                    smallestNumber_(pattern, idx + 1, used, psf + "" + i);
                    used[i] = false;
                }
            }
        } else {
            char ch = pattern.charAt(idx);
            if (ch == 'I') {
                char lch = psf.charAt(psf.length() - 1);
                int val = (int) (lch - '0');
                for (int i = val + 1; i <= 9; i++) {
                    if (used[i] == false) {
                        used[i] = true;
                        smallestNumber_(pattern, idx + 1, used, psf + "" + i);
                        used[i] = false;
                    }
                }
            } else {
                char lch = psf.charAt(psf.length() - 1);
                int val = (int) (lch - '0');
                for (int i = val - 1; i >= 1; i--) {
                    if (used[i] == false) {
                        used[i] = true;
                        smallestNumber_(pattern, idx + 1, used, psf + "" + i);
                        used[i] = false;
                    }
                }
            }
        }
    }

    //2376. Count Special Integers
    public int countSpecialNumbers(int n) {
        String s = n+"";
       
        int[][][]dp = new int[(1<<10)][s.length()][2];
        for(int[][]ddp:dp){
            for(int[]d:ddp) Arrays.fill(d,-1);
        }
        
        //{mask,idx,lim}
        return countSpecialNumbers_(s,0,0,1,dp);
    }
    
    public int countSpecialNumbers_(String s,int mask,int idx,int lim,int[][][]dp){
        if(idx==s.length()){
            return (mask==0) ? 0 : 1;
        }
        
        if(dp[mask][idx][lim]!=-1) return dp[mask][idx][lim];
        
        int ans = 0;
        
        int limit = (lim==1)  ? s.charAt(idx) - '0'  : 9;
        
        for(int k = 0; k<= limit;k++){
            if( (mask&(1<<k)) != 0 ) continue;
            
            //leading zeros
            int nmask = (k==0 && mask==0 ) ? mask : (mask|(1<<k)) ; 
            int nlim = -1;
            
            if(lim==0){
                nlim = 0;
            }else{
                if(k==(s.charAt(idx) - '0')){
                    nlim = 1;
                }else{
                    nlim = 0;
                }
            }
            
            ans += countSpecialNumbers_(s,nmask,idx+1,nlim,dp);
            
            
            
        }
        
        
        
        return dp[mask][idx][lim] =ans;
    }
}