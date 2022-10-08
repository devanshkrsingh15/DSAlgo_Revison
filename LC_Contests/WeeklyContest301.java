import java.util.*;

public class WeeklyContest301 {
    //2335. Minimum Amount of Time to Fill Cups 
    public int fillCups(int[] amount) {
        PriorityQueue<int[]>pq = new PriorityQueue<>((a,b)->{
            return b[1] - a[1];
        });

        for(int i = 0; i <3;i++){ if(amount[i]>0)  pq.add(new int[]{i,amount[i]});}

        int ans = 0;
        while(pq.size()>0){
            ans++;
            int[]m = pq.remove();
            m[1]--;
            if(pq.size()>0){
                int[]sm = pq.remove();
                sm[1]--;
                if(sm[1]>0) pq.add(sm);
            }
            if(m[1]>0) pq.add(m);
            
        }
        return ans ;
    }
    //2337. Move Pieces to Obtain a String
    public boolean canChange(String start, String target) {
        int lcount
    }
    
}

//2336. Smallest Number in Infinite Set
class SmallestInfiniteSet {
    TreeSet<Integer>set;
   
    public SmallestInfiniteSet() {
        set = new TreeSet<>();
        for(int i = 1;i<=1000;i++){
            set.add(i);
        }
    }
    
    public int popSmallest() {
        int rv= set.first();
        set.remove(rv);
        return rv;
    }
    
    public void addBack(int num) {
        if(set.contains(num)==false)set.add(num);
    }


    //2337. Move Pieces to Obtain a String
    public boolean canChange(String start, String target) {
      
        int i  = 0;
        int j = 0;
        int n = start.length();

        while(i<n || j<n){
            
            while(i<n && start.charAt(i)=='_') i++;
            while(j<n && target.charAt(j)=='_') j++;

            if(i==n  || j==n) return i==n && j==n;

            if(start.charAt(i) != target.charAt(j)) return false;

            if(start.charAt(i)=='L'){
                if(i<j) return false;
            }else{
                if(i>j) return false;
            }

            i++;
            j++;
        }

        return true;


    }
}
