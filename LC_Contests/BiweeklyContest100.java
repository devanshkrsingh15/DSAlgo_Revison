import java.util.*;

public class BiweeklyContest100 {

    //2591. Distribute Money to Maximum Children
    public int distMoney(int money, int children) {
        if(money<children) return -1;
        money-=children;
        int ans = 0;

        while(money>=7 && children>0){
            money-=7;
            children--;
            ans++;
        }

        if(money==3 && children==1) ans--;
        if(money!=0 && children==0) ans--;

        return ans;
    }

    public int distMoney_(int money, int children) {
        if(money<children) return -1;
        int[]arr = new int[children];
        Arrays.fill(arr,1);
        money -= children;
        int cnt = 0;
        int lastIdx = -1;
        for(int i = 0;i<children;i++){
            if(money>=7){
                money-=7;
                arr[i] += 7;
            }else if(money==0){
                break;
            }
            else{
                if(money==3){
                    arr[i]+= 2;
                    money -= 2;
                }else{
                    arr[i] += money;
                    money = 0;
                }
            }
        }
        
        int ans = 0;
        for(int ele : arr){
            ans += ele==8 ? 1 : 0;
        }
        
        if(money!=0) ans--;
        return ans;
    }


    //2592. Maximize Greatness of an Array
    public int maximizeGreatness(int[] nums) {
        int n  = nums.length;
        Arrays.sort(nums);
        int i = 0;
        int j = 1;

        int ans = 0;
        while(j<n){
            if(nums[i]<nums[j]){
                ans++;
                i++;
            }
            j++;
        }

        return ans;
    }


    //2593. Find Score of an Array After Marking All Elements
    public long findScore(int[] nums) {
        long score = 0l;
        int n = nums.length;

        HashSet<Integer>marked = new HashSet<>();
        PriorityQueue<Integer>pq = new PriorityQueue<>((a,b)->{
            if(nums[a]==nums[b]) return a - b;
            else return nums[a] - nums[b];
        });

        for(int i = 0;i<n;i++) pq.add(i);

        while(marked.size()!=n){
            while(pq.size()!=0 && marked.contains(pq.peek())) pq.remove();

            int lidx = pq.remove();
            int lft = lidx-1;
            int rgt = lidx+1;

            score += (long)nums[lidx];
            
            marked.add(lidx);
            if(lft!=-1) marked.add(lft);
            if(rgt!=n) marked.add(rgt);
        }

        return score;
    }


    //2594. Minimum Time to Repair Cars
    public long repairCars(int[] ranks, int cars) {
        int n =  ranks.length;
        long min = 0l;
        long max = 100*(long)cars*(long)cars;

        long ans = 0l;
        while(min<=max){
            long mid = min + (max - min)/2;

            if(isPossible(ranks,cars,mid)){
                ans = mid;
                max = mid-1;
            }else{
                min = mid +1;
            }
        }

        return ans;
    }

    public boolean isPossible(int[]ranks,int cars,long tot){
        int n = ranks.length;
        for(int i = 0 ;i <n ;i ++){
            long r = (long)ranks[i];
            int c = (int)Math.sqrt((tot*1.0)/(r*1.0));
            cars-=c;
            if(cars<=0) return true;
        }

        return false;
    }

    class LC881 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int i = 0;
        int j = n-1;

        int boats = 0;

        while(i<=j){
            if(i==j){
                boats++;
                i++;
                j--;
            }else{
                int sum = people[i] + people[j];
                if(sum<=limit){
                     boats++;
                     i++;
                     j--;
                }else{
                    boats++;
                    j--;
                }
            }
        }

        return  boats;
    }
}
}
