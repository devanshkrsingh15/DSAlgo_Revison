package HashMap_Heap;

import java.util.*;

public class MaxFreqStack {

    HashMap<Integer, Integer> fmap;
    HashMap<Integer, Stack<Integer>> smap;
    int max;

    public MaxFreqStack() {
        fmap = new HashMap<>();
        smap = new HashMap<>();
        max = 0;
    }

    public void push(int val) {
        fmap.put(val, fmap.getOrDefault(val, 0) + 1);
        max = Math.max(max, fmap.get(val));

        smap.putIfAbsent(fmap.get(val), new Stack<>());
        smap.get(fmap.get(val)).add(val);
    }

    public int pop() {
        int rv = smap.get(max).pop();
        if (smap.get(max).size() == 0) {
            smap.remove(max);
            max--;
        }

        fmap.put(rv, fmap.get(rv) - 1);
        if (fmap.get(rv) == 0)
            fmap.remove(rv);
        return rv;
    }
}