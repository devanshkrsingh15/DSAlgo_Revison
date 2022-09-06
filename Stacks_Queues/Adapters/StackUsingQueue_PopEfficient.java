package Adapters; 
import java.util.ArrayDeque;
import java.util.Queue;

public class StackUsingQueue_PopEfficient {
    Queue<Integer>mainQ = new ArrayDeque<>();
    Queue<Integer>helperQ = new ArrayDeque<>();

    public int size(){
        return mainQ.size();
    }

    public boolean isEmpty(){
        return mainQ.size()==0;
    }

    public void push(int val){
        while(mainQ.size()!=0) helperQ.add(mainQ.remove());
        mainQ.add(val);

        while(helperQ.size()!=0) mainQ.add(helperQ.remove());

        helperQ =   new ArrayDeque<>(); 
    }

    public int pop(int val){
        int rv = mainQ.remove();
        return rv;
    }

    public int peek(int val){
        int rv = mainQ.peek();
        return rv;
    }
}
