public class DynamicStack extends Stacks {

    public DynamicStack() {
        super();
    }

    public DynamicStack(int size) {
        super(size);
    }

    public DynamicStack(int[] arr) throws Exception {
        int n = arr.length;
        super.initialize(2 * n);

        for (int ele : arr)
            super.push(ele);
    }

    @Override
    public void push(int val) throws Exception {
        if (super.isFull()) {
            int n = super.size();
            int[] temp = new int[2 * n];
            int i = n - 1;
            while (super.size() != 0) {
                temp[i--] = super.pop();
            }
            initialize(2 * n);

            for (int ele : temp)
                super.push(ele);
        }

        super.push(val);

    }
}
