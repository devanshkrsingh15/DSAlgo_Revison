package HashMap_Heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

//703. Kth Largest Element in a Stream
public class KthLargest {
    int K;
    PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        K = k;

        for (int ele : nums) {
            add(ele);
        }
    }

    public int add(int val) {
        pq.add(val);
        if (pq.size() > K)
            pq.remove();
        return pq.peek();
    }

   

}
