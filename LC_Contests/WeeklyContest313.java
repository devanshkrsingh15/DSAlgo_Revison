import java.util.*;

public class WeeklyContest313 {
    // 2427. Number of Common Factors
    public int commonFactors(int a, int b) {
        int ans = 0;
        int g = gcd(a, b);
        for (int i = 1; i <= g; i++) {
            if (a % i == 0 && b % i == 0)
                ans++;
        }

        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    // 2428. Maximum Sum of an Hourglass
    public int maxSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] rowPsum = generateRowPrefixSum(grid);

        int[][] colPsum = generateColPrefixSum(grid);

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 2 < n && j + 2 < m) {
                    int top = rowPsum[i][j + 2] - ((j == 0) ? 0 : rowPsum[i][j - 1]);
                    int bottom = rowPsum[i + 2][j + 2] - ((j == 0) ? 0 : rowPsum[i + 2][j - 1]);
                    int mid = colPsum[i + 1][j + 1] - (colPsum[i][j + 1]);

                    max = Math.max(max, top + bottom + mid);
                }

            }
        }

        return max;
    }

    private int[][] generateRowPrefixSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0)
                    ans[i][j] = grid[i][j];
                else
                    ans[i][j] = ans[i][j - 1] + grid[i][j];
            }
        }

        return ans;
    }

    private int[][] generateColPrefixSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] ans = new int[n][m];

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 0)
                    ans[i][j] = grid[i][j];
                else
                    ans[i][j] = ans[i - 1][j] + grid[i][j];
            }
        }

        return ans;
    }


    //2429. Minimize XOR
    public int minimizeXor(int num1, int num2) {
        int bit = countSetBits(num2);
        
        int ans = 0;
        for(int i = 31 ;i>=0 && bit>0 ;i--){
            int mask = (1<<i);
            if((num1&mask)!=0){
                ans|=mask;
                bit--;
            }
        }
        
        for(int i = 0 ;i<32 && bit>0 ;i++){
            int mask = (1<<i);
            if((ans&mask)==0){
                ans|=mask;
                 bit--;
            }
        }
        
        
        return ans;
       
    }
    

    
    public int countSetBits(int a){
        int cnt = 0;
        
        while(a>0){
            a= a&(a-1);
            cnt++;
        }
        
        return cnt;
    }
}
