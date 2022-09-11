package HashMap_Heap;

import java.util.*;

public class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        right = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
    }

    public void addNum(int num) {
        if (left.size() == 0 || num <= left.peek())
            left.add(num);
        else
            right.add(num);

        if (left.size() - right.size() == 2) {
            right.add(left.remove());
        } else if (left.size() - right.size() == -1) {
            left.add(right.remove());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            int a = left.peek();
            int b = right.peek();
            return (double) (a + b) / 2.0;
        } else {
            return (double) left.peek();
        }
    }
}
