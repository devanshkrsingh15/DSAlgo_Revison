import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class questions {

    public int[] NGOR(int[] arr) {
        int n = arr.length;
        int[] ngor = new int[n];
        Arrays.fill(ngor, n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[i] > arr[st.peek()]) {
                ngor[st.pop()] = i;
            }

            st.push(i);
        }

        return ngor;
    }

    public int[] NGOL(int[] arr) {
        int n = arr.length;
        int[] ngol = new int[n];
        Arrays.fill(ngol, -1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[i] > arr[st.peek()]) {
                ngol[st.pop()] = i;
            }

            st.push(i);
        }

        return ngol;
    }

    public int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] nsor = new int[n];
        Arrays.fill(nsor, n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[i] < arr[st.peek()]) {
                nsor[st.pop()] = i;
            }

            st.push(i);
        }

        return nsor;
    }

    public int[] NSOL(int[] arr) {
        int n = arr.length;
        int[] nsol = new int[n];
        Arrays.fill(nsol, n);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[i] < arr[st.peek()]) {
                nsol[st.pop()] = i;
            }

            st.push(i);
        }

        return nsol;
    }

    // Stock span problem (GFG)
    public int[] calculateSpan(int arr[], int n) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;

            }
            st.push(i);
        }

        for (int i = 0; i < n; i++) {
            ans[i] = i - ans[i];
        }

        return ans;
    }

    // 735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int ele : asteroids) {
            if (ele > 0) {
                st.push(ele);
            } else {
                while (st.size() != 0 && st.peek() > 0 && st.peek() < Math.abs(ele))
                    st.pop();
                if (st.size() == 0)
                    st.push(ele);
                else if (st.peek() < 0)
                    st.push(ele);
                else if (st.peek() > 0 && st.peek() == Math.abs(ele))
                    st.pop();
            }

        }
        if (st.size() == 0)
            return new int[0];

        ArrayList<Integer> list = new ArrayList<>();

        while (st.size() != 0)
            list.add(st.pop());

        Collections.reverse(list);

        return connverToArray(list);
    }

    private int[] connverToArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        int i = 0;
        for (int ele : list)
            arr[i++] = ele;
        return arr;
    }

    // 20. Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            else {
                if (st.size() != 0 && isComp(st.peek(), ch))
                    st.pop();
                else
                    st.push(ch);
            }
        }

        return st.size() == 0;
    }

    private boolean isComp(char peek, char ch) {
        if (ch == ')')
            return peek == '(';
        if (ch == '}')
            return peek == '{';

        return peek == '[';
    }

    // 946. Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int i = 0;
        for (int ele : pushed) {
            st.push(ele);

            while (st.size() != 0 && i < popped.length && popped[i] == st.peek()) {
                i++;
                st.pop();
            }
        }

        return st.size() == 0 && i == popped.length;
    }

    // 1021. Remove Outermost Parentheses
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            if (currChar == '(') {
                st.push(i);
                continue;
            }

            if (st.size() != 0 && s.charAt(st.peek()) == '(') {
                if (i - st.peek() >= 2) {
                    if (start.size() == 0) {
                        start.add(st.peek());
                        end.add(i);
                    } else if (start.get(start.size() - 1) > st.peek() && i > end.get(end.size() - 1)) {
                        int lst = start.get(start.size() - 1);
                        start.remove(start.size() - 1);
                        int len = end.get(end.size() - 1);
                        end.remove(end.size() - 1);
                        start.add(st.peek());
                        end.add(i);

                    } else {
                        start.add(st.peek());
                        end.add(i);
                    }

                }
                st.pop();
            }

            if (st.size() == 0 || s.charAt(st.peek()) == ')')
                st.push(i);
        }

        ArrayList<int[]> coordinates = new ArrayList<>();
        for (int i = 0; i < start.size(); i++) {
            int a = start.get(i);
            int b = end.get(i);
            coordinates.add(new int[] { a, b });
        }

        Collections.sort(coordinates, (a, b) -> {
            return a[0] - b[0];
        });

        int lastA = -1;
        int lastB = -1;
        for (int[] c : coordinates) {
            int a = c[0];
            int b = c[1];
            if (lastA == -1 && lastB == -1) {
                sb.append(s.substring(a + 1, b));
                lastA = a;
                lastB = b;
            } else if (lastB < a) {
                sb.append(s.substring(a + 1, b));
                lastA = a;
                lastB = b;
            }

        }
        return sb.toString();
    }

    // 1249. Minimum Remove to Make Valid Parentheses
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                st.push(i);
            else if (ch == ')') {
                if (st.size() != 0 && s.charAt(st.peek()) == '(')
                    st.pop();
                else
                    st.push(i);
            }
        }

        if (st.size() == 0)
            return s;

        HashSet<Integer> hs = new HashSet<>();
        while (st.size() != 0)
            hs.add(st.pop());
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (hs.contains(i) == false) {
                sb.append(ch);
            }
            i++;
        }

        return sb.toString();

    }

    // 32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        int max = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for(int i   = 0;i<n;i++){
            char ch = s.charAt(i);
            if(ch=='(') st.push(i);
            else{
                if(st.peek()!=-1 && s.charAt(st.peek())=='('){
                    st.pop();
                    max = Math.max(max,i-st.peek());
                }else{
                    st.push(i);
                }
            }
        }

        return max;
    }
}