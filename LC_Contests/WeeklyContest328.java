import java.util.*;

public class WeeklyContest328 {

    // 2535. Difference Between Element Sum and Digit Sum of an Array
    public int differenceOfSum(int[] nums) {
        int sof = 0;
        int dig = 0;

        for (int ele : nums) {
            sof += ele;
            dig += getSum(ele);
        }

        return Math.abs(sof - dig);

    }

    private int getSum(int ele) {
        int sof = 0;
        while (ele != 0) {
            sof += ele % 10;
            ele /= 10;

        }

        return sof;
    }

    // 2536. Increment Submatrices by One

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n];

        for (int[] q : queries) {
            int sr = q[0];
            int sc = q[1];
            int er = q[2];
            int ec = q[3];

            for (int r = sr; r <= er; r++) {
                ans[r][sc]++;
                if (ec + 1 < n)
                    ans[r][ec + 1]--;
            }
        }

        for(int i  = 0 ; i<n;i++){
            int sof  = 0;
            for(int j = 0; j<n;j++){
                sof+=ans[i][j];
                ans[i][j]=sof;
            }
        }

        return ans;
    }

    //2537. Count the Number of Good Subarrays
    public long countGood(int[] nums, int k) {
        long ans  = 0;
        int n  = nums.length;
        ArrayList<Long>list = new ArrayList<>();
        HashMap<Integer,Long>map = new HashMap<>();
        long sof = 0;

        int idx = 0;
        while(idx<n){
            int ele = nums[idx];
            sof += map.getOrDefault(ele, 0l);
            map.put(ele, map.getOrDefault(ele, 0l)+1l);
            ans += findFloor(list,sof-k) + 1;
            idx++;
        }
        return ans;
       
    }

    private long findFloor(ArrayList<Long> list, long tar) {
        int lo = 0;
        long ans = -1;
        int hi = list.size()-1;

        while(lo<=hi){
            int mid = lo + (hi-lo)/2;

            if(list.get(mid)>tar){
                hi  = mid-1;
            }else{
                ans = lo;
                lo = mid+1;
            }
        }

        return ans;
    }
}
