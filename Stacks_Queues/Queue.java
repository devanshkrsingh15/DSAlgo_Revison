public class Queue {
    private int[] arr;
    private int maxCapacity;
    private int numberOfElements;
    private int front;
    private int back; // addition will take place this side

    public Queue() {
        initialize(15);
    }

    protected void initialize(int size) {
        this.arr = new int[size];
        this.maxCapacity = size;
        this.numberOfElements = 0;
        this.front = 0;
        this.front = 0;
    }

    public Queue(int size) {
        initialize(size);
    }

    public int size(){
        return this.numberOfElements;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    public boolean isFull() {
        return this.numberOfElements == this.maxCapacity;
    }

    public void add(int val) throws Exception {
        queueOverFlowExceptions();
        add_(val);
    }

    public int remove() throws Exception {
        queueUnderFlowExceptions();
        int rv = remove_();
        return rv;
    }

    protected int remove_() {
        int rv = arr[this.front];
        this.front = (this.front + 1) % this.maxCapacity;
        return rv;
    }

    public int top(int val) throws Exception {
        queueUnderFlowExceptions();
        int rv = arr[this.front];
        return rv;
    }

    public void display() throws Exception {
        queueUnderFlowExceptions();

        int i = 0;

        while (i < this.numberOfElements) {
            int idx = (this.front + i) % this.maxCapacity;
            i++;
            System.out.print(arr[idx] + " ");
        }
    }

    protected void queueUnderFlowExceptions() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is Empty");
    }

    protected void add_(int val) {
        this.back = (this.back + 1) % this.maxCapacity;
        this.arr[this.back] = val;
    }

    protected void queueOverFlowExceptions() throws Exception {
        if (isFull())
            throw new Exception("Queue Overflow");
    }

}
