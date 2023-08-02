import java.util.*;

public class WeeklyContest356 {

    // 2798. Number of Employees Who Met the Target
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (int ele : hours) {
            if (ele >= target)
                ans++;
        }

        return ans;
    }

    // 2799. Count Complete Subarrays in an Array
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> mainSet = new HashSet<>();
        for (int ele : nums)
            mainSet.add(ele);

        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() == mainSet.size()) {
                    ans += (n - j);
                    break;
                }
            }
        }

        return ans;
    }

    // 2800. Shortest String That Contains Three Strings

    ArrayList<String>fans = new ArrayList<>();
    public String minimumString(String a, String b, String c) {
         ArrayList<String>list = new ArrayList<>();
         list.add(a);
         list.add(b);
         list.add(c);
 
         helper_(list, "", 0);
         Collections.sort(fans,(A,B)->{
             if(A.length()!=B.length()) return A.length() - B.length();
             else{
                 return A.compareTo(B);
             }
         });
 
         return fans.get(0);
     }
     
     public void helper_(ArrayList<String>list, String ans, int vis) {
         if (ans.length() == list.size()) {
             String mstr = getMergedString(ans, list);
             fans.add(mstr);
         }
 
         for (int i = 0; i < list.size(); i++) {
             if ((vis & (1 << i)) == 0) {
                 vis ^= (1 << i);
                 helper_(list, ans + "" + i, vis);
                 vis ^= (1 << i);
             }
         }
     }
 
     private String getMergedString(String ans, ArrayList<String>list) {
         StringBuilder sb = new StringBuilder("");
         int idx = 0;
         while (idx < ans.length()) {
             char ch = ans.charAt(idx);
             String next = list.get(Integer.parseInt(ch + ""));
             merge(sb,next);
             idx++;
         }
 
         return sb.toString();
     }
 
     public void merge(StringBuilder curr, String next) {
         if(curr.length()==0){
             curr.append(next);
             return;
         }else if(curr.toString().contains(next)){
             return;
         }
         int n = next.length();
         int st = -1;
 
         for (int i = n - 1; i >= 0; i--) {
             String part = next.substring(0, i + 1);
             if (curr.length() - part.length() >= 0 && curr.substring(curr.length() - part.length()).equals(part)) {
                 st = i + 1;
                 break;
             }
         }
 
         if(st==-1){
             curr.append(next);
         }else if(st<n){
             curr.append(next.substring(st));
         }
 
     }

}