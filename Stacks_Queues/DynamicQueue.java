public class DynamicQueue extends Queue {

    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int size) {
        super(size);
    }

    public DynamicQueue(int[] arr) {
        int n = arr.length;
        super.initialize(2 * n);

        for (int ele : arr)
            super.add_(ele);
    }

    public void add(int ele) {
        if (super.isFull()) {
            int[]arr = new int[this.size()];
            int i = 0;
            while (super.isEmpty() == false) {
                arr[i++]=super.remove_();
            }

            super.initialize(2*this.size());

            for(int e:arr){
                super.add_(e);
            }
        }
        super.add_(ele);
    }

}
