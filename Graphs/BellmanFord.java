import java.util.Arrays;

public class BellmanFord {
    /*
     * Similar to Dijkrstra, min wt path
     * Used to detect -ve cycle
     * If there is any update at nth iteration, where n is the number of vtx
     */

    public void Algo(int n, Edge[] edges, int src) {
        int[] dp = new int[n]; // store min wt to reach ith node
        Arrays.fill(dp, (int) 1e9);
        dp[src] = 0; // 0th iteration done
        boolean negCycle = false;

        for (int i = 1; i <= n; i++) {
            int[] ndp = copy(dp);

            for (Edge e : edges) {
                int u = e.u;
                int v = e.v;
                int wt = e.wt;

                if (ndp[v] > dp[u] + wt) {
                    ndp[v] = dp[u] + wt;

                    if (i == n)
                        negCycle = true;
                }
            }

            dp = ndp;

        }

    }

    private int[] copy(int[] dp) {
        int n = dp.length;
        int[] ndp = new int[n];
        for (int i = 0; i < n; i++)
            ndp[i] = dp[i];
        return ndp;
    }

    /*
     * 
     * int v = number of vertex
     * if(vth trans dp state is updating){
     * => -ve cycle
     * }else{
     * if( check kahn algo){
     * => cycle
     * }else{
     * => no cycle
     * }
     * }
     * 
     * 
     * 
     */

}