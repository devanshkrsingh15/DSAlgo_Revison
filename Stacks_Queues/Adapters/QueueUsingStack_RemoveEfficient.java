package Adapters;

import java.util.Stack;

public class QueueUsingStack_RemoveEfficient{
    Stack<Integer>mainStack = new Stack<>();
    Stack<Integer>helperStack = new Stack<>();

    public int size(){
        return mainStack.size();
    }

    public boolean isEmpty(){
        return mainStack.size()==0;
    }

    public void add(int val){
       while(mainStack.size()!=0){
        helperStack.push(mainStack.pop());
       }
       mainStack.push(val);
       while(helperStack.size()!=0) mainStack.add(helperStack.pop());
       helperStack = new Stack<>();

    }

    public int remove(){
        int rv = mainStack.pop();
        return rv;
    }

    public int top(){
        int rv = mainStack.peek();
        return rv;
    }
}
