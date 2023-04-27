public class WeeklyContest342 {

    //2651. Calculate Delayed Arrival Time
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime+delayedTime)%24;
    }

    //2652. Sum Multiples
    public int sumOfMultiples(int n) {
        int ans = 0;

        for(int i =1;i<=n;i++){
            ans+= (i%3==0 || i%5==0 || i%7==0) ? i  :0 ;
        }

        return ans;
    }
    

    //2653. Sliding Subarray Beauty
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[]ans = new int[n-k+1];
        int[][]freq = new int[n][51];

        for(int i = 0; i<n ;i++){
            int ele = nums[i];

            if(ele<0){
                int c = Math.abs(nums[i]);
                freq[i][c]++;
            }
        }

        for(int i = 1 ;i<n;i++){
            for(int j = 0 ;j<51;j++){
                freq[i][j]+=freq[i-1][j];
            }
        }


        for(int i = 0 ;i<ans.length;i++){
            int st = i;
            int en = i + k - 1;
            ans[i] = -1*findMin(freq,st,en,x);
        }
    
        return ans;
    }

    public int findMin(int[][]freq,int st,int en,int x){
        int cnt = 0;

        for(int j = 50 ;j>=1;j--){
            cnt += freq[en][j] - ((st==0) ? 0 : freq[st-1][j]);
            if(cnt>=x) return j;
        }

        return 0;
    }

    //2654. Minimum Number of Operations to Make All Array Elements Equal to 1
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ones = 0;
        for(int ele :nums ){
            if(ele==1) ones++;
        }
        if(ones>0) return n - ones;

        //if ones are not present, try to make one element as 

        int min  = (int)1e9;
        for(int i = 0 ;i<n;i++){
            int g = nums[i];
            for(int j = i+1;j<n;j++){
                g = findGCD(g,nums[j]);
                if(g==1){
                    min = Math.min(j-i+1,min);
                    break;
                }
            }
        }

        if(min==(int)1e9) return -1;

        int stepsToGetOne = min - 1;
        return stepsToGetOne + n-1;

    }

    public int findGCD(int a,int b){
        if(b==0) return a;
        return findGCD(b,a%b);
    }
}
