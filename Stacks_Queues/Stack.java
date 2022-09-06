public class Stack {

    private int[] arr;
    private int size;
    private int topPtr;

    public Stack() {
        initialize(15);
    }

    public Stack(int defSize) {
        initialize(defSize);
    }

    protected void initialize(int defSize) {
        this.arr = new int[defSize];
        this.size = 0;
        this.topPtr = -1;
    }

    public int size() {
        return this.size;
    }

    public void push(int val) throws Exception {
        overFlowStackExceptionHandling();
        this.topPtr++;
        arr[this.topPtr] = val;
        this.size++;

    }

    protected void overFlowStackExceptionHandling() throws Exception {
        if (isFull())
            throw new Exception("Stack is full");
    }

    public boolean isFull() {
        return this.topPtr == this.arr.length - 1;
    }

    public int pop() throws Exception {
        emptyStackExceptionHandling();
        int rv = arr[this.topPtr];
        arr[this.topPtr] = 0;
        this.topPtr--;
        this.size--;
        return rv;
    }

    public boolean isEmpty() {
        return this.topPtr == -1;
    }

    public int top() throws Exception {
        emptyStackExceptionHandling();
        int rv = arr[this.topPtr];
        return rv;
    }

    protected void emptyStackExceptionHandling() throws Exception {
        if (isEmpty())
            throw new Exception("Stack is empty");
    }

}

// throw -> generate exception
// throws -> propagate exception