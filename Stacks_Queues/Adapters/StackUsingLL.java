package Adapters;

import java.util.LinkedList;

public class StackUsingLL{
    LinkedList<Integer>list = new  LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.size()==0;
    }

    public void push(int val){
        list.addLast(val);
    }

    public int peek(){
        return list.removeLast();
    }

    public int pop(){
        return list.getLast();
    }

}