package HashMap_Heap;

import java.util.*;

//380. Insert Delete GetRandom O(1)
public class RandomizedSet {

    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;
    Random rand;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        else {
            map.put(val, list.size());
            list.add(val);

            return true;
        }

    }

    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;

        int li = list.size() - 1;
        int lval = list.get(li);

        int ci = map.get(val);
        int cval = val;

        list.set(li, cval);
        list.set(ci, lval);

        map.put(lval, ci);
        map.remove(val);

        list.remove(list.size() - 1);

        return true;

    }

    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }
}