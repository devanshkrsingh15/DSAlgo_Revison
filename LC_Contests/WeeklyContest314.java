import java.util.*;

public class WeeklyContest314 {
    // 2432. The Employee That Worked on the Longest Task

    public int hardestWorker(int n, int[][] logs) {
        int st = 0;
        int ans = -1;
        int maxTime = 0;

        for (int[] l : logs) {
            int idx = l[0];
            int en = l[1];
            int time = en - st;

            if (time >= maxTime) {
                if (time == maxTime) {
                    ans = Math.min(idx, ans);
                } else {
                    ans = idx;
                    maxTime = time;
                }
            }

            st = en;

        }

        return ans;
    }

    //2433. Find The Original Array of Prefix Xor
    public int[] findArray(int[] pref) {
        int n  = pref.length;

        int[]ans =new int[n];
        ans[0] = pref[0];
        for(int i=  1;i<n;i++){
            ans[i] =( pref[i]^pref[i-1]) ;
        }


        return ans;
    }

    //2434. Using a Robot to Print the Lexicographically Smallest String
    public String robotWithString(String s) {
        StringBuilder t =  new StringBuilder();
        StringBuilder p =  new StringBuilder();
        int i = 0;
        int n= s.length();

        while(i<n){
            char ch = s.charAt(i);
            if(p.length()==0 || t.charAt(t.length()-1) >= ch) t.append(ch);
            else p.append(ch);

            if(i+1<n){
                while(t.length()!=0 && t.charAt(t.length()-1)<s.charAt(i+1)){
                    p.append(t.charAt(t.length()-1));
                    t.deleteCharAt(t.length()-1);
                }
            }

            i++;
        }

        while(t.length()!=0){
            p.append(t.charAt(t.length()-1));
            t.deleteCharAt(t.length()-1);
        }


        return p.toString();
    }

    //2435. Paths in Matrix Whose Sum Is Divisible by K
    int[][]direcs ={{0,1},{1,0}};
    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][]dp = new int[n+1][m+1][k+1];
      
        for(int[][]d2:dp){
            for(int[]d:d2)Arrays.fill(d,-1);
        }
        
        return numberOfPaths(grid,k,0,0,0,dp);
    }
    
    public int numberOfPaths(int[][] grid,int k,int i,int j,int sum,int[][][]dp){
        int mod = (int)1e9 + 7;
        int n = grid.length;
        int m = grid[0].length;
        if(i==n-1 && j==m-1){
            int nrem = (sum + grid[i][j])%k;
            return dp[i][j][sum%k] =(nrem==0) ?  1 : 0;
        }
        
        if(dp[i][j][sum%k]!=-1)  return dp[i][j][sum%k];
        
        int ans= 0;
        for(int l = 0 ; l<2;l++){
            int x = i +  direcs[l][0];
            int y  = j +  direcs[l][1];
            if(x>=0 && y>=0 && x<n  && y<m){
                int ni = x;
                int nj = y;
                int nsum = grid[i][j] +sum;
                
                ans= (ans%mod + numberOfPaths(grid,k,ni,nj,nsum,dp)%mod)%mod;
            }
        }
        
        
        return dp[i][j][sum%k] =( ans )%mod;
    }

}
