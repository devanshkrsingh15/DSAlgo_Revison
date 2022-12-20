import java.util.*;

public class WeeklyContest324 {
    // 2506. Count Pairs Of Similar Strings
    public int similarPairs(String[] words) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : words) {
            int mask = getMask(s);
            map.put(mask, map.getOrDefault(mask, 0) + 1);
        }

        int ans = 0;
        for (int k : map.keySet()) {
            int v = map.get(k);
            ans += v * (v - 1);
        }

        return ans / 2;
    }

    private int getMask(String s) {
        int ans = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int k = s.charAt(i) - 'a';

            if ((ans & (1 << k)) == 0) {
                ans |= (1 << k);
            }
        }

        return ans;
    }

    // 2507. Smallest Value After Replacing With Sum of Prime Factors
    public int smallestValue(int n) {
        boolean[] isPrime = new boolean[n + 1];
        ArrayList<Integer> list = getPrimeFactors(n, isPrime);

        if (isPrime[n] == false)
            return n;

        while (isPrime[n]) {
            int ns = getSum(n, list);
            if (ns == n)
                return n;
            n = ns;
        }

        return n;
    }

    private int getSum(int n, ArrayList<Integer> list) {
        int ans = 0;

        for (int ele : list) {

            while (n % ele == 0) {

                ans += ele;
                n = n / ele;
            }
        }

        return ans;
    }

    public ArrayList<Integer> getPrimeFactors(int n, boolean[] isPrime) {
        Arrays.fill(isPrime, false);

        for (int i = 2; i <= (int) Math.ceil(Math.sqrt(n)); i++) {

            if (isPrime[i] == false) {
                for (int j = 2 * i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] == false)
                list.add(i);
        }

        return list;

    }

    // 2508. Add Edges to Make Degrees of All Nodes Even
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] degree = new int[n + 1];
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (List<Integer> ed : edges) {
            int u = ed.get(0);
            int v = ed.get(1);

            degree[u]++;
            degree[v]++;

            graph.putIfAbsent(u, new HashSet<>());
            graph.putIfAbsent(v, new HashSet<>());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                list.add(i);
            }
        }

        if (list.size() % 2 != 0)
            return false;

        if (list.size() == 0)
            return true;

        if (list.size() == 2) {
            // if there are two
            // either connect them both => if possible
            // or connect both with other even degree node => if possible
            int u = list.get(0);
            int v = list.get(1);

            if (graph.get(u).contains(v) == false)
                return true;

            for (int i = 1; i <= n; i++) {
                if (u != i && i != v) {
                    if (degree[i] % 2 == 0 && graph.get(u).contains(i) == false && graph.get(v).contains(i) == false)
                        return true;
                }
            }

            return false;
        }

        if (list.size() == 4) {
            int ed = 0;
            for (int i = 0; i < 4; i++) {
                if (degree[list.get(i)] % 2 == 1) {
                    for (int j = i + 1; j < 4; j++) {
                        if (degree[list.get(j)] % 2 == 1) {
                            int u = list.get(i);
                            int v = list.get(j);
                            int taken = 0;

                            if (graph.get(u).contains(v) == false) {
                                ed++;
                                degree[list.get(i)]++;
                                degree[list.get(j)]++;
                                taken++;
                                break;
                            }

                            if (taken == 1)
                                break;

                        }
                    }
                }
            }

            if (ed == 2)
                return true;
            return false;

        }

        // as we are allowed for atmost 2 only
        return false;
    }

    // 2509. Cycle Length Queries in a Tree
    public int[] cycleLengthQueries(int N, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        int idx = 0;

        for (int[] q : queries) {
            int u = q[0];
            int v = q[1];

            int d1 = getDist(u);
            int d2 = getDist(v);

            int lca = findLCA(u, v);
            int dl = getDist(lca);

            //distance between u and v =>  du + dv - 2 * (dlca)

            ans[idx++] = d1 + d2 - 2 * (dl) + 1;
        }

        return ans;
    }

    public int findLCA(int u, int v) {
        int d1 = getDist(u);
        int d2 = getDist(v);

        if (d1 > d2) {
            while (d1 != d2) {
                d1--;
                u = u / 2;
            }

        } else if (d2 > d1) {
            while (d1 != d2) {
                d2--;
                v = v / 2;
            }
        }

        while (u != v) {
            u = u / 2;
            v = v / 2;
        }
        return u;
    }

    public int getDist(int a) {
        if (a == 1)
            return 0;
        if (a % 2 == 1)
            a--;

        return (int) (Math.log(a) / Math.log(2));
    }

}