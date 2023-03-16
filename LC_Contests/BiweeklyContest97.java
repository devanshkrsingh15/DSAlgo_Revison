import java.util.*;

public class BiweeklyContest97 {
    //2553. Separate the Digits in an Array
    public int[] separateDigits(int[] nums) {
        ArrayList<Integer>list = new ArrayList<>();
        for(int ele : nums){
            ArrayList<Integer>arr = getDigits(ele);
            for(int d :arr ){
                list.add(d);
            }
        }
        
        int[]ans = new int[list.size()];
        int idx = 0;
        for(int ele :list ){
            ans[idx++]= ele;
        }
        return ans;
        
    }
    
    public ArrayList<Integer> getDigits(int ele){
        ArrayList<Integer>list = new ArrayList<>();
        while(ele!=0){
            list.add(ele%10);
            ele/=10;
        }
        Collections.reverse(list);
        return list;
    }
    //2554. Maximum Number of Integers to Choose From a Range I
    public int maxCount(int[] banned, int n, int maxSum) {
        int sum = 0;
        HashSet<Integer>hs= new HashSet<>();
        for(int ele : banned) hs.add(ele);
        
        int st =1;
        int cnt = 0;
        while(st<=n && sum+st<=maxSum ){
            if(!hs.contains(st)){
                sum+=st;
                cnt++;
                st+=1;
            }
            
            if(hs.contains(st)) st++;
           
        }
        
        return cnt;
    }
}
