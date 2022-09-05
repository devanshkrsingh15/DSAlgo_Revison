package LinkedList;

public class CopyWithRandom {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        makeCopy(head);
        setPointers(head);
        return segregate(head);
    }

    private Node segregate(Node head) {
        Node curr = head;
        Node ncurr = curr.next;

        Node nhead = curr.next;

        while (curr != null) {
            curr.next = ncurr.next;
            ncurr.next = (ncurr.next == null) ? null : ncurr.next.next;

            curr = curr.next;
            ncurr = ncurr.next;
        }

        return nhead;

    }

    private void setPointers(Node head) {
        Node curr = head;

        while (curr != null) {
            Node frw = curr.next.next;
            if (curr.random == null) {
                curr.next.random = null;
            } else {
                curr.next.random = curr.random.next;
            }
            curr = frw;
        }
    }

    private void makeCopy(Node head) {
        Node curr = head;
        while (curr != null) {
            Node frw = curr.next;
            Node node = new Node(curr.val);
            curr.next = node;
            node.next = frw;
            curr = frw;
        }
    }

}
