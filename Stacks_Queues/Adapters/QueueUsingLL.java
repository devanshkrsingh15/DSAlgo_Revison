package Adapters;

import java.util.LinkedList;

public class QueueUsingLL {
    LinkedList<Integer>list = new  LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.size()==0;
    }

    public void add(int val){
        list.addLast(val);
    }

    public int remove(){
        return list.removeFirst();
    }

    public int top(){
        return list.getFirst();
    }


}
