package LinkedList;

import java.util.HashMap;

public class LFUCache {
    public class Node {
        int key, val, freq;
        Node next = null;
        Node prev = null;

        Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    // (first) Head <-> ...... <-> Tail (last)
    public class DoubleLinkedList {
        Node head = null;
        Node tail = null;
        int len = 0;

        public void AddFist(Node node) {
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.head.prev = node;
                node.next = this.head;
                this.head = node;
            }
            size++;
            len++;
        }

        public void AddLast(Node node) {
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }
            size++;
            len++;
        }

        public void remove(Node node) {
            if (this.head == node) {
                RemoveFirst();
            } else if (this.tail == node) {
                RemoveLast();
            } else {
                Node pnode = node.prev;
                Node nnode = node.next;

                pnode.next = nnode;
                nnode.prev = pnode;

                node.prev = null;
                node.next = null;

                size--;
                len--;
            }
        }

        public void RemoveFirst() {
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                Node nhead = this.head.next;
                nhead.prev = null;
                this.head.next = null;
                this.head = nhead;
            }
            size--;
            len--;

        }

        public void RemoveLast() {
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                Node ntail = this.tail.prev;
                ntail.next = null;
                this.tail.prev = null;
                this.tail = ntail;
            }
            size--;
            len--;
        }

    }

    int size = 0;
    int cap = 0;
    int minF = 0;

    HashMap<Integer, Node> nodeMappping;
    HashMap<Integer, DoubleLinkedList> freqMapping;

    public LFUCache(int capacity) {
        initialize(capacity);
    }

    private void initialize(int capacity) {
        this.nodeMappping = new HashMap<>();
        this.freqMapping = new HashMap<>();
        this.size = 0;
        this.cap = capacity;
    }

    public int get(int key) {
        if (!nodeMappping.containsKey(key))
            return -1;

        Node node = nodeMappping.get(key);
        makeRecent(node);
        return node.val;

    }

    private void makeRecent(Node node) {
        int of = node.freq;
        int nf = of + 1;

        DoubleLinkedList olist = freqMapping.get(of);
        olist.remove(node);

        if (olist.len == 0) {
            freqMapping.remove(of);
            if (minF == of)
                minF++;
        }

        node.freq = nf;
        nodeMappping.put(node.key, node);

        freqMapping.putIfAbsent(nf, new DoubleLinkedList());
        DoubleLinkedList nlist = freqMapping.get(nf);

        nlist.AddLast(node);
    }

    public void put(int key, int value) {
        if (this.cap == 0)
            return;

        if (!nodeMappping.containsKey(key)) {
            if (this.size == this.cap) {
                removeLeastFrequent();
            }

            Node node = new Node(key, value, 1);
            nodeMappping.put(key, node);

            freqMapping.putIfAbsent(1, new DoubleLinkedList());
            DoubleLinkedList list = freqMapping.get(1);
            list.AddLast(node);

            minF = 1;
        } else {
            Node node = nodeMappping.get(key);
            node.val = value;
            makeRecent(node);
        }
    }

    private void removeLeastFrequent() {
        DoubleLinkedList list = freqMapping.get(minF);
        Node n = list.head;
        nodeMappping.remove(n.key);
        list.RemoveFirst();
        if (list.len == 0) {
            freqMapping.remove(minF);
        }
    }
}
