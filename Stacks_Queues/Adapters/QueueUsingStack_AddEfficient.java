package Adapters;

import java.util.Stack;

public class QueueUsingStack_AddEfficient{
    Stack<Integer>mainStack = new Stack<>();
    Stack<Integer>helperStack = new Stack<>();

    public int size(){
        return mainStack.size();
    }

    public boolean isEmpty(){
        return mainStack.size()==0;
    }

    public void add(int val){
        mainStack.push(val);
    }

    public int remove(){
        while(mainStack.size()>1){
            helperStack.push(mainStack.pop());
        }

        int rv = mainStack.pop();
        while(helperStack.size()!=0){
            mainStack.push(helperStack.pop());
        }
        helperStack = new Stack<>();
        return rv;
    }

    public int top(){
        while(mainStack.size()>1){
            helperStack.push(mainStack.pop());
        }

        int rv = mainStack.peek();
        while(helperStack.size()!=0){
            mainStack.push(helperStack.pop());
        }
        helperStack = new Stack<>();
        return rv;
    }
}
