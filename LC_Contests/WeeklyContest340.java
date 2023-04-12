import java.util.*;

public class WeeklyContest340 {

    // 2614. Prime In Diagonal
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j || i + j + 1 == n) && isPrime(nums[i][j])) {
                    max = Math.max(max, nums[i][j]);
                }
            }
        }

        return max;
    }

    public boolean isPrime(int val) {
        for (int i = 2; i <= (int) Math.sqrt(val); i++) {
            if (val % i == 0)
                return false;
        }

        return val > 1;
    }

    // 2615. Sum of Distances

    public long[] distance(int[] nums) {
        HashMap<Long, ArrayList<Long>> prefixSum = new HashMap<>();
        HashMap<Long, ArrayList<Integer>> freqIndices = new HashMap<>();

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            long val = (long) nums[i];
            prefixSum.putIfAbsent(val, new ArrayList<>());
            freqIndices.putIfAbsent(val, new ArrayList<>());

            freqIndices.get(val).add(i);
            long sof = prefixSum.get(val).size() == 0 ? 0 : prefixSum.get(val).get(prefixSum.get(val).size() - 1);
            prefixSum.get(val).add(sof + i);
        }

        long[] ans = new long[n];

        for (long k : freqIndices.keySet()) {
            if (freqIndices.get(k).size() == 1) {
                ans[freqIndices.get(k).get(0)] = 0l;
                continue;
            }

            ArrayList<Integer> idxList = freqIndices.get(k);
            ArrayList<Long> sumList = prefixSum.get(k);

            for (int i = 0; i < idxList.size(); i++) {
                long leftSum = i == 0 ? 0 : (long) idxList.get(i) * (i) - sumList.get(i - 1);
                long rightSum = i == idxList.size() - 1 ? 0
                        : (sumList.get(idxList.size() - 1) - sumList.get(i))
                                - (long) (idxList.size() - i - 1) * idxList.get(i);

                ans[idxList.get(i)] = leftSum + rightSum;
            }

        }
        return ans;
    }

    //2616. Minimize the Maximum Difference of Pairs
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        if(n<=1) return 0;
        
        Arrays.sort(nums);
        int lo = 0;
        int hi = nums[n-1] - nums[0];
        int ans = 0;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            
            if(isPossible(nums,mid,p)){
                ans = mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        
        return ans;
    }
    
    public boolean isPossible(int[]nums,int maxDiff,int reqP){
        int i = 1;
        int pairs= 0;

        while(i<nums.length){
            if(nums[i] - nums[i-1] <=maxDiff){
                i+=2;
                pairs++;
            }else{
                i++;
            }
        }


        return pairs>=reqP;
    }
    //71. Simplify Path
    class LC71 {
        public String removeDoubleSlashes(String path){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<path.length();i++){
                char ch = path.charAt(i);
                sb.append(ch);
                if(ch=='/'){
                    while(i+1<path.length() && path.charAt(i+1)=='/') i++;
                }
            }
    
            return sb.toString();
        }
        public String simplifyPath(String path) {
            // path = removeDoubleSlashes(path);
            String[]arr = path.split("/");
            ArrayList<String>list = new ArrayList<>();
    
            for(int i = 0;i<arr.length;i++){
                String s = arr[i];
                if(s.length()==0 || s.equals(".")) continue;
                if(s.equals("..")){
                    if(list.size()!=0)list.remove(list.size()-1);
                    else continue;
                }else list.add(s);
            }
    
            if(list.size()==0) return "/";
            
    
            StringBuilder sb= new StringBuilder();
            for(String s : list){
               sb.append('/');
               sb.append(s);
            }
    
             return sb.toString();
        }
  }
}
