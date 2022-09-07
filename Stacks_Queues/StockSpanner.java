import java.util.Stack;

public class StockSpanner {
   
    Stack<int[]>st; //{span,price}
    public StockSpanner() {
        st = new Stack<>();
        st.push(new int[]{-1,0});
    }
    
    
    public int next(int price) {
        int span =1;
        
        while(st.peek()[0]!=-1 && st.peek()[1]<=price){
            span+=st.pop()[0];
        }
        
        st.push(new int[]{span,price});
        return span;
    }
}
