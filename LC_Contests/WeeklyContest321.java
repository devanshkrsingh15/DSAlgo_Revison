import java.util.*;

public class WeeklyContest321 {

    //2485. Find the Pivot Integer
    public int pivotInteger(int n) {
        int sum =0;
        for(int i =1;i<=n;i++) sum+=i;

        int lsum = 0;
        for(int i =1;i<=n;i++){
            lsum += i;

            if(lsum == i + sum-lsum) return i;
        }


        return -1;

    }

    //2486. Append Characters to String to Make Subsequence
    public int appendCharacters(String s, String t) {
        int n = s.length();
        int m = t.length();
        int i = 0;
        int j = 0;
        
        while(i<n && j<m){
            if(s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else{
                i++;
            }
        }
        
        
        if(j==m) return 0;
        
        return m-j;
    }
   
    
    //2487. Remove Nodes From Linked List
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNodes(ListNode head) {
        if(head==null || head.next==null) return head;

        Stack<ListNode>st = new Stack<>();

        ListNode curr = head;

        while(curr!=null){
            while(st.size()!=0 && st.peek().val<curr.val) st.pop();

            ListNode tmp = curr;
            curr = curr.next;
            tmp.next= null;
            st.push(tmp);
        }

        ListNode nhead = new ListNode(-1);
        ListNode ptr =  nhead;
        while(st.size()!=0){
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


    //2488. Count Subarrays With Median K
    public int countSubarrays(int[] nums, int k) {
        
    }
}