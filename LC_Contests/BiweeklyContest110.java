import java.util.*;

public class BiweeklyContest110 {
    // 2806. Account Balance After Rounded Purchase
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i <= 100; i += 10) {
            set.add(i);
        }

        int f = set.floor(purchaseAmount);
        int c = set.ceiling(purchaseAmount);

        if (c - purchaseAmount <= purchaseAmount - f) {
            return 100 - c;
        }

        return 100 - f;
    }

    // 2807. Insert Greatest Common Divisors in Linked List
    public class ListNode {
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

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;

        ListNode a = head;

        while (a != null) {
            ListNode na = a.next;

            ptr.next = a;
            ptr = ptr.next;

            if (na == null)
                break;

            int g = getGCD(a.val, na.val);
            ListNode nnode = new ListNode(g);

            ptr.next = nnode;
            ptr = ptr.next;

            a = na;
        }

        return dummy.next;
    }

    public int getGCD(int a, int b) {
        if (b == 0)
            return a;

        return getGCD(b, a % b);
    }


    //2808. Minimum Seconds to Equalize a Circular Array

    
}
