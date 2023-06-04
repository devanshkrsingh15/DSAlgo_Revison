import java.util.*;

public class WeeklyContest346 {

    // 2696. Minimum String Length After Removing Substrings
    public int minLength(String s) {
        Stack<Integer> st = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {

            if (st.size() != 0 && (check(s.charAt(st.peek()), s.charAt(i)))) {
                st.pop();
            } else {
                st.push(i);
            }
        }

        return st.size();
    }

    private boolean check(char a, char b) {
        return (a == 'A' && b == 'B') || (a == 'C' && b == 'D');
    }

    // 2697. Lexicographically Smallest Palindrome
    public String makeSmallestPalindrome(String s) {
        if (isPal(s))
            return s;
        int n = s.length();
        char arr[] = new char[n];
        Arrays.fill(arr, '*');
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                arr[i] = s.charAt(i);
                arr[n - i - 1] = s.charAt(i);
            } else {
                char min = (char) Math.min(s.charAt(i), s.charAt(n - i - 1));
                arr[i] = min;
                arr[n - i - 1] = min;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (arr[i] == '*')
                sb.append(s.charAt(i));
            else
                sb.append(arr[i]);
        }

        return sb.toString();
    }

    public boolean isPal(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;

        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    // 2698. Find the Punishment Number of an Integer
    public int punishmentNumber(int n) {
        boolean isPossiblePartition[] = getPartitionArray(n);
        long ans = 0l;
        for (int i = 1; i <= n; i++) {
            if (isPossiblePartition[i])
                ans += (long) i * i;
        }

        return (int) ans;
    }

    private boolean[] getPartitionArray(int n) {
        boolean[] arr = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            Boolean[][] dp = new Boolean[10][i + 1];
            arr[i] = check(i * i + "", 0, i, dp);
        }

        return arr;
    }

    public boolean check(String tmp, int idx, int tar, Boolean[][] dp) {
        if (idx == tmp.length()) {
            return tar == 0 ? true : false;
        }

        if (dp[idx][tar] != null)
            return dp[idx][tar];

        boolean res = false;
        for (int i = idx; i < tmp.length(); i++) {
            int num = Integer.parseInt(tmp.substring(idx, i + 1));
            if (tar - num >= 0)
                res = res || check(tmp, i + 1, num, dp);
        }

        return dp[idx][tar] = res;
    }

    // 2699. Modify Graph Edge Weights
    class Solution {
        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
            ArrayList<int[]> graph[] = new ArrayList[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0];
                int v = edges[i][1];
                int w = edges[i][2];
                graph[u].add(new int[] { v, w });
                graph[v].add(new int[] { u, w });

                String key = Math.max(u, v) + "/" + Math.min(u, v);
                map.put(key, i);
            }
            int[] disR = new int[n];
            int[] parR = new int[n];
            dijkstra(graph, destination, source, disR, parR, false); // back , right to left
            if (disR[source] < target)
                return new int[0][]; // min path without the neg value is less than tar

            int[] dis = new int[n];
            int[] par = new int[n];
            dijkstra(graph, source, destination, dis, par, true); // forward , left to right
            if (dis[destination] > target)
                return new int[0][]; // min path considering the min value is greater than tar

            ArrayList<int[]> path = getPath(par, destination, source);

            HashMap<Integer, Integer> assinedWt = new HashMap<>();
            int wsof = 0;
            for (int[] ed : path) {
                int u = ed[0];
                int v = ed[1];
                String key = Math.max(u, v) + "/" + Math.min(u, v);

                int wt = edges[map.get(key)][2];

                if (wt == -1) {
                    int diff = target - wsof - disR[v];
                    diff = Math.max(diff, 1);
                    wt = diff;
                    assinedWt.put(map.get(key), wt);
                }
                wsof += wt;
            }

            for (int i = 0; i < edges.length; i++) {
                if (edges[i][2] == -1) {
                    edges[i][2] = assinedWt.containsKey(i) ? assinedWt.get(i) : 2 * (int) 1e9;
                }
            }

            return edges;

        }

        public ArrayList<int[]> getPath(int[] par, int des, int src) {
            ArrayList<int[]> path = new ArrayList<>();
            int vtx = des;

            while (par[vtx] != -1) {
                int u = par[vtx];
                int v = vtx;
                path.add(new int[] { u, v });
                vtx = u;
            }

            Collections.reverse(path);
            return path;
        }

        public void dijkstra(ArrayList<int[]> graph[], int src, int des, int[] dis, int[] par, boolean consaiderNeg) {
            // idx,wt
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                return a[1] - b[1];
            });
            Arrays.fill(par, -1);
            Arrays.fill(dis, (int) 1e9);
            pq.add(new int[] { src, 0 });
            dis[src] = 0;
            par[src] = -1;

            while (pq.size() != 0) {
                int s = pq.size();
                while (s-- > 0) {
                    int[] rem = pq.remove();
                    int id = rem[0];
                    int wsof = rem[1];
                    if (dis[id] < wsof)
                        continue;

                    if (id == des)
                        return;

                    for (int[] ed : graph[id]) {
                        int nbr = ed[0];
                        int wt = ed[1];
                        if (wt == -1) {
                            if (consaiderNeg)
                                wt = 1;
                            else
                                continue;
                        }

                        if (dis[nbr] > wsof + wt) {
                            dis[nbr] = wsof + wt;
                            par[nbr] = id;
                            pq.add(new int[] { nbr, wsof + wt });
                        }
                    }
                }
            }
        }
    }

}