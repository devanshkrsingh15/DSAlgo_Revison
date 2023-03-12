import java.util.*;

public class BiweeklyContest99 {
    // 2578. Split With Minimum Sum
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num != 0) {
            int n = num % 10;
            arr[n]++;
            num = num / 10;
        }

        int a = 0;
        int b = 0;
        boolean firstTaken = false;

        for (int i = 0; i < 10; i++) {

            while (arr[i] != 0) {
                if (!firstTaken) {
                    a *= 10;
                    a += i;
                    firstTaken = true;
                } else {
                    b *= 10;
                    b += i;
                    firstTaken = false;
                }

                arr[i]--;

            }
        }

        return a + b;

    }

    // 2579. Count Total Number of Colored Cells
    public long coloredCells(int n) {
        if (n == 1)
            return 1l;
        long ans = 1l;
        long prv = 4l;
        for (int i = 2; i <= n; i++) {
            ans += prv;
            prv += 4l;
        }
        return ans;

    }

    //2580. Count Ways to Group Overlapping Ranges
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges,(a,b)->{
            if(a[0]==b[0]) return a[1] - b[1];
            return a[0] - b[0]; 
        });

        Stack<int[]>st = new Stack<>();
        for(int[]rn :ranges ){
            int start = rn[0];
            int end = rn[1];

            while(st.size()!=0 && st.peek()[1]>=rn[0]){
                int[]rem_rn = st.pop();
                start = Math.min(start,rem_rn[0]);
                end = Math.max(end,rem_rn[1]);
            }

            st.push(new int[]{start,end});
        }

        int size = st.size();
        long mod = (long)1e9 + 7;
        long ans = 1;
        for(int i = 0 ; i<size;i++){
            ans= (ans%mod * 2%mod)%mod;
        }

        return (int)ans;
    }
}
