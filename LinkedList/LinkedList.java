package LinkedList;

public class LinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    ListNode head = null;
    ListNode tail = null;
    int size = 0;

    LinkedList() {
        initialize();
    }

    public void initialize() {
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void addFirst(int val) {
        ListNode node = new ListNode(val);
        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;

    }

    public void addLast(int val) {
        ListNode node = new ListNode(val);
        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addAt(int val, int idx) {
        if (idx < 0 || idx >= this.size)
            return;

        if (idx == 0) {
            addFirst(val);
            return;
        }
        if (idx == size - 1) {
            addLast(val);
            return;
        }

        ListNode pnode = getAt(idx - 1);
        ListNode nnode = pnode.next;

        ListNode node = new ListNode(val);
        pnode.next = node;
        node.next = nnode;

        this.size++;
    }

    public void deleteFirst() {
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            ListNode nhead = this.head.next;
            this.head.next = null;
            this.head = nhead;
        }
        this.size--;

    }

    public void deleteLast() {
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            ListNode ntail = getAt(this.size - 2);
            ntail.next = null;
            this.tail = ntail;
        }
        this.size--;
    }

    public void deleteAt(int val, int idx) {
        if (idx < 0 || idx >= this.size)
            return;

        if (idx == 0) {
            deleteFirst();
            return;
        }
        if (idx == size - 1) {
            deleteLast();
            return;
        }

        ListNode pnode = getAt(idx - 1);
        ListNode node = getAt(idx);
        ListNode nnode = node.next;

        pnode.next = nnode;
        node.next = null;

        this.size--;

    }

    public void updateFirst(int val) {
        this.head.val = val;

    }

    public void updateLast(int val) {
        this.tail.val = val;
    }

    public void updateAt(int val, int idx) {
        ListNode node = getAt(idx);
        node.val = val;
    }

    public ListNode getFirst() {
        return this.head;
    }

    public ListNode getLast() {
        return this.tail;
    }

    public ListNode getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return null;

        ListNode node = this.head;
        int pos = 0;
        while (pos < idx) {
            node = node.next;
            pos++;
        }

        return node;
    }

    public void displayForward() {
        // head to tail
        for (int i = 0; i < this.size; i++) {
            ListNode node = getAt(i);
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

    public void displayBackward() {
        // tail to head
        for (int i = this.size - 1; i >= 0; i--) {
            ListNode node = getAt(i);
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

}
