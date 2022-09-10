package HashMap_Heap;
import java.util.*;
//381. Insert Delete GetRandom O(1) - Duplicates allowed

public class RandomizedCollection {

    HashMap<Integer,HashSet<Integer>>map;
    ArrayList<Integer>list;
    Random rand;

    public RandomizedCollection() {
        map  = new HashMap<>();
        rand = new Random();
        list = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        boolean res =  !map.containsKey(val);
        map.putIfAbsent(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        return res;
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)==false) return false;

        //get index and val for swap and remove
        int cval  = val;
        int cidx = -1;
        for(int e:map.get(val)){
            cidx = e;
            break;
        }

        int lidx  =list.size()-1;
        int lval =list.get(lidx);

        //swap in list
        list.set(lidx,cval);
        list.set(cidx,lval);

        //update the map
        map.get(cval).remove(cidx);
        map.get(lval).remove(lidx);

        map.get(cval).add(lidx);
        map.get(lval).add(cidx);

        //remove the ele
        map.get(cval).remove(lidx);
        list.remove(lidx);
        if(map.get(cval).size()==0)map.remove(cval);


        return true;
    }
    
    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }
}