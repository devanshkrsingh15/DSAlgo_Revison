package LinkedList;

public class DLListNode {
    public int val;
    public  DLListNode prev, next;

    public DLListNode() {
        this.val = 0;
        this.prev = null;
        this.next = null;
    }

    public DLListNode(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }

    public DLListNode AddLeft(DLListNode node, int val) {
        DLListNode lnode = new DLListNode(val);
        node.prev = lnode;
        lnode.next = node;

        return lnode;
    }

    public DLListNode AddRight(DLListNode node, int val) {
        DLListNode rnode = new DLListNode(val);
        node.next = rnode;
        rnode.prev = node;

        return rnode;
    }

}
