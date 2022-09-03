package LinkedList;

public class QuickSort {

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

    public ListNode QuickSort(ListNode head) {
        return QuickSort_(head)[0]; // {head and tail of sorted list}
    }

    private ListNode[] QuickSort_(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLength(head);
        int pivotIdx = len / 2;

        ListNode[] segList = segregate(head, pivotIdx); // {head node,pivot node,after pivot node}
        ListNode lhead = segList[0], rhead = segList[3];
        ListNode phead = segList[1], ptail = segList[2];

        ListNode left[] = QuickSort_(lhead);
        ListNode right[] = QuickSort_(rhead);

        return mergeLists(left, new ListNode[] { phead, ptail }, right);

    }

    private ListNode[] mergeLists(ListNode[] left, ListNode pvt[], ListNode[] right) {
        ListNode lhead = left[0];
        ListNode ltail = left[1];

        ListNode rhead = right[0];
        ListNode rtail = right[1];

        ListNode phead = pvt[0];
        ListNode ptail = pvt[1];

        ListNode ohead = null;
        ListNode otail = null;

        if (lhead == null && phead == null && rhead == null) {

        } else if (lhead == null && phead == null && rhead != null) {
            ohead = rhead;
            otail = rtail;

        } else if (lhead == null && phead != null && rhead == null) {
            ohead = phead;
            otail = ptail;

        } else if (lhead == null && phead != null && rhead != null) {
            ohead = phead;
            otail = rtail;

            ptail.next = rhead;

        } else if (lhead != null && phead == null && rhead == null) {
            ohead = lhead;
            otail = ltail;

        } else if (lhead != null && phead == null && rhead != null) {
            ohead = lhead;
            otail = rtail;

            ltail.next = rhead;

        } else if (lhead != null && phead != null && rhead == null) {
            ohead = lhead;
            otail = ptail;

            ltail.next = phead;

        } else if (lhead != null && phead != null && rhead != null) {
            ohead = lhead;
            otail = rtail;

            ltail.next = phead;
            ptail.next = rhead;
        }

        return new ListNode[] { ohead, otail };
    }

    private ListNode[] segregate(ListNode head, int pivotIdx) {
        int idx = 0;
        ListNode pivotNode = head;
        while (idx < pivotIdx) {
            idx++;
            pivotNode = pivotNode.next;
        }

        ListNode sdummy = new ListNode(-1);
        ListNode ldummy = new ListNode(-1);
        ListNode pdummy = new ListNode(-1);

        ListNode sp = sdummy;
        ListNode lp = ldummy;
        ListNode pp = pdummy;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val < pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else if (curr.val == pivotNode.val) {
                pp.next = curr;
                pp = pp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }

            curr = curr.next;
        }

        sp.next = null;
        lp.next = null;
        pp.next = null;

        ListNode shead = sdummy.next;
        ListNode lhead = ldummy.next;
        ListNode phead = pdummy.next;
        ListNode ptail = pp;

        return new ListNode[] { shead, phead, ptail, lhead };
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

    public ListNode sortList(ListNode head) {
        return QuickSort(head);
    }

}