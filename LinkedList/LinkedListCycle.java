package LinkedList;

public class LinkedListCycle {

    // WATCH YT VIDEO ON THIS
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    /*
     * ListNode==null || ListNode.next==null => no cycle
     * else cycle is present
     * 
     * ratio r, (of speeds) => r >= 1
     * 
     * if r==2, slow ptr will not complete any rotation before meeting point
     * 
     * a-> tail length
     * b + c -> cycle length
     * n-> number of rotations before meeting point of slow
     * m-> number of rotations before meeting point of fast
     * 
     * m-1 = mDash -> number of rotations after meeting point till we reach the
     * cycle node
     * of fast
     * 
     */

    public ListNode hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                break;
        }
        return fast;
    }

    public ListNode IntersectionNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode meetingPt = hasCycle(head);
        if (meetingPt == null || meetingPt.next == null)
            return null;

        ListNode fast = meetingPt;
        ListNode slow = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    // only if r==2
    public void AllVariables(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode meetingPt = hasCycle(head);
        if (meetingPt == null || meetingPt.next == null)
            return;

        ListNode cycleNode = IntersectionNode(head);

        ListNode slow = head;
        ListNode fast = meetingPt;

        int A = 0;
        int mDash = 0;
        while (slow != meetingPt) {
            fast = fast.next;
            slow = slow.next;
            A++;
            if (fast == meetingPt)
                mDash++;
        }

        int B = 0;
        int C = 0;
        int M = 0;
        // start from meetingPt and one next step -> to handle the case where only cycle
        // is present
        slow = meetingPt;
        slow = slow.next;
        int cycleLen = 1; // B+C
        while (slow != meetingPt) {
            cycleLen++;
            slow = slow.next;
        }

        if (A != 0 && mDash == 0 && meetingPt == cycleNode) {
            // m==-1 // not possible
            C = 0;
            B = cycleLen;
        } else {
            M = mDash + 1;
            C = A - cycleLen * mDash;
            B = cycleLen - C;

        }

    }
}
