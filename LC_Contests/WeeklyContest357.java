import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class WeeklyContest357 {

    // 2810. Faulty Keyboard
    public String finalString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch != 'i')
                sb.append(ch);
            else {
                sb = sb.reverse();
            }
        }

        return sb.toString();
    }

    // 2811. Check if it is Possible to Split Array
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        int[] psum = new int[n];
        for (int i = 0; i < n; i++) {
            psum[i] = nums.get(i);
            if (i - 1 >= 0)
                psum[i] += psum[i - 1];
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return canSplitArray_(nums, 0, n - 1, m, dp, psum) == n;

    }

    public int canSplitArray_(List<Integer> nums, int st, int en, int m, int[][] dp, int[] psum) {
        if (en - st + 1 <= 2) {
            return en - st + 1;
        }

        if (dp[st][en] != -1)
            return dp[st][en];

        int ans = 0;

        if (psum[en] - psum[st] >= m) {
            ans = Math.max(ans, canSplitArray_(nums, st + 1, en, m, dp, psum) + 1);
        }

        if ((psum[en - 1] - (st == 0 ? 0 : psum[st - 1])) >= m) {
            ans = Math.max(ans, canSplitArray_(nums, st, en - 1, m, dp, psum) + 1);
        }

        return dp[st][en] = ans;
    }

    //2812. Find the Safest Path in a Grid
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        if(grid.get(0).get(0)==1 || grid.get(n-1).get(m-1)==1) return 0;

        TreeSet<Integer>thieves = new TreeSet<>();
        for(int i = 0;i<n;i++){
            for(int j =0 ;j<m;j++){
                if(grid.get(i).get(j)==1){
                    thieves.add(i*m + j);
                }
            }
        }

        int[]ans= maximumSafenessFactor_(grid,0,thieves,new boolean[n][m]);

        return ans[1]==1 ? 0 : ans[0];

    
    }

    int[][]direcs = {{0,1},{1,0},{-1,0},{0,-1}};

    public int[] maximumSafenessFactor_(List<List<Integer>> grid,int idx, TreeSet<Integer>thieves,boolean[][]vis ){
        int n = grid.size();
        int m = grid.get(0).size();

        int r = idx/m;
        int c = idx%m;

        vis[r][c] = true;

        if(r==n-1 || c==m-1){
            int sf = getSafenessFactor(thieves,idx,n,m);
            return new int[] { grid.get(n-1).get(m-1), sf};
        }

        int msf = getSafenessFactor(thieves,idx,n,m);
        boolean hasThief =  grid.get(r).get(c)==1;

        for(int k = 0 ; k < direcs.length ;k++){
            int x = r + direcs[k][0];
            int y = c + direcs[k][1];

            if(x>=0 && y>=0 && x<n && y<m && !vis[x][y]){
                int[]fans = maximumSafenessFactor_(grid,x*m+y,thieves,vis);
                hasThief = hasThief || fans[1]!=1 ;
                msf = Math.max(msf,fans[0]);
            }
        }

        return new int[]{msf,hasThief ? 1 : 0};


    }

    public int getSafenessFactor(TreeSet<Integer>thieves,int idx,int n,int m){
        int r = idx/m;
        int c = idx%m;
        int tidx = thieves.ceiling(idx) == null ?  thieves.floor(idx) :  thieves.ceiling(idx);
        int tr = tidx/m ; int tc = tidx%m;
        int sf = Math.abs(r-tr) +  Math.abs(c-tc);

        return sf;
    }
}
