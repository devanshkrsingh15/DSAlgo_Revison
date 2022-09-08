import java.util.Stack;

public class MinStack {

    Stack<Long>st;
    long min = -(long)1e9;
    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int val) {
        long v = (long)val;
        if(st.size()==0){
            min  =v;
            st.push(0l);
        }else{
            long diff =  v - min;
            st.push(diff);
            if(diff<0){
                min = v;
            }
        }
    }
    
    public void pop() {
        long diff= st.pop();
        if(diff<=0){
            min = min - diff;
        }
    }
    
    public int top() {
        long diff= st.peek();
        if(diff<=0){
            long cval = min;
            return (int)cval;
        }

        return (int)(min + diff);
    }
    
    public int getMin() {
        return (int)min;
    }
}