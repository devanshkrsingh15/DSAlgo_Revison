import java.util.*;

public class WeeklyContest322 {
    // 2490. Circular Sentence
    public boolean isCircularSentence(String sentence) {
        String[] arr = sentence.split(" ");

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int currIdx = i;
            int nextIdx = (i == n - 1) ? 0 : i + 1;

            if (arr[currIdx].charAt(arr[currIdx].length() - 1) != arr[nextIdx].charAt(0))
                return false;
        }

        return true;
    }

    // 2491. Divide Players Into Teams of Equal Skill
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        long sof = 0;
        long max = 0;

        for (int s : skill) {
            long sk = (long) s;
            sof += sk;
            max = Math.max(max, sk);
        }
        long te = (long) (n / 2);
        if (sof % te != 0 || sof / te <= max)
            return -1l;

        long tar = sof / te;
        Arrays.sort(skill);

        int lo = 0;
        int hi = n - 1;
        long ans = 0;
        while (lo < hi) {
            long a = (long) skill[lo];
            long b = (long) skill[hi];

            if (a + b == tar) {
                ans += (long) a * b;
                lo++;
                hi--;
            } else {
                return -1;
            }

        }
        return ans;

    }

    // 2492. Minimum Score of a Path Between Two Cities
    public int minScore(int n, int[][] roads) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Edge>[] graph = buildGraph(n, roads, map);
        boolean[] vis = new boolean[n + 1];
        ArrayList<Integer> list = new ArrayList<>();

        dfs(graph, 1, vis, list);
        if (n == 1)
            return 0;

        int min = (int) 1e9;

        for (int ele : list)
            min = Math.min(min, map.get(ele));

        return min;
    }

    private void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> list) {
        vis[src] = true;
        list.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {

                dfs(graph, e.nbr, vis, list);
            }
        }
    }

    class Edge {
        int nbr;
        int cost;

        Edge(int nbr, int cost) {
            this.nbr = nbr;
            this.cost = cost;
        }

    }

    public ArrayList<Edge>[] buildGraph(int n, int[][] roads, HashMap<Integer, Integer> map) {
        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int w = r[2];

            map.putIfAbsent(u, w);
            map.putIfAbsent(v, w);

            if (w < map.get(u))
                map.put(u, w);
            if (w < map.get(v))
                map.put(v, w);

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        return graph;
    }
}