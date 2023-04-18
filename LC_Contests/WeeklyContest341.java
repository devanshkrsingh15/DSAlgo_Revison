import java.util.*;

public class WeeklyContest341 {

    //2643. Row With Maximum Ones
    public int[] rowAndMaximumOnes(int[][] mat) {
        int ridx = -1;
        int cnt = -(int)1e9;

        for(int i = 0;i<mat.length;i++){
            int c = 0;
            for(int ele :mat[i]){
                c += ele;
            }

            if(c>cnt){
                cnt = c;
                ridx = i;
            }
        }

        //return {idx,cnt}

        return new int[]{ridx,cnt};
    }


    //2644. Find the Maximum Divisibility Score
    public int maxDivScore(int[] nums, int[] divisors) {
        int n =divisors.length;
        int maxCnt = -1;
        int maxIdx = -1;
        
        for(int i = 0;i<n;i++){
            int cnt = 0;
            for(int ele :nums){
                if(ele%divisors[i]==0) cnt++;
            }
            
            if(cnt>maxCnt){
                maxCnt = cnt;
                maxIdx = divisors[i];
            }else if(cnt==maxCnt){
                maxIdx = Math.min(maxIdx,divisors[i]);
            }
        }
        
        
        return maxIdx;
    }

    //2645. Minimum Additions to Make Valid String
}
