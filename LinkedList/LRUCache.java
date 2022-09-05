package LinkedList;

import java.util.HashMap;

public class LRUCache {
    public class Node {
        int key;
        int val;
        Node next = null;
        Node prev = null;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = null;
    Node tail = null;
    int size = 0;
    int cap = 0;

    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        intialize(capacity);
    }

    private void intialize(int capacity) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.cap = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        makeRecent(node);
        return node.val;
    }

    private void makeRecent(Node node) {
        if (this.tail == node)
            return;
        remove(node);
        addLast(node);
    }

    private void addLast(Node node) {
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    private void remove(Node node) {
        if (this.head == node) {
            removeHead();
            return;
        } else if (this.tail == node) {
            removeTail();
            return;
        } else {
            Node pnode = node.prev;
            Node nnode = node.next;

            pnode.next = nnode;
            nnode.prev = pnode;

            node.prev = null;
            node.next = null;
        }
        this.size--;
    }

    private void removeHead() {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node node = this.head.next;
            this.head.next = null;
            node.prev = null;
            this.head = node;
        }
        this.size--;
    }

    private void removeTail() {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node node = this.tail.prev;
            this.tail.prev = null;
            node.next = null;
            this.tail = node;
        }
        this.size--;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (this.size == this.cap) {
                Node node = this.head;
                removeHead();
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addLast(node);
            this.size++;
        } else {
            Node node = map.get(key);
            node.val = value;
            makeRecent(node);
        }
    }
}
