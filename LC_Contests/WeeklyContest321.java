import java.util.*;

public class WeeklyContest321 {

    // 2485. Find the Pivot Integer
    public int pivotInteger(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++)
            sum += i;

        int lsum = 0;
        for (int i = 1; i <= n; i++) {
            lsum += i;

            if (lsum == i + sum - lsum)
                return i;
        }

        return -1;

    }

    // 2486. Append Characters to String to Make Subsequence
    public int appendCharacters(String s, String t) {
        int n = s.length();
        int m = t.length();
        int i = 0;
        int j = 0;

        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        if (j == m)
            return 0;

        return m - j;
    }

    // 2487. Remove Nodes From Linked List
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNodes(ListNode head) {
        if (head == null || head.next == null)
            return head;

        Stack<ListNode> st = new Stack<>();

        ListNode curr = head;

        while (curr != null) {
            while (st.size() != 0 && st.peek().val < curr.val)
                st.pop();

            ListNode tmp = curr;
            curr = curr.next;
            tmp.next = null;
            st.push(tmp);
        }

        ListNode nhead = new ListNode(-1);
        ListNode ptr = nhead;
        while (st.size() != 0) {
            ptr.next = st.pop();
            ptr = ptr.next;
        }

        return reverseList(nhead.next);

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prv = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode frw = curr.next;
            curr.next = prv;
            prv = curr;

            curr = frw;
        }
        return prv;
    }

    // 2488. Count Subarrays With Median K
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int pos = -1;
        HashMap<Integer,Integer>map= new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                nums[i] = 0;
                pos = i;
            } else if (nums[i] < k) {
                nums[i] = -1;
            } else {
                nums[i] = 1;
            }
        }
        int sum = 0;

        for(int i = pos;i>=0;i--){
            sum+=nums[i];
            map.put(sum,map.getOrDefault(sum,0)+1);
        }

        int ans = 0;
        ans += map.getOrDefault(0,0) +map.getOrDefault(1,0);

        sum = 0;
        for(int i = pos+1;i<n;i++){
            sum+=nums[i];

            /*
            for odd  => left + right = 0;   
            right = sum;
            left = -sum

            for even =>  left + right = 1;   
            right = sum;
            left = 1 - sum
            */ 

            ans += map.getOrDefault(-sum,0) + map.getOrDefault(1-sum,0);
        }

        
        return ans;

    }

    private int calSum(int[] psum, int l, int r) {
        int ans = psum[r] - ((l == 0) ? 0 : psum[l - 1]);
        return (ans == 0 || ans == 1) ? 1 : 0;
    }

    public int[] getPsum(int[] nums, boolean flag) {
        int n = nums.length;
        int[] ret = new int[n];
        int st = (flag) ? 0 : n - 1;
        ret[st] = nums[st];
        for (int i = 1; i < n; i++) {
            int idx = (flag) ? i : n - i - 1;
            int nidx = (flag) ? i - 1 : n - i - 1 + 1;
            ret[idx] = nums[idx] + ret[nidx];
        }

        return ret;
    }

}