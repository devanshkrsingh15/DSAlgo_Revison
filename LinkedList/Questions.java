package LinkedList;

public class Questions {

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

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        // this condition for first node in case of even
        // if second node => while(fast!=null && fast.next!=null)
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;

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

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = middleNode(head);
        ListNode nmid = mid.next;
        mid.next = null;

        nmid = reverseList(nmid);

        ListNode c1 = head;
        ListNode c2 = nmid;

        while (c1 != null && c2 != null) {
            if (c1.val != c2.val)
                return false;
            c1 = c1.next;
            c2 = c2.next;
        }

        return true;
    }

    public void foldList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = middleNode(head);
        ListNode nmid = mid.next;
        mid.next = null;

        nmid = reverseList(nmid);

        ListNode c1 = head;
        ListNode c2 = nmid;

        while (c1 != null && c2 != null) {
            ListNode nc1 = c1.next;
            ListNode nc2 = c2.next;

            c1.next = c2;
            c2.next = nc1;

            c1 = nc1;
            c2 = nc2;

        }

    }

    public void unfoldList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode c1 = head;
        ListNode c2 = head.next;
        ListNode tmp = c2;

        while (c2.next != null && c2.next.next != null) {
            ListNode f1 = c2.next;
            ListNode f2 = c2.next.next;

            c1.next = f1;
            c2.next = f2;

            c1 = f1;
            c2 = f2;
        }

        if (c2.next != null) {
            ListNode f1 = c2.next;
            c1.next = f1;
            c1 = f1;
        }

        c1.next = null;
        c2.next = null;

        ListNode rc2 = reverseList(tmp);
        c1.next = rc2;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return (l2 == null) ? l1 : l2;

        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode prv = dummy;

        while (c1 != null && c2 != null) {

            if (c1.val <= c2.val) {
                prv.next = c1;
                c1 = c1.next;
            } else {
                prv.next = c2;
                c2 = c2.next;
            }

            prv = prv.next;
        }

        prv.next = (c1 != null) ? c1 : c2;
        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = middleNode(head);
        ListNode nmid = mid.next;
        mid.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(nmid);

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n <= 1) {
            return n == 0 ? null : lists[0];
        }

        return mergeKLists_(lists, 0, n - 1);
    }

    private ListNode mergeKLists_(ListNode[] lists, int i, int j) {
        if (i == j)
            return lists[i];

        int m = i + (j - i) / 2;
        ListNode l1 = mergeKLists_(lists, i, m);
        ListNode l2 = mergeKLists_(lists, m + 1, j);

        return mergeTwoLists(l1, l2);
    }

    public ListNode SegregateEvenOdd(ListNode head) {
        // all the even numbers appear before all the odd numbers
        if (head == null || head.next == null)
            return head;

        ListNode odummy = new ListNode(-1);
        ListNode edummy = new ListNode(-1);

        ListNode optr = odummy;
        ListNode eptr = edummy;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                eptr.next = curr;
                eptr = eptr.next;
            } else {
                optr.next = curr;
                optr = optr.next;
            }

            curr = curr.next;
        }
        optr.next = null;
        eptr.next = null;

        eptr.next = odummy.next;

        return edummy.next;
    }

    ListNode th = null;
    ListNode tt = null;

    public int getlength(ListNode node) {
        int l = 0;
        while (node != null) {
            l++;
            node = node.next;
        }

        return l;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        ListNode oh = null;
        ListNode ot = null;

        ListNode curr = head;
        int len = getlength(head);
        while (len >= k) {
            int s = k;
            while (s-- > 0) {
                ListNode frw = curr.next;
                addFirst(curr);
                curr = frw;
            }

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            tt = null;
            th = null;
            len -= k;
        }
        ot.next = curr;
        return oh;
    }

    private void addFirst(ListNode curr) {
        if (th == null) {
            th = curr;
            tt = curr;
        } else {
            curr.next = th;
            th = curr;
        }
    }

    ListNode t_head = null;
    ListNode t_tail = null;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null)
            return head;

        ListNode oh = null;
        ListNode ot = null;

        ListNode curr = head;
        int idx = 1;
        while (curr != null) {
            ListNode frw = curr.next;
            if (idx >= left && idx <= right) {
                addFirst(curr);
            }

            if (idx < left || idx > right) {
                if (oh == null && ot == null) {
                    oh = ot = curr;
                } else {
                    ot.next = curr;
                    ot = curr;
                }
            }

            if (idx == right) {
                if (oh == null && ot == null) {
                    oh = t_head;
                    ot = t_tail;
                } else {
                    ot.next = t_head;
                    ot = t_tail;
                }
            }

            curr = frw;
            idx++;
        }

        ot.next = null;
        return oh;
    }

    // Given a linked list of 0s, 1s and 2s, sort it.
    public ListNode Segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero_dummy = new ListNode(-1);
        ListNode one_dummy = new ListNode(-1);
        ListNode two_dummy = new ListNode(-1);

        ListNode zero_ptr = zero_dummy;
        ListNode one_ptr = one_dummy;
        ListNode two_ptr = two_dummy;

        boolean hasZero = false;
        boolean hasOne = false;
        boolean hasTwo = false;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                hasZero = true;
                zero_ptr.next = curr;
                zero_ptr = zero_ptr.next;
            } else if (curr.val == 1) {
                hasOne = true;
                one_ptr.next = curr;
                one_ptr = one_ptr.next;

            } else if (curr.val == 2) {
                hasTwo = true;
                two_ptr.next = curr;
                two_ptr = two_ptr.next;

            }

            curr = curr.next;

        }
        zero_ptr.next = null;
        one_ptr.next = null;
        two_ptr.next = null;

        ListNode zero_head = zero_dummy.next;
        ListNode one_head = one_dummy.next;
        ListNode two_head = two_dummy.next;

        zero_ptr.next = one_head != null ? one_head : two_head;
        one_ptr.next = two_head;

        if (zero_head != null)
            return zero_head;
        if (one_head != null)
            return one_head;

        return two_head;

    }

   
    public int getVal(ListNode l1) {
        int val = 0;
        while (l1 != null) {
            val = val * 10 + l1.val;
            l1 = l1.next;
        }

        return val;
    }

    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        while (l1 != null && l1.val == 0)
            l1 = l1.next;

        while (l2 != null && l2.val == 0)
            l2 = l2.next;

        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }

        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;

        ListNode rl1 = reverseList(l1);
        ListNode rl2 = reverseList(l2);

        int n = getVal(l1);
        int m = getVal(l2);

        ListNode c1 = null;
        ListNode c2 = null;
        // System.out.println(n + " " + m);
        if (n >= m) {
            c1 = rl1;
            c2 = rl2;
        } else {
            c1 = rl2;
            c2 = rl1;
        }

        int borrow = 0;

        while (c2 != null) {
            int d1 = c1.val;
            int d2 = c2.val;
            // System.out.println(d1 + " " + d2);
            int diff = d1 - d2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            // System.out.println(diff);

            c1.val = diff;
            ptr.next = c1;

            c1 = c1.next;
            c2 = c2.next;
            ptr = ptr.next;
        }

        while (c1 != null) {
            int d1 = c1.val;
            int diff = d1 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            c1.val = diff;
            ptr.next = c1;
            ptr = ptr.next;

            c1 = c1.next;

        }

        ListNode ans = reverseList(dummy.next);

        ListNode temp = ans;

        // remove leading zeros
        while (temp != null && temp.val == 0)
            temp = temp.next;

        if (temp == null)
            return new ListNode(0);

        return temp;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || (head.next == null && n == 1))
            return null;

        ListNode f = head;
        while (n-- > 0)
            f = f.next;
        if (f == null)
            return head.next;
        ListNode s = head;
        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        s.next = s.next.next;

        return head;

    }

}

