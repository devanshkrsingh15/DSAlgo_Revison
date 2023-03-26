import java.util.*;

public class WeeklyContest338 {

    //2600. K Items With the Maximum Sum
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int a = Math.min(k,numOnes);
        k-=a;
        int b = Math.min(k,numZeros);
        k-=b;
        int c = Math.min(k,numNegOnes);
       
        return a*1 + b*0 +c*(-1) ;
        
    }

    //2601. Prime Subtraction Operation
    public boolean primeSubOperation(int[] nums) {
        TreeSet<Integer>primes = findAllPrimes(1001);
        int n = nums.length;
        if(n==1) return true;
        for(int i = 0;i<n;i++){
            if(i==0){
                if(primes.floor(nums[i] -1)==null){
                       if(nums[i]<nums[i+1]) continue;
                    return false;
                    }
                int floor = primes.floor(nums[i] -1);
                int nval = nums[i] - floor;
                nums[i] = nval;
            }else{
                if(primes.floor(nums[i] - nums[i-1] -1)==null){
                    if(nums[i]>nums[i-1]) continue;
                    return false;
                }
                int floor = primes.floor(nums[i] - nums[i-1] -1);
                int nval = nums[i] - floor;
                nums[i] = nval;

            }
        }
        
        
        return true;
        
    }
    
    public TreeSet<Integer> findAllPrimes(int n){
        boolean[]isPrime = new boolean[n+1];
        for(int i = 2 ; i<=n;i++){
            if(isPrime[i]==false){
                for(int  j = 2*i ;j<=n;j+=i){
                    isPrime[j] = true;
                }
            }
        }
        
        TreeSet<Integer>set = new TreeSet<>();
        for(int i = 2;i<=n;i++){
            if(isPrime[i]==false) set.add(i);
        }
        
        return set;
        
    }

    //2602. Minimum Operations to Make All Array Elements Equal
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[]psum = new long[n];
        psum[0] = nums[0];
        for(int i  = 1;i<n;i++) psum[i] += psum[i-1] + (long)nums[i];

        List<Long>ans = new ArrayList<>();
        for(int q: queries ){
            int higher = findCeiling(nums,q);
            int lower = findFloor(nums,q);

            int left = lower+1;
            int right = nums.length - higher;
            
            long a = (lower==-1) ? 0 :  (long)((long)q*(long)left) -  psum[lower] ;
            long b = (higher==-1) ? 0 : (higher==0) ? psum[n-1] -  (long)q*(long)right :  psum[n-1] -  psum[higher-1]  -  (long)q*(long)right ;
            ans.add(a + b);
        }
        
        return ans;    
    }
    
    public int findCeiling(int[] nums,int tar){
        int n = nums.length;
        int lo = 0;
        int hi = n-1;
        
        int ans = -1;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid]>tar){
                ans = mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        
        return ans;
    }
    
    public int findFloor(int[] nums,int tar){
        int n = nums.length;
        int lo = 0;
        int hi = n-1;
        
        int ans = -1;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid]<tar){
                ans = mid;
                lo = mid+1;
            }else{
               hi = mid-1;
            }
        }
        
        return ans;
    }
    
}
