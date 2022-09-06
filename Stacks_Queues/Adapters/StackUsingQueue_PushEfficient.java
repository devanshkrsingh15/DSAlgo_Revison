package Adapters; 
import java.util.ArrayDeque;
import java.util.Queue;

public class StackUsingQueue_PushEfficient {
    Queue<Integer>mainQ = new ArrayDeque<>();
    Queue<Integer>helperQ = new ArrayDeque<>();

    public int size(){
        return mainQ.size();
    }

    public boolean isEmpty(){
        return mainQ.size()==0;
    }

    public void push(int val){
        mainQ.add(val);
    }

    public int pop(int val){
        while(mainQ.size()>1){
            helperQ.add(mainQ.remove());
        }
        int  rv = mainQ.remove();
        while(helperQ.size()>0){
            mainQ.add(helperQ.remove());
        }
        helperQ = new ArrayDeque<>();
        return rv;
    }

    public int peek(int val){
        while(mainQ.size()>1){
            helperQ.add(mainQ.remove());
        }
        int  rv = mainQ.peek();
        helperQ.add(mainQ.remove());
        while(helperQ.size()>0){
            mainQ.add(helperQ.remove());
        }
        helperQ = new ArrayDeque<>();
        return rv;
    }
}
