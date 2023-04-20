import java.util.*;

public class WeeklyContest341 {

    // 2643. Row With Maximum Ones
    public int[] rowAndMaximumOnes(int[][] mat) {
        int ridx = -1;
        int cnt = -(int) 1e9;

        for (int i = 0; i < mat.length; i++) {
            int c = 0;
            for (int ele : mat[i]) {
                c += ele;
            }

            if (c > cnt) {
                cnt = c;
                ridx = i;
            }
        }

        // return {idx,cnt}

        return new int[] { ridx, cnt };
    }

    // 2644. Find the Maximum Divisibility Score
    public int maxDivScore(int[] nums, int[] divisors) {
        int n = divisors.length;
        int maxCnt = -1;
        int maxIdx = -1;

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int ele : nums) {
                if (ele % divisors[i] == 0)
                    cnt++;
            }

            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxIdx = divisors[i];
            } else if (cnt == maxCnt) {
                maxIdx = Math.min(maxIdx, divisors[i]);
            }
        }

        return maxIdx;
    }

    // 2645. Minimum Additions to Make Valid String
    public int addMinimum(String word) {
        int n = word.length();
        Stack<Character> st = new Stack<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);

            if (ch == 'a') {
                if (st.size() == 0)
                    st.push(ch);
                else {
                    if (st.peek() == 'a') {
                        ans += 2;
                        st.push('b');
                        st.push('c');
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        ans++;
                        st.push('c');
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        st.push(ch);
                    }
                }
            } else if (ch == 'b') {
                if (st.size() == 0) {
                    ans++;
                    st.push('a');
                    st.push(ch);
                } else {
                    if (st.peek() == 'a') {
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        ans += 2;
                        st.push('c');
                        st.push('a');
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        ans++;
                        st.push('a');
                        st.push(ch);
                    }
                }
            } else {
                if (st.size() == 0) {
                    ans += 2;
                    st.push('a');
                    st.push('b');
                    st.push(ch);
                } else {
                    if (st.peek() == 'a') {
                        ans++;
                        st.push('b');
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        ans += 2;
                        st.push('a');
                        st.push('b');
                        st.push(ch);
                    }
                }
            }
        }

        if (st.size() != 0 && st.peek() == 'a')
            ans += 2;
        if (st.size() != 0 && st.peek() == 'b')
            ans++;

        return ans;

    }
}

//2646. Minimize the Total Price of the Trips
class LC2646 {
  
    int[]freq ;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        freq = new int[n];
        ArrayList<Integer>graph[] = buildGraph(n,edges);
        for(int[]t :trips ){
            int src = t[0];
            int des = t[1];
            freq[src]++;
            dfs(graph,src,des,price,new boolean[n]);
        }    
        int[][]dp = new int[n+1][2];
        for(int[]d:dp) Arrays.fill(d,-1);

        return dfsCost(graph,-1,price,0,0,dp);
    }

    public boolean dfs(ArrayList<Integer>graph[],int src,int des,int[] price,boolean[]vis){
        if(src==des) return true;
        vis[src] = true;

        for(int nbr :graph[src]){
            if(!vis[nbr]){
                boolean res = dfs(graph,nbr,des,price,vis);
                if(res){
                    freq[nbr]++;
                    return true;
                }
            }
        }

        return false;
    }

    public int dfsCost(ArrayList<Integer>graph[],int par,int[]price,int src,int canHalf,int[][]dp){
        if(dp[src][canHalf]!=-1) return dp[src][canHalf];

        int halfPath = (int)1e9;
        int normalPath = (int)1e9;

        if(canHalf==0){
          //2 options
          halfPath= (price[src]/2) * freq[src];
          normalPath = price[src] * freq[src];

          for(int nbr : graph[src]){
            if(nbr!=par){
                halfPath += dfsCost(graph,src,price,nbr,1,dp);
                normalPath += dfsCost(graph,src,price,nbr,0,dp);
            }
        }

        }else{
          normalPath = price[src]* freq[src];
          for(int nbr : graph[src]){
            if(nbr!=par){
                normalPath += dfsCost(graph,src,price,nbr,0,dp);
            }
        }
        }


        return dp[src][canHalf] = Math.min(normalPath,halfPath);
    }

    public ArrayList<Integer>[] buildGraph(int n ,int[][]edges){
        ArrayList<Integer>graph[] = new ArrayList[n];
        for(int i =0;i<n;i++) graph[i] = new ArrayList<>();

        for(int[]ed:edges){
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        return graph;
    }
}
