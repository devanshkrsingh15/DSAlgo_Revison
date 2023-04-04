import java.util.*;

public class WeeklyContest339 {
    //2609. Find the Longest Balanced Substring of a Binary String
    public int findTheLongestBalancedSubstring(String s) {
        int ans = 0;
        int n = s.length();
        int ei = 0;

        TreeSet<Integer>set = new TreeSet<>();
        for(int i = 0;i<n;i++){
            if(s.charAt(i)=='0') set.add(i);
        }
        set.add(n);

        while(ei<n){
            if(s.charAt(ei)=='0'){
                int cnt = 0;
                int tmp = ei;
                while(tmp<n && s.charAt(tmp)=='0'){
                    cnt++;
                    tmp++;
                }

                if(tmp==n) return ans;
            
                int nextidx = set.ceiling(tmp);
                int zeros = cnt;
                int ones = nextidx - tmp;
                ans = Math.max(ans,2*Math.min(zeros,ones));
                

                ei = nextidx;
            }else{
                ei++;
            }
        }

        return ans;
    }

    //2610. Convert an Array Into a 2D Array With Conditions
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>>ans = new ArrayList<>();

        HashMap<Integer,Integer>map = new HashMap<>();
        for(int ele : nums){
            map.put(ele,map.getOrDefault(ele,0)+1);
        }

        int size = 0;
        HashMap<Integer,ArrayList<Integer>>freq = new HashMap<>();
        for(int k : map.keySet()){
            size=Math.max(map.get(k),size);
            freq.putIfAbsent(map.get(k), new ArrayList<>());
            freq.get(map.get(k)).add(k);
        }

        for(int i = 0;i<size;i++){
            ans.add(new ArrayList<>());
        }

        for(int i= 1;i<=size;i++){
            if(freq.containsKey(i)){
                for(int ele: freq.get(i)){
                    for(int r = 0;r<map.get(ele);r++){
                        ans.get(r).add(ele);
                    }
                }
            }
        }

        return ans;
    }
}
