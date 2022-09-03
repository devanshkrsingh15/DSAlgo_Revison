package LinkedList;

public class MultiplicationLL {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    // Multiplication of K LinkedLists
    public static ListNode multiplyKLL(ListNode[] lists) {
        int n = lists.length;
        if (n == 1)
            return lists[0];
        return multiplyKLL_DnC(lists, 0, n - 1);
    }

    public static ListNode multiplyKLL_DnC(ListNode[] lists, int i, int j) {
        if (i == j)
            return lists[i];

        int m = i + (j - i) / 2;

        ListNode left = multiplyKLL_DnC(lists, i, m);
        ListNode right = multiplyKLL_DnC(lists, m + 1, j);

        return multiplyTwoLL(left, right);
    }

    // Multiplication of 2 LinkedLists
    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {

        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;

        ListNode l2_itr = l2;

        while (l2_itr != null) {
            ListNode smallAns = multiplyLLwithDig(l1, l2_itr.val);
            l2_itr = l2_itr.next;

            addLists(smallAns, ptr);
            ptr = ptr.next;
        }

        return reverse(dummy.next);

    }

    private static void addLists(ListNode smallAns, ListNode ptr) {
        int carry = 0;
        ListNode c1 = smallAns;
        ListNode c2 = ptr;

        while (c1 != null || carry != 0) {
            int sum = (c1 == null ? 0 : c1.val) + ( (c2.next != null) ? c2.next.val : 0) + carry;

            int dig = sum % 10;
            carry = sum / 10;

            if (c2.next != null)
                c2.next.val = dig;
            else
                c2.next = new ListNode(dig);

            if (c1 != null)
                c1 = c1.next;
            
             c2 = c2.next;
        }
    }

    private static ListNode multiplyLLwithDig(ListNode l1, int val) {

        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;

        int carry = 0;
        ListNode c1 = l1;

        while (c1 != null || carry != 0) {
            int product = carry + ((c1 != null) ? c1.val : 0) * val;
            int dig = product % 10;
            carry = product / 10;
            ptr.next = new ListNode(dig);
            ptr = ptr.next;

            if (c1 != null) {
                c1 = c1.next;
            }

        }

        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode frw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = frw;
        }

        return prev;
    }

}