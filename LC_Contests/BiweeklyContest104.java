import java.util.*;

public class BiweeklyContest104 {
    // 2678. Number of Senior Citizens
    public int countSeniors(String[] details) {
        int cnt = 0;

        for (String s : details) {
            if (Integer.parseInt(s.substring(11, 13)) > 60)
                cnt++;
        }

        return cnt;
    }

    // 2679. Sum in a Matrix
    public int matrixSum(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        PriorityQueue<Integer>rowPQs[] = new PriorityQueue[n];
        for(int  i  = 0 ;i<n; i++) rowPQs[i] = new PriorityQueue<>((a,b)->{
            return nums[b/m][b%m]-nums[a/m][a%m];
        });


        for(int i = 0 ; i<n;i++){
            for(int  j  = 0;j<m;j++){
                rowPQs[i].add(i*m + j);
            }
        }

        int itr = 0;
        int ans = 0;
        while(itr++<m){
            int max = 0;
            for(int i = 0 ;i<n;i++){
                int idx =rowPQs[i].remove();
                max = Math.max(max,nums[idx/m][idx%m]);
            }
            ans += max;
        }

        return ans;
    }

    //2680. Maximum OR
}
