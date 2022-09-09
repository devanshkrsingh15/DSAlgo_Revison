package HashMap_Heap;

import java.util.ArrayList;

public class Heap {
    ArrayList<Integer> list;
    boolean isMaxHeap;

    private void initialize(boolean isMaxHeap) {
        this.list = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }

    Heap() {
        initialize(true);
    }

    Heap(boolean isMaxHeap) {
        initialize(isMaxHeap);
    }

    Heap(int[] arr, boolean isMaxHeap) {
        initialize(isMaxHeap);
        for (int ele : arr)
            this.list.add(ele);
        construct();
    }

    private void construct() {
        int n = this.list.size();
        for (int i = n - 1; i >= 0; i--)
            downHeapify(i);
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.size() == 0;
    }

    public void add(int ele) {
        this.list.add(ele);
        int n = this.list.size();
        upHeapify(n - 1);
    }

    public int top() {
        return this.list.get(0);
    }

    public int remove() {
        int rv = this.list.get(0);
        int n = this.list.size();
        swap(0, n - 1);

        this.list.remove(n - 1);
        downHeapify(0);
        return rv;
    }

    private void upHeapify(int i) {
        int pi = (i-1)/2;
        if(isValidIndex(pi) && comparator(pi,i)){
            swap(i,pi);
            upHeapify(pi);
        }
    }

    private void downHeapify(int pi) {
        int maxi = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (isValidIndex(lci) && comparator(maxi, lci)) {
            maxi = lci;
        }

        if (isValidIndex(rci) && comparator(maxi, rci)) {
            maxi = rci;
        }

        if (maxi != pi) {
            swap(maxi, pi);
            downHeapify(maxi);
        }

    }

    private boolean comparator(int pi, int ci) {
        if (isMaxHeap) {
            return this.list.get(ci) > this.list.get(pi);
        } else {
            return this.list.get(ci) < this.list.get(pi);
        }
    }

    private boolean isValidIndex(int i) {
        return i >= 0 && i < this.list.size();
    }

    private void swap(int i, int j) {
        int temp = this.list.get(i);
        this.list.set(i, this.list.get(j));
        this.list.set(j, temp);
    }

}