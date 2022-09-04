package LinkedList;

public class DoublyLinkedList {
    public class DoublyLinkedListNode {
        int val;
        DoublyLinkedListNode next, prev;

        DoublyLinkedListNode() {
        }

        DoublyLinkedListNode(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    DoublyLinkedListNode head = null;
    DoublyLinkedListNode tail = null;
    int size = 0;

    DoublyLinkedList() {
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
        DoublyLinkedListNode node = new DoublyLinkedListNode(val);
        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size++;

    }

    public void addLast(int val) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(val);
        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
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

        DoublyLinkedListNode pnode = getAt(idx - 1);
        DoublyLinkedListNode nnode = pnode.next;

        DoublyLinkedListNode node = new DoublyLinkedListNode(val);
        pnode.next = node;
        node.prev = pnode;
        node.next = nnode;
        nnode.prev = node;
        this.size++;
    }

    public void deleteFirst() {
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            DoublyLinkedListNode nhead = this.head.next;
            this.head.next = null;
            nhead.prev = null;
            this.head = nhead;
        }
        this.size--;

    }

    public void deleteLast() {
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            DoublyLinkedListNode ntail = this.tail.prev;
            this.tail.prev = null;
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

        DoublyLinkedListNode node = getAt(idx);
        DoublyLinkedListNode pnode = node.prev;
        DoublyLinkedListNode nnode = node.next;

        pnode.next = nnode;
        nnode.prev = pnode;
        node.next = null;
        node.prev = null;

        this.size--;

    }

    public void updateFirst(int val) {
        this.head.val = val;

    }

    public void updateLast(int val) {
        this.tail.val = val;
    }

    public void updateAt(int val, int idx) {
        DoublyLinkedListNode node = getAt(idx);
        node.val = val;
    }

    public DoublyLinkedListNode getFirst() {
        return this.head;
    }

    public DoublyLinkedListNode getLast() {
        return this.tail;
    }

    public DoublyLinkedListNode getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return null;

        DoublyLinkedListNode node = this.head;
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
            DoublyLinkedListNode node = getAt(i);
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

    public void displayBackward() {
        // tail to head
        for (int i = this.size - 1; i >= 0; i--) {
            DoublyLinkedListNode node = getAt(i);
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

}
