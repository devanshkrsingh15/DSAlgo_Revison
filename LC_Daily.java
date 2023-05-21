import java.util.ArrayList;
import java.util.HashMap;

import LinkedList.ListNode;

import java.util.*;

public class LC_Daily {

    class LC131 {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] isPal;

        public List<List<String>> partition(String s) {
            int n = s.length();
            isPal = new boolean[n][n];
            for (int gap = 0; gap < n; gap++) {
                for (int st = 0, en = st + gap; en < n; st++, en++) {
                    if (gap == 0)
                        isPal[st][en] = true;
                    else if (gap == 1)
                        isPal[st][en] = s.charAt(st) == s.charAt(en);
                    else {
                        isPal[st][en] = isPal[st + 1][en - 1] && s.charAt(st) == s.charAt(en);
                    }
                }
            }

            partition_(s, 0, new ArrayList<>());
            return ans;
        }

        private void partition_(String s, int idx, ArrayList<Integer> tmp) {
            if (idx == s.length()) {
                List<String> myans = getStrings(s, tmp);
                if (myans != null)
                    ans.add(myans);
                return;
            }

            for (int i = idx; i < s.length(); i++) {
                char ch = s.charAt(i);
                tmp.add(i);
                partition_(s, i + 1, tmp);
                tmp.remove(tmp.size() - 1);

            }
        }

        private List<String> getStrings(String s, ArrayList<Integer> tmp) {
            List<String> ans = new ArrayList<>();
            int st = 0;
            for (int i = 0; i < tmp.size(); i++) {
                int en = (i == tmp.size() - 1) ? s.length() : tmp.get(i) + 1;
                if (!isPal[st][en - 1])
                    return null;
                ans.add(s.substring(st, en));
                st = tmp.get(i) + 1;
            }

            return ans;
        }
    }

    class LC997 {
        public int findJudge(int n, int[][] trust) {
            int[] indegree = new int[n + 1];
            int[] outdegree = new int[n + 1];

            for (int[] t : trust) {
                int u = t[0];
                int v = t[1];

                indegree[v]++;
                outdegree[u]++;
            }

            for (int i = 1; i <= n; i++) {
                if (outdegree[i] == 0 && indegree[i] == n - 1)
                    return i;
            }

            return -1;
        }
    }

    class LCC909 {
        public int snakesAndLadders(int[][] board) {
            int n = board.length;
            int m = board[0].length;
            HashMap<Integer, Integer> map = new HashMap<>();
            int val = 0;
            int idx = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (val % 2 == 0) {
                    for (int j = 0; j < m; j++) {
                        map.put(idx, i * m + j);
                        idx++;
                    }
                } else {
                    for (int j = m - 1; j >= 0; j--) {
                        map.put(idx, i * m + j);
                        idx++;
                    }
                }
                val++;
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();
            val = board[n - 1][0] == -1 ? 1 : board[n - 1][0];
            q.add(val);
            boolean[] vis = new boolean[(int) 1e5 + 10];
            int level = 0;

            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int nval = q.remove();

                    if (vis[nval])
                        continue;
                    vis[nval] = true;

                    if (nval == n * n)
                        return level;

                    for (int k = 1; k <= 6; k++) {
                        if (k + nval > n * n)
                            break;
                        int ridx = map.get(k + nval);

                        int r = ridx / n;
                        int c = ridx % n;

                        if (board[r][c] != -1) {
                            q.add(board[r][c]);
                        } else {
                            q.add(k + nval);
                        }
                    }
                }

                level++;
            }

            return -1;
        }

    }

    class LC2359 {
        int ans = (int) 1e9;
        int ansidx = -1;

        public int closestMeetingNode(int[] edges, int node1, int node2) {
            int n = edges.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            getPath(edges, node1, map, 0, true);
            getPath(edges, node2, map, 0, false);
            return ansidx;

        }

        private void getPath(int[] edges, int src, HashMap<Integer, Integer> map, int dis, boolean isBuilding) {

            if (isBuilding == false) {
                if (map.containsKey(src)) {
                    int cans = Math.max(dis, map.get(src));
                    if (cans < ans) {
                        ans = cans;
                        ansidx = src;
                    } else if (cans == ans) {
                        ansidx = Math.min(ansidx, src);
                    }
                }
            }

            if (isBuilding)
                map.put(src, dis);
            else if (!isBuilding) {
                map.put(src + (int) 1e5, dis);
            }

            int nbr = edges[src];
            if ((isBuilding && nbr != -1 && !map.containsKey(nbr))
                    || (!isBuilding && nbr != -1 && !map.containsKey(nbr + (int) 1e5)))
                getPath(edges, nbr, map, dis + 1, isBuilding);
        }

    }

    class LC787 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] arr = new int[n];
            Arrays.fill(arr, (int) 1e9);
            arr[src] = 0;

            for (int f = 0; f <= k; f++) {
                int[] uarr = new int[n];
                for (int i = 0; i < n; i++)
                    uarr[i] = arr[i];

                for (int[] fl : flights) {
                    int u = fl[0];
                    int v = fl[1];
                    int c = fl[2];
                    uarr[v] = Math.min(uarr[v], arr[u] + c);
                }

                arr = uarr;
            }

            return arr[dst] == (int) 1e9 ? -1 : arr[dst];
        }

    }

    class LC472 {
        class TrieNode {
            char ch;
            boolean isFinal;
            TrieNode[] child;

            TrieNode(char ch) {
                this.ch = ch;
                isFinal = false;
                child = new TrieNode[26];
            }

        }

        TrieNode root = new TrieNode('*');

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            for (String s : words) {
                insert(s);
            }

            List<String> ans = new ArrayList<>();
            for (String s : words) {
                if (check(s, 0, 0)) {
                    ans.add(s);
                }
            }

            return ans;

        }

        private boolean check(String s, int idx, int cnt) {
            if (idx == s.length())
                return cnt >= 2;

            TrieNode curr = root;
            boolean res = false;
            for (int i = idx; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (curr.child[ch - 'a'] != null) {
                    curr = curr.child[ch - 'a'];
                    if (curr.isFinal) {
                        boolean a = check(s, i + 1, cnt + 1);
                        if (a)
                            return true;
                    }
                } else {
                    return false;
                }
            }

            return res;
        }

        private void insert(String s) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (curr.child[ch - 'a'] == null)
                    curr.child[ch - 'a'] = new TrieNode(ch);
                curr = curr.child[ch - 'a'];
            }
            curr.isFinal = true;
        }
    }

    // 352. Data Stream as Disjoint Intervals
    class SummaryRanges {

        HashSet<Integer> numbers;
        int max = 0;
        int min = (int) 1e9;

        public SummaryRanges() {
            numbers = new HashSet<>();
        }

        public void addNum(int value) {
            numbers.add(value);
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        public int[][] getIntervals() {
            ArrayList<int[]> intervals = new ArrayList<>();
            boolean[] taken = new boolean[(int) 1e5];
            for (int i = min; i <= max; i++) {
                if (numbers.contains(i) && !taken[i]) {
                    int st = i;
                    int en = i;
                    int ptr = st;
                    taken[i] = true;
                    while (numbers.contains(ptr + 1)) {
                        en = ptr + 1;
                        ptr++;
                        taken[ptr] = true;
                    }

                    intervals.add(new int[] { st, en });
                }
            }

            int[][] ans = new int[intervals.size()][];
            int idx = 0;
            for (int[] intr : intervals) {
                ans[idx++] = intr;
            }

            return ans;

        }
    }

    class LFUCache {
        public class Node {
            int key, val, freq;
            Node next = null;
            Node prev = null;

            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        // (first) Head <-> ...... <-> Tail (last)
        public class DoubleLinkedList {
            Node head = null;
            Node tail = null;
            int len = 0;

            public void AddFist(Node node) {
                if (this.head == null) {
                    this.head = node;
                    this.tail = node;
                } else {
                    this.head.prev = node;
                    node.next = this.head;
                    this.head = node;
                }
                size++;
                len++;
            }

            public void AddLast(Node node) {
                if (this.head == null) {
                    this.head = node;
                    this.tail = node;
                } else {
                    this.tail.next = node;
                    node.prev = this.tail;
                    this.tail = node;
                }
                size++;
                len++;
            }

            public void remove(Node node) {
                if (this.head == node) {
                    RemoveFirst();
                } else if (this.tail == node) {
                    RemoveLast();
                } else {
                    Node pnode = node.prev;
                    Node nnode = node.next;

                    pnode.next = nnode;
                    nnode.prev = pnode;

                    node.prev = null;
                    node.next = null;

                    size--;
                    len--;
                }
            }

            public void RemoveFirst() {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else {
                    Node nhead = this.head.next;
                    nhead.prev = null;
                    this.head.next = null;
                    this.head = nhead;
                }
                size--;
                len--;

            }

            public void RemoveLast() {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else {
                    Node ntail = this.tail.prev;
                    ntail.next = null;
                    this.tail.prev = null;
                    this.tail = ntail;
                }
                size--;
                len--;
            }

        }

        int size = 0;
        int cap = 0;
        int maxF = 0;
        int minF = 0;

        HashMap<Integer, Node> nodeMappping;
        HashMap<Integer, DoubleLinkedList> freqMapping;

        public LFUCache(int capacity) {
            initialize(capacity);
        }

        private void initialize(int capacity) {
            this.nodeMappping = new HashMap<>();
            this.freqMapping = new HashMap<>();
            this.size = 0;
            this.cap = capacity;
        }

        public int get(int key) {
            if (!nodeMappping.containsKey(key))
                return -1;

            Node node = nodeMappping.get(key);
            makeRecent(node);
            return node.val;

        }

        private void makeRecent(Node node) {
            int of = node.freq;
            int nf = of + 1;

            DoubleLinkedList olist = freqMapping.get(of);
            olist.remove(node);

            if (olist.len == 0) {
                freqMapping.remove(of);
                if (minF == of)
                    minF++;
            }

            node.freq = nf;
            nodeMappping.put(node.key, node);

            freqMapping.putIfAbsent(nf, new DoubleLinkedList());
            DoubleLinkedList nlist = freqMapping.get(nf);

            maxF = Math.max(maxF, nf);
            nlist.AddLast(node);
        }

        public void put(int key, int value) {
            if (this.cap == 0)
                return;

            if (!nodeMappping.containsKey(key)) {
                if (this.size == this.cap) {
                    removeLeastFrequent();
                }

                Node node = new Node(key, value, 1);
                nodeMappping.put(key, node);

                freqMapping.putIfAbsent(1, new DoubleLinkedList());
                DoubleLinkedList list = freqMapping.get(1);
                list.AddLast(node);

                minF = 1;
            } else {
                Node node = nodeMappping.get(key);
                node.val = value;
                makeRecent(node);
            }
        }

        private void removeLeastFrequent() {
            DoubleLinkedList list = freqMapping.get(minF);
            Node n = list.head;
            nodeMappping.remove(n.key);
            list.RemoveFirst();
            if (list.len == 0) {
                freqMapping.remove(minF);
            }
        }
    }

    class LC1137 {
        public int tribonacci(int n) {
            int[] dp = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                if (i <= 1)
                    dp[i] = i;
                else if (i == 2)
                    dp[i] = 1;
                else
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            return dp[n];
        }
    }

    class LC1626 {
        int[][] dp;

        public int bestTeamScore(int[] scores, int[] ages) {
            int n = scores.length;
            ArrayList<int[]> list = new ArrayList<>();
            dp = new int[n + 1][(int) 1e3 + 15];
            for (int[] d : dp)
                Arrays.fill(d, -1);

            for (int i = 0; i < n; i++) {
                int ag = ages[i];
                int sc = scores[i];
                list.add(new int[] { ag, sc });
            }

            Collections.sort(list, (a, b) -> {
                if (a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            });
            return bestTeamScore_(list, 0, (int) 1e3 + 10);

        }

        public int bestTeamScore_(ArrayList<int[]> list, int idx, int prvIdx) {
            if (idx == list.size())
                return 0;
            if (dp[idx][prvIdx] != -1)
                return dp[idx][prvIdx];

            int max = 0;
            int myScore = list.get(idx)[1];
            int myAge = list.get(idx)[0];

            if (prvIdx == (int) 1e3 + 10 || list.get(prvIdx)[1] <= myScore)
                max = Math.max(max, bestTeamScore_(list, idx + 1, idx) + myScore);
            max = Math.max(max, bestTeamScore_(list, idx + 1, prvIdx));

            return dp[idx][prvIdx] = max;
        }
    }

    class LC1071 {
        public String gcdOfStrings(String str1, String str2) {
            HashSet<String> hs1 = getDivisors(str1);
            HashSet<String> hs2 = getDivisors(str2);

            if (hs1.size() == 0 || hs2.size() == 0)
                return "";

            int maxLen = 0;
            String maxStr = "";

            for (String s1 : hs1) {
                if (hs2.contains(s1)) {
                    if (maxLen < s1.length()) {
                        maxLen = s1.length();
                        maxStr = s1;
                    }
                }
            }

            return maxStr;
        }

        public HashSet<String> getDivisors(String s) {
            int len = s.length();
            HashSet<String> hs = new HashSet<>();
            for (int i = 0; i < len; i++) {
                String cs = s.substring(0, i + 1);

                if (check(cs, s, i + 1)) {
                    hs.add(cs);
                }

            }

            return hs;
        }

        public boolean check(String cs, String s, int st) {
            int n = s.length();
            int m = cs.length();
            if (n % m != 0)
                return false;

            for (int i = st; i < n; i += m) {
                if (i + m > n)
                    return false;
                if (!cs.equals(s.substring(i, i + m)))
                    return false;
            }

            return true;
        }

        public String gcdOfStringsOpti(String str1, String str2) {
            if (!(str1 + str2).equals((str2 + str1)))
                return "";

            int gcd = findGcd(str1.length(), str2.length());
            return str1.substring(0, gcd);
        }

        private int findGcd(int a, int b) {
            if (b == 0)
                return a;
            return findGcd(b, a % b);
        }
    }

    class LC953 {
        public boolean isAlienSorted(String[] words, String o) {
            int[] order = new int[26];
            for (int i = 0; i < o.length(); i++) {
                char ch = o.charAt(i);
                order[ch - 'a'] = i;
            }

            String prv = words[0];
            for (int i = 1; i < words.length; i++) {
                String curr = words[i];
                int lim = Math.max(prv.length(), curr.length());
                int idx = 0;
                while (idx < lim) {
                    char a = (idx < curr.length()) ? curr.charAt(idx) : ' ';
                    char b = (idx < prv.length()) ? prv.charAt(idx) : ' ';
                    int u = (a == ' ') ? -1 : order[a - 'a'];
                    int v = (b == ' ') ? -1 : order[b - 'a'];
                    idx++;
                    if (u == v) {
                        continue;
                    } else if (u > v) {
                        break;
                    } else {
                        return false;
                    }
                }
                prv = curr;
            }

            return true;
        }

    }

    class LC6 {
        public String convert(String s, int numRows) {
            int n = s.length();
            char arr[][] = new char[numRows][n];
            for (char[] a : arr)
                Arrays.fill(a, '*');
            int r = 0;
            int c = 0;
            int idx = 0;
            int maxC = 0;
            boolean down = true;
            while (idx < n) {
                if (down) {
                    arr[r][c] = s.charAt(idx++);
                    r++;
                    if (r == numRows) {
                        down = false;
                        c++;
                        r -= 2;
                    }
                } else {
                    if (r <= 0) {
                        r = Math.max(0, r);
                        down = true;
                        continue;
                    }
                    arr[r][c] = s.charAt(idx++);
                    r--;
                    c++;
                }
            }

            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < maxC; j++) {
                    if (arr[i][j] != '*') {
                        sb.append(arr[i][j]);
                        cnt++;
                    }
                    if (cnt == n)
                        return sb.toString();
                }
            }

            return sb.toString();
        }
    }

    class LC567 {
        public boolean checkInclusion(String s1, String s2) {
            int[] farr = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                char ch = s1.charAt(i);
                farr[ch - 'a']++;
            }

            int[][] arr = new int[s2.length()][26];
            for (int i = 0; i < s2.length(); i++) {
                char ch = s2.charAt(i);
                arr[i][ch - 'a']++;
                for (int j = 0; j < 26; j++) {
                    if (i - 1 >= 0)
                        arr[i][j] += arr[i - 1][j];
                }
            }

            for (int i = 0; i < s2.length(); i++) {
                if (i + s1.length() - 1 < s2.length()) {
                    int cnt = 0;

                    for (int j = 0; j < 26; j++) {

                        if (farr[j] == ((i == 0) ? arr[i + s1.length() - 1][j]
                                : arr[i + s1.length() - 1][j] - arr[i - 1][j])) {
                            cnt++;
                        }
                    }
                    if (cnt == 26)
                        return true;
                }

            }

            return false;

        }
    }

    class LC438 {
        public List<Integer> findAnagrams(String s, String p) {
            ArrayList<Integer> ans = new ArrayList<>();
            int[] arr = new int[26];
            int cnt = 0;
            for (int i = 0; i < p.length(); i++) {
                if (arr[p.charAt(i) - 'a'] == 0)
                    cnt++;
                arr[p.charAt(i) - 'a']++;
            }

            int ei = 0;
            int si = 0;

            while (ei < s.length()) {
                arr[s.charAt(ei) - 'a']--;
                if (arr[s.charAt(ei) - 'a'] == 0)
                    cnt--;
                ei++;
                while (cnt == 0) {
                    if (ei - si == p.length())
                        ans.add(si);
                    arr[s.charAt(si) - 'a']++;
                    if (arr[s.charAt(si) - 'a'] == 1)
                        cnt++;
                    si++;
                }
            }

            return ans;
        }
    }

    class LC1470 {
        public int[] shuffle(int[] nums, int n) {
            int[] arr = new int[2 * n];
            int len = nums.length;
            int idx = 0;
            for (int i = 0; i < len / 2; i++) {
                arr[idx] = nums[i];
                idx += 2;
            }
            idx = 1;

            for (int i = len / 2; i < len; i++) {
                arr[idx] = nums[i];
                idx += 2;
            }

            return arr;
        }
    }

    class LC904 {
        public int totalFruit(int[] fruits) {
            int[] farr = new int[(int) 1e5 + 7];
            int cnt = 0;
            int ei = 0;
            int si = 0;
            int n = fruits.length;
            int max = 0;
            while (ei < n) {
                if (farr[fruits[ei++]]++ == 0)
                    cnt++;
                while (cnt > 2) {
                    if (farr[fruits[si++]]-- == 1)
                        cnt--;
                }

                max = Math.max(max, ei - si);
            }

            return max;
        }
    }

    class LC2306 {
        public long distinctNames(String[] ideas) {
            HashSet<String> sets[] = new HashSet[26];
            for (int i = 0; i < 26; i++) {
                sets[i] = new HashSet<>();
            }

            for (String id : ideas) {
                char ch = id.charAt(0);
                sets[ch - 'a'].add(id.substring(1));
            }

            long ans = 0l;

            for (int i = 0; i < 26; i++) {
                for (int j = i + 1; j < 26; j++) {
                    HashSet<String> setA = sets[i];
                    HashSet<String> setB = sets[j];

                    long sameSuff = 0l;

                    for (String s : setA) {
                        if (setB.contains(s))
                            sameSuff++;
                    }

                    ans += ((long) setA.size() - sameSuff) * ((long) setB.size() - sameSuff) * 2;

                }
            }

            return ans;

        }

    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = -1;

        int ones = 0;
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    zeros++;
                if (grid[i][j] == 1)
                    ones++;
            }
        }

        if (ones == 0 || zeros == 0)
            return -1;

        if (ones == 1) {
            int x1 = -1;
            int y1 = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        x1 = i;
                        y1 = j;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        max = Math.max(max, Math.abs(i - x1) + Math.abs(j - y1));
                    }
                }
            }

            return max;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int res = bfs(grid, i, j);
                    if (res != -1) {
                        int x = res / m;
                        int y = res % m;

                        max = Math.max(max, Math.abs(i - x) + Math.abs(j - y));
                    }
                }

            }
        }

        return max;
    }

    public int bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;

        boolean vis[][] = new boolean[n][m];
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i * m + j);

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();
                int r = ridx / m;
                int c = ridx % m;

                if (grid[r][c] == 1)
                    return r * m + c;
                if (vis[r][c])
                    continue;

                vis[r][c] = true;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {
                        q.add(x * m + y);
                    }
                }
            }

        }

        return -1;
    }

    class LC1129 {
        class Edge {
            int u;
            int v;
            int col;

            Edge(int u, int v, int col) {
                this.u = u;
                this.v = v;
                this.col = col;
            }
        }

        // red wt = 1
        // blue wt = 0;
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            ArrayList<Edge> graph[] = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] ed : redEdges) {
                int u = ed[0];
                int v = ed[1];
                graph[u].add(new Edge(u, v, 1));

            }

            for (int[] ed : blueEdges) {
                int u = ed[0];
                int v = ed[1];
                graph[u].add(new Edge(u, v, 0));
            }

            int[] ans = new int[n];
            Arrays.fill(ans, -1);

            boolean[][] vis = new boolean[n][2];

            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[] { 0, -1 }); // src,color
            vis[0][0] = vis[0][1] = true;
            int level = 0;
            while (q.size() != 0) {
                int s = q.size();

                while (s-- > 0) {
                    int[] ridx = q.remove();
                    int idx = ridx[0];
                    int color = ridx[1];
                    if (ans[idx] == -1)
                        ans[idx] = level;

                    for (Edge ed : graph[idx]) {
                        if (!vis[ed.v][ed.col] && color != ed.col) {
                            q.add(new int[] { ed.v, ed.col });
                            vis[ed.v][ed.col] = true;
                        }
                    }
                }

                level++;
            }

            return ans;
        }
    }

    class LC2477 {
        long ans = 0l;
        int S = 0;

        public long minimumFuelCost(int[][] roads, int seats) {
            S = seats;
            ArrayList<Integer> graph[] = buildGraph(roads);
            if (graph.length == 1)
                return 0l;

            int n = graph.length;
            boolean[] vis = new boolean[n];
            dfs(0, graph, vis);
            return ans;
        }

        public int dfs(int src, ArrayList<Integer> graph[], boolean[] vis) {
            int size = 0;
            vis[src] = true;

            for (int nbr : graph[src]) {
                if (!vis[nbr]) {
                    size += dfs(nbr, graph, vis);
                }
            }
            size++;

            if (src != 0)
                ans += size / S + (size % S > 0 ? 1 : 0);

            return size;
        }

        public ArrayList<Integer>[] buildGraph(int[][] roads) {
            int n = roads.length;
            ArrayList<Integer>[] graph = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] rd : roads) {
                int u = rd[0];
                int v = rd[1];

                graph[u].add(v);
                graph[v].add(u);
            }

            return graph;
        }
    }

    class LC1523 {
        public int countOdds(int low, int high) {
            if (low % 2 == 0)
                low++;
            if (high % 2 == 0)
                high--;

            long n = (high - low) / 2 + 1;

            return (int) n;
        }
    }

    class LC989 {
        public List<Integer> addToArrayForm(int[] num, int k) {
            int i = num.length - 1;
            ArrayList<Integer> ans = new ArrayList<>();
            int carry = 0;

            while (i >= 0 || k != 0) {
                int a = (i >= 0) ? num[i] : 0;
                int b = (k > 0) ? k % 10 : 0;
                int sum = a + b + carry;

                if (sum < 10) {
                    ans.add(sum);
                    carry = 0;
                } else {
                    ans.add(sum % 10);
                    carry = sum / 10;
                }

                i--;
                k /= 10;
            }
            if (carry != 0) {
                ans.add(carry);
            }

            Collections.reverse(ans);
            return ans;
        }
    }

    class LC67 {
        public String addBinary(String a, String b) {
            if (a.length() < b.length()) {
                return addBinary(b, a);
            }

            int n = a.length();
            int m = b.length();
            int i = n - 1;
            int j = m - 1;
            int carry = 0;

            StringBuilder sb = new StringBuilder();

            while (j >= 0) {
                char cha = a.charAt(i);
                char chb = b.charAt(j);

                int an = cha - '0';
                int am = chb - '0';

                if (an == 0 && am == 0) {
                    sb.append(carry);
                    carry = 0;
                } else if (an == 1 && am == 1) {
                    int app = (carry == 1) ? 1 : 0;
                    sb.append(app);
                    carry = 1;
                } else {
                    int app = (carry == 1) ? 0 : 1;
                    sb.append(app);
                    carry = (carry == 1) ? 1 : 0;
                }

                i--;
                j--;

            }

            while (i >= 0) {
                char cha = a.charAt(i);
                int an = cha - '0';
                int am = carry;

                if (an == 0 && am == 0) {
                    sb.append(carry);
                    carry = 0;
                } else if (an == 1 && am == 1) {
                    int app = 0;
                    sb.append(app);
                    carry = 1;
                } else {
                    int app = 1;
                    sb.append(app);
                    carry = 0;
                }

                i--;
            }

            if (carry != 0)
                sb.append(carry);

            sb.reverse();
            return sb.toString();
        }
    }

    class LC540 {
        public int singleNonDuplicate(int[] nums) {
            int n = nums.length;
            int lo = 0;
            int hi = n - 1;

            if (n == 1)
                return nums[lo];

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (mid > 0 && mid < n - 1) {
                    if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid])
                        return nums[mid];

                    if ((nums[mid - 1] == nums[mid] && mid % 2 == 1) || (nums[mid + 1] == nums[mid] && mid % 2 == 0)) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                } else if (mid == 0) {
                    if (nums[mid + 1] != nums[mid])
                        return nums[mid];

                } else {
                    if (nums[mid - 1] != nums[mid])
                        return nums[mid];
                }
            }

            return 0;

        }
    }

    class LC1011 {
        public int shipWithinDays(int[] weights, int days) {
            int max = 0;
            int min = 0;
            for (int ele : weights) {
                min = Math.max(min, ele);
                max += ele;
            }

            int ans = -1;

            while (min <= max) {
                int mid = min + (max - min) / 2;
                if (check(weights, days, mid)) {
                    ans = mid;
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }
            }
            return ans;
        }

        public boolean check(int[] arr, int D, int cap) {
            int d = 1;
            int cp = 0;
            for (int ele : arr) {
                cp += ele;
                if (cp > cap) {
                    cp = ele;
                    d++;
                }
            }

            return d <= D;
        }
    }

    class LC35 {
        public int searchInsert(int[] nums, int tar) {
            int l = 0;
            int h = nums.length - 1;

            while (l <= h) {
                int m = l + (h - l) / 2;

                if (nums[m] == tar)
                    return m;
                else if (nums[m] < tar)
                    l = m + 1;
                else
                    h = m - 1;

            }

            return l;
        }
    }

    class LC1675 {
        public int minimumDeviation(int[] nums) {
            int n = nums.length;
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
                return b - a;
            });

            int min = (int) 1e9 + 10;
            int max = -1;

            for (int ele : nums) {
                int nele = (ele % 2 == 0) ? ele : 2 * ele;
                min = Math.min(min, nele);
                max = Math.max(max, nele);
                pq.add(nele);
            }

            int ans = max - min;

            while (pq.peek() % 2 == 0) {
                int rp = pq.remove();
                min = Math.min(min, rp / 2);
                pq.add(rp / 2);
                ans = Math.min(ans, pq.peek() - min);
            }

            return ans;

        }
    }

    class LC502 {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            PriorityQueue<Integer> profits_pq = new PriorityQueue<>((a, b) -> {
                return profits[b] - profits[a];
            });
            PriorityQueue<Integer> capitals_pq = new PriorityQueue<>((a, b) -> {
                return capital[a] - capital[b];
            });

            int n = capital.length;
            for (int i = 0; i < n; i++)
                capitals_pq.add(i);

            while (k-- > 0) {
                while (capitals_pq.size() != 0 && w >= capital[capitals_pq.peek()]) {
                    profits_pq.add(capitals_pq.peek());
                    capitals_pq.remove();
                }
                if (profits_pq.size() == 0)
                    return w;
                w += profits[profits_pq.remove()];
            }

            return w;

        }
    }

    class EditDis {
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();
            int[][] dp = new int[n + 1][m + 1];
            for (int[] d : dp)
                Arrays.fill(d, -1);

            return minDistance_(word1, word2, n, m, dp);
        }

        public int minDistance_(String word1, String word2, int n, int m, int[][] dp) {
            if (n == 0 || m == 0) {
                return (n == 0 && m == 0) ? 0 : (m == 0 ? n : m);
            }

            if (dp[n][m] != -1)
                return dp[n][m];

            int min = (int) 1e9;

            if (word1.charAt(n - 1) == word2.charAt(m - 1))
                min = Math.min(min, minDistance_(word1, word2, n - 1, m - 1, dp));
            else {
                min = Math.min(min, minDistance_(word1, word2, n, m - 1, dp) + 1); // INSERT
                min = Math.min(min, minDistance_(word1, word2, n - 1, m, dp) + 1); // DELETE
                min = Math.min(min, minDistance_(word1, word2, n - 1, m - 1, dp) + 1); // REPLACE

            }
            return dp[n][m] = min;
        }
    }

    class LC427 {
        class Node {
            public boolean val;
            public boolean isLeaf;
            public Node topLeft;
            public Node topRight;
            public Node bottomLeft;
            public Node bottomRight;

            public Node() {
                this.val = false;
                this.isLeaf = false;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = topLeft;
                this.topRight = topRight;
                this.bottomLeft = bottomLeft;
                this.bottomRight = bottomRight;
            }
        }

        public Node construct(int[][] grid) {
            int len = grid.length;
            return construct_(grid, 0, 0, len);
        }

        public Node construct_(int[][] grid, int i, int j, int n) {
            if (areEqual(grid, i, j, n)) {
                return new Node(grid[i][j] == 1, true, null, null, null, null);
            }
            Node rn = new Node(true, false);
            rn.topLeft = construct_(grid, i, j, n / 2);
            rn.topRight = construct_(grid, i, j + n / 2, n / 2);
            rn.bottomLeft = construct_(grid, i + n / 2, j, n / 2);
            rn.bottomRight = construct_(grid, i + n / 2, j + n / 2, n / 2);
            return rn;
        }

        public boolean areEqual(int[][] grid, int r, int c, int len) {
            int val = grid[r][c];
            for (int i = r; i < r + len; i++) {
                for (int j = c; j < c + len; j++) {
                    if (grid[i][j] != val)
                        return false;
                }
            }

            return true;
        }
    }

    class LC912 {
        public int[] sortArray(int[] nums) {
            int n = nums.length;
            return mergeSort(nums, 0, n - 1);
        }

        public int[] mergeSort(int[] arr, int lo, int hi) {
            if (lo == hi)
                return new int[] { arr[lo] };

            int mid = lo + (hi - lo) / 2;
            int[] lft = mergeSort(arr, lo, mid);
            int[] right = mergeSort(arr, mid + 1, hi);

            return mergeTwoSortedArrays(lft, right);
        }

        public int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
            int n = arr1.length;
            int m = arr2.length;

            int[] narr = new int[n + m];
            int i = 0;
            int j = 0;
            int idx = 0;

            while (i < n && j < m) {
                if (arr1[i] <= arr2[j]) {
                    narr[idx++] = arr1[i++];
                } else {
                    narr[idx++] = arr2[j++];
                }
            }

            while (i < n)
                narr[idx++] = arr1[i++];
            while (j < m)
                narr[idx++] = arr2[j++];
            return narr;
        }
    }

    class LC443 {
        public int compress(char[] chars) {
            int i = 0;
            int n = chars.length;
            int len = 0;
            int idx = 0;

            while (i < n) {
                char ch = chars[i];
                if (i + 1 < n && ch == chars[i + 1]) {
                    int cnt = 1;
                    while (i + 1 < n && ch == chars[i + 1]) {
                        cnt++;
                        i++;
                    }
                    String tmp = "" + cnt + "";
                    len += tmp.length() + 1;
                    chars[idx++] = ch;
                    for (int j = 0; j < tmp.length(); j++) {
                        chars[idx++] = tmp.charAt(j);
                    }
                    i++;
                } else {
                    i++;
                    len += 1;
                    chars[idx++] = ch;
                }

            }

            return len;
        }
    }

    class LC28 {
        public int strStr(String h, String nd) {
            int n = h.length();
            int m = nd.length();
            if (m > n)
                return -1;
            if (h.equals(nd))
                return 0;

            int en = 0;
            while (en < n) {
                char ch = h.charAt(en);
                int oen = en;
                if (ch == nd.charAt(0)) {
                    int ans = en;
                    int idx = 0;
                    while (en < n && idx < m && h.charAt(en) == nd.charAt(idx)) {
                        en++;
                        idx++;
                    }
                    if (idx == m)
                        return ans;
                }
                en = oen + 1;

            }
            return -1;

        }
    }

    class LC2444 {
        public long countSubarrays(int[] nums, int minK, int maxK) {
            long ans = 0;
            int min = -1;
            int max = -1;

            int en = 0;
            int n = nums.length;
            int st = 0;

            while (en < n) {
                int ele = nums[en];
                if (ele == minK)
                    min = en;
                if (ele == maxK)
                    max = en;

                if (ele < minK || ele > maxK) {
                    min = -1;
                    max = -1;
                    st = en + 1;
                }

                if (min != -1 && max != -1) {
                    ans += (long) (Math.min(min, max) - st + 1);
                }

                en++;
            }

            return ans;
        }
    }

    class LC1345 {
        public int minJumps(int[] arr) {
            ArrayDeque<Integer> q = new ArrayDeque<>();
            int n = arr.length;
            boolean[] vis = new boolean[n];
            q.add(0); // src,par
            vis[0] = true;
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int ele = arr[i];
                map.putIfAbsent(ele, new ArrayList<>());
                map.get(ele).add(i);
            }

            int level = 0;
            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int src = q.remove();

                    if (src == n - 1)
                        return level;

                    if (src - 1 > 0 && !vis[src - 1]) {
                        vis[src - 1] = true;
                        q.add(src - 1);
                    }
                    if (src + 1 < n && !vis[src + 1]) {
                        vis[src + 1] = true;
                        q.add(src + 1);
                    }

                    int val = arr[src];
                    if (map.containsKey(val)) {
                        for (int nsrc : map.get(val)) {
                            if (nsrc != src && !vis[nsrc]) {
                                vis[nsrc] = true;
                                q.add(nsrc);
                            }
                        }
                        map.remove(val);
                    }

                }
                level++;
            }

            return -1;

        }
    }

    class LC2466 {
        int L;
        int H;
        int z;
        int o;

        public int countGoodStrings(int low, int high, int zero, int one) {
            L = low;
            H = high;
            z = zero;
            o = one;

            long[] dp = new long[H + 1];
            Arrays.fill(dp, -1l);

            return (int) countGoodStrings_(0, dp);
        }

        long mod = (long) 1e9 + 7;

    public long countGoodStrings_(int len,long[]dp){
        if(len>=dp.length) return 0l;

        if(dp[len]!=-1l) return dp[len];

        long zeros = countGoodStrings_(len + z,dp);
        long ones = countGoodStrings_(len + o,dp);
        
        long ans = (zeros%mod + ones%mod)%mod;c
        if(len>=L && len<=H) ans = (ans%mod + 1l)%mod;


        return dp[len] = ans;
    }
    }

    class LC2140 {
        public long mostPoints(int[][] questions) {
            int n = questions.length;

            long[] dp = new long[n + 1];
            Arrays.fill(dp, -1l);

            return mostPoints_(questions, 0, dp);
        }

        public long mostPoints_(int[][] arr, int idx, long[] dp) {
            if (idx >= arr.length)
                return 0l;
            if (dp[idx] != -1l)
                return dp[idx];

            long exc = mostPoints_(arr, idx + 1, dp);
            long inc = mostPoints_(arr, idx + arr[idx][1] + 1, dp) + arr[idx][0];

            return dp[idx] = Math.max(exc, inc);
        }
    }

    class LC1539 {
        public int findKthPositive(int[] arr, int k) {
            int n = arr.length;
            int hi = n - 1;
            int lo = 0;
            int K = k + 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] - mid < K)
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }

            int val = 1;

            if (hi >= 0) {
                int diff = arr[hi] - hi;
                diff--;
                k -= diff;
                val = arr[hi];
            } else if (hi == -1 && arr[0] > k) {
                while (k-- > 1) {
                    val++;
                }
                return val;
            }

            while (k-- >= 1) {
                val++;
            }

            return val;
        }
    }

    class LC2187 {
        public long minimumTime(int[] time, int totalTrips) {
            long min = 1l;
            long max = (long) 1e18;

            long ans = (long) time[0] * (long) totalTrips;

            while (min <= max) {
                long mid = min + (max - min) / 2;
                if (isPossible(time, totalTrips, mid)) {
                    ans = mid;
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }
            }

            return ans;
        }

        public boolean isPossible(int[] arr, long tot, long curr) {
            long trips = 0;
            for (int ele : arr) {
                long a = (curr / (long) ele);
                trips += a;
                if (trips >= tot)
                    return true;
            }

            return false;
        }
    }

    class LC1799 {
        HashMap<String, Integer> map = new HashMap<>();

        public int maxScore(int[] nums) {
            int n = nums.length;
            return helper(nums, n / 2, 0);
        }

        public int helper(int[] nums, int n, int vis) {
            String key = n + "/" + vis;
            if (n == 0)
                return 0;

            if (map.containsKey(key))
                return map.get(key);

            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((vis & (1 << i)) == 0) {
                    vis ^= (1 << i);
                    int x = nums[i];

                    for (int j = i + 1; j < nums.length; j++) {
                        if ((vis & (1 << j)) == 0) {
                            vis ^= (1 << j);
                            int y = nums[j];

                            max = Math.max(max, helper(nums, n - 1, vis) + n * gcd(x, y));
                            vis ^= (1 << j);
                        }
                    }

                    vis ^= (1 << i);
                }
            }

            map.put(key, max);
            return max;
        }

        public int gcd(int a, int b) {
            if (b == 0)
                return a;
            return gcd(b, a % b);
        }
    }

    class Trie {
        class TrieNode {
            char ch;
            TrieNode[] children;
            boolean isEnd;

            TrieNode(char ch) {
                this.ch = ch;
                this.children = new TrieNode[26];
                this.isEnd = false;
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode('-');
        }

        public void insert(String word) {
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (tmp.children[ch - 'a'] == null)
                    tmp.children[ch - 'a'] = new TrieNode(ch);
                tmp = tmp.children[ch - 'a'];
            }
            tmp.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (tmp.children[ch - 'a'] == null)
                    return false;
                tmp = tmp.children[ch - 'a'];
            }

            return tmp.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode tmp = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (tmp.children[ch - 'a'] == null)
                    return false;
                tmp = tmp.children[ch - 'a'];
            }

            return true;
        }
    }

    class BrowserHistory {
        Stack<String> ms;
        Stack<String> hs;

        public BrowserHistory(String homepage) {
            ms = new Stack<>();
            hs = new Stack<>();
            ms.add(homepage);
        }

        public void visit(String url) {
            ms.push(url);
            hs = new Stack<>();
        }

        public String back(int steps) {
            while (ms.size() != 0 && steps-- > 0) {
                hs.push(ms.pop());
            }
            if (ms.size() == 0) {
                ms.push(hs.pop());
            }
            return ms.peek();
        }

        public String forward(int steps) {
            while (hs.size() != 0 && steps-- > 0) {
                ms.push(hs.pop());
            }
            return ms.peek();
        }
    }

    class LC2555 {
        public int maximizeWin(int[] prizePositions, int k) {
            int n = prizePositions.length;
            if (prizePositions[0] + k >= prizePositions[n - 1])
                return n;

            int st = 0;
            int en = 0;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            int max = 0;
            while (en < n) {
                while (prizePositions[st] + k < prizePositions[en]) {
                    st++;
                }

                dp[en + 1] = Math.max(dp[en], en - st + 1);
                max = Math.max(max, en - st + 1 + dp[st]);
                en++;
            }
            return max;
        }

    }

    class Node {
        char ch;
        HashMap<Character, Node> children;
        boolean isEnd;

        Node(char ch) {
            this.ch = ch;
            this.children = new HashMap<>();
            this.isEnd = false;
        }
    }

    Node root = new Node('*');

    public void addWord(String word) {
        Node tmp = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            tmp.children.putIfAbsent(ch, new Node(ch));
            tmp = tmp.children.get(ch);
        }

        tmp.isEnd = true;
    }

    public boolean search(String word) {
        return search_(word, 0, root);
    }

    public boolean search_(String word, int idx, Node tmp) {
        if (idx == word.length())
            return tmp.isEnd;

        char ch = word.charAt(idx);
        if (ch == '.') {
            for (char nch : tmp.children.keySet()) {
                if (search_(word, idx + 1, tmp.children.get(nch))) {
                    return true;
                }
            }

            return false;
        }
        if (!tmp.children.containsKey(ch))
            return false;

        return search_(word, idx + 1, tmp.children.get(ch));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if ((i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }

        return n <= 0;
    }

    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n];
        long ans = 0l;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = (nums[i] == 0) ? 1l : 0l;
            } else {
                dp[i] = (nums[i] == 0) ? dp[i - 1] + 1l : 0l;
            }

            ans += dp[i];
        }

        return ans;
    }

    public int minScore(int n, int[][] roads) {
        HashMap<Integer, Integer> graph[] = new HashMap[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] rd : roads) {
            int u = rd[0];
            int v = rd[1];
            int wt = rd[2];
            graph[u].put(v, Math.min(wt, graph[u].getOrDefault(v, (int) 1e9)));
            graph[v].put(u, Math.min(wt, graph[v].getOrDefault(u, (int) 1e9)));
        }

        return dfs(graph, 1, n, new HashSet<>());

    }

    public int dfs(HashMap<Integer, Integer> graph[], int src, int dest, HashSet<Integer> vis) {
        int ans = (int) 1e9;
        vis.add(src);
        HashMap<Integer, Integer> map = graph[src];
        for (int nbr : map.keySet()) {

            int wt = map.get(nbr);
            if (!vis.contains(nbr))
                ans = Math.min(ans, dfs(graph, nbr, dest, vis));
            ans = Math.min(ans, wt);

        }

        return ans;
    }

    public int findPar(int u, int[] par) {
        if (par[u] == u)
            return u;
        else {
            int tmp = findPar(par[u], par);
            par[u] = tmp;
            return tmp;
        }
    }

    public int makeConnected(int n, int[][] connections) {
        int[] par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        int avl = 0;
        for (int[] ed : connections) {
            int u = ed[0];
            int v = ed[1];

            int p1 = findPar(u, par);
            int p2 = findPar(v, par);

            if (p1 == p2) {
                avl++;
            } else {
                par[p2] = p1;
            }

        }

        int req = 0;
        for (int i = 0; i < n; i++) {
            if (par[i] == i)
                req++;
        }

        req--;

        return (avl >= req) ? req : -1;

    }

    class LC1466 {
        public int minReorder(int n, int[][] connections) {
            ArrayList<Integer> graph[] = new ArrayList[n];
            HashMap<Integer, HashSet<Integer>> direcs = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                direcs.put(i, new HashSet<>());
            }

            int ans = 0;
            for (int[] ed : connections) {
                int u = ed[0];
                int v = ed[1];
                graph[u].add(v);
                graph[v].add(u);
                direcs.get(u).add(v);
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(0);
            boolean[] vis = new boolean[n];

            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int src = q.remove();
                    if (vis[src])
                        continue;
                    vis[src] = true;
                    for (int nbr : graph[src]) {
                        if (!vis[nbr]) {
                            if (direcs.get(src).contains(nbr))
                                ans++;
                            q.add(nbr);
                        }
                    }
                }
            }

            return ans;
        }
    }

    class LC2360 {
        int ans = -1;

        public int longestCycle(int[] edges) {
            int n = edges.length;
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    bfs(edges, i, vis);
                }
            }
            return ans;
        }

        public void bfs(int[] edges, int src, boolean[] vis) {
            int n = edges.length;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(src);
            int level = 0;
            HashMap<Integer, Integer> map = new HashMap<>();

            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int ridx = q.remove();
                    map.putIfAbsent(ridx, level);
                    if (vis[ridx])
                        continue;
                    vis[ridx] = true;
                    int nbr = edges[ridx];
                    if (nbr != -1) {
                        if (!vis[nbr])
                            q.add(nbr);
                        if (map.containsKey(nbr)) {
                            ans = Math.max(ans, level - map.get(nbr) + 1);
                        }
                    }
                }
                level++;
            }

        }
    }

    // 2598. Smallest Missing Non-negative Integer After Operations
    public int findSmallestInteger(int[] nums, int value) {
        int[] remArray = new int[value];
        for (int ele : nums)
            remArray[(ele % value + value) % value]++;

        int i = 0;
        while (true) {
            int r = i % value;
            if (remArray[r] == 0)
                break;
            remArray[r]--;
            i++;
        }

        return i;
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] daysAllowed = new int[] { 1, 7, 30 };
        int[] dp = new int[400];
        Arrays.fill(dp, -1);
        boolean[] travel = new boolean[400];
        for (int ele : days)
            travel[ele] = true;
        int n = days.length;
        int last = days[n - 1];
        return mincostTickets_(last + 1, costs, 1, dp, daysAllowed, travel);
    }

    public int mincostTickets_(int tar, int[] costs, int cd, int[] dp, int[] daysAllowed, boolean[] travel) {
        if (cd >= tar)
            return 0;
        if (dp[cd] != -1)
            return dp[cd];
        int min = (int) 1e9;

        if (travel[cd] == false)
            return dp[cd] = mincostTickets_(tar, costs, cd + 1, dp, daysAllowed, travel);

        for (int i = 0; i < 3; i++) {
            int ncd = cd + daysAllowed[i];
            min = Math.min(min, mincostTickets_(tar, costs, ncd, dp, daysAllowed, travel) + costs[i]);
        }
        return dp[cd] = min;
    }

    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e9);
        Arrays.sort(satisfaction);
        return maxSatisfaction_(satisfaction, 0, 1, dp);
    }

    public int maxSatisfaction_(int[] arr, int idx, int time, int[][] dp) {
        if (idx == arr.length)
            return 0;
        if (dp[idx][time] != -(int) 1e9)
            return dp[idx][time];

        int max = -(int) 1e9;

        max = Math.max(max, maxSatisfaction_(arr, idx + 1, time, dp));
        max = Math.max(max, maxSatisfaction_(arr, idx + 1, time + 1, dp) + arr[idx] * time);
        return dp[idx][time] = max;
    }

    class ScrambleString {
        HashMap<String, Boolean> dp = new HashMap<>();

        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) {
                dp.put(s1 + "+" + s2, true);
                return true;
            }

            if (haveSameCharacters(s1, s2) == false) {
                dp.put(s1 + "+" + s2, false);
                return false;
            }

            if (dp.containsKey(s1 + "+" + s2))
                return dp.get(s1 + "+" + s2);

            for (int i = 1; i < s1.length(); i++) {
                // swapping
                boolean f1 = isScramble(s1.substring(0, i), s2.substring(s2.length() - i));
                boolean f2 = isScramble(s1.substring(i), s2.substring(0, s2.length() - i));
                if (f1 && f2) {
                    dp.put(s1 + "+" + s2, true);
                    return true;
                }

                // not swapping
                f1 = isScramble(s1.substring(0, i), s2.substring(0, i));
                f2 = isScramble(s1.substring(i), s2.substring(i));
                if (f1 && f2) {
                    dp.put(s1 + "+" + s2, true);
                    return true;
                }

            }

            dp.put(s1 + "+" + s2, false);
            return false;
        }

        public boolean haveSameCharacters(String s1, String s2) {
            int m1 = getMask(s1);
            int m2 = getMask(s2);
            return m1 == m2;
        }

        public int getMask(String s) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int m = (1 << (ch - 'a'));
                mask |= m;
            }

            return mask;
        }
    }

    class LC1444 {

        long mod = (long) 1e9 + 7;
        long[][][] dp;

        public int ways(String[] pizza, int k) {
            int n = pizza.length;
            int m = pizza[0].length();
            char grid[][] = convert(pizza);
            int[][] countApple = new int[n][m];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    countApple[i][j] = grid[i][j] == 'A' ? 1 : 0;
                    if (i + 1 < n)
                        countApple[i][j] += countApple[i + 1][j];
                    if (j + 1 < m)
                        countApple[i][j] += countApple[i][j + 1];
                    if (i + 1 < n && j + 1 < m)
                        countApple[i][j] -= countApple[i + 1][j + 1];
                }
            }

            dp = new long[60][60][20];
            for (long[][] d2 : dp) {
                for (long[] d1 : d2) {
                    Arrays.fill(d1, -1l);
                }
            }

            return (int) ways_(countApple, 0, 0, k);
        }

        public long ways_(int[][] countApple, int str, int stc, int parts) {
            if (countApple[str][stc] == 0)
                return 0l;
            if (parts == 1)
                return 1l;

            if (dp[str][stc][parts] != -1l)
                return (long) dp[str][stc][parts];
            int n = countApple.length;
            int m = countApple[0].length;

            long ans = 0l;

            // horizontal
            for (int r = str + 1; r < n; r++) {
                if (countApple[str][stc] - countApple[r][stc] > 0) {
                    ans = (ans % mod + ways_(countApple, r, stc, parts - 1) % mod) % mod;
                }
            }

            // vertical
            for (int c = stc + 1; c < m; c++) {
                if (countApple[str][stc] - countApple[str][c] > 0) {
                    ans = (ans % mod + ways_(countApple, str, c, parts - 1) % mod) % mod;
                }
            }

            return dp[str][stc][parts] = ans;

        }

        public char[][] convert(String[] pizza) {
            int n = pizza.length;
            int m = pizza[0].length();
            char[][] arr = new char[n][m];
            for (int i = 0; i < n; i++) {
                String st = pizza[i];
                for (int j = 0; j < m; j++) {
                    arr[i][j] = st.charAt(j);
                }
            }

            return arr;
        }

    }

    class LC2300 {
        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            Arrays.sort(potions);
            int ans[] = new int[spells.length];
            int i = 0;
            for (int sp : spells) {
                int reqP = (int) Math.ceil((success * 1.0) / (sp * 1.0));
                int idx = find(potions, reqP);
                ans[i] = potions.length - idx;
                i++;
            }

            return ans;
        }

        public int find(int[] arr, int tar) {
            int lo = 0;
            int hi = arr.length - 1;
            int ans = arr.length;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid] >= tar) {
                    ans = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            return ans;
        }
    }

    class LC2405 {
        public int partitionString(String s) {
            int ans = 0;
            int n = s.length();
            int mask = 0;
            int ei = 0;
            while (ei < n) {
                char ch = s.charAt(ei);
                int k = (1 << (ch - 'a'));
                if ((mask & k) == 0) {
                    mask |= k;
                } else {
                    mask = 0;
                    mask |= k;
                    ans++;
                }

                ei++;
            }
            return ans + 1;
        }
    }

    class LC2439 {
        public int minimizeArrayValue(int[] nums) {
            int lo = 0;
            int hi = (int) 1e9;
            int ans = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (isPossible(nums, mid)) {
                    ans = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            return ans;
        }

        public boolean isPossible(int[] arr, int max) {
            int n = arr.length;
            int cnt = 1;
            long sof = 0;
            for (int ele : arr) {
                sof += (long) ele;
                if (sof > (long) max * (long) cnt)
                    return false;
                cnt++;
            }

            return true;
        }
    }

    class LC1254 {
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        public int closedIsland(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            boolean[][] vis = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                if (grid[i][0] == 0 && !vis[i][0])
                    dfs(grid, i, 0, vis);
                if (grid[i][m - 1] == 0 && !vis[i][m - 1])
                    dfs(grid, i, m - 1, vis);
            }

            for (int j = 0; j < m; j++) {
                if (grid[0][j] == 0 && !vis[0][j])
                    dfs(grid, 0, j, vis);
                if (grid[n - 1][j] == 0 && !vis[n - 1][j])
                    dfs(grid, n - 1, j, vis);
            }

            int ans = 0;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (!vis[i][j] && grid[i][j] == 0) {
                        ans++;
                        dfs(grid, i, j, vis);
                    }
                }
            }

            return ans;
        }

        public void dfs(int[][] grid, int i, int j, boolean[][] vis) {
            int n = grid.length;
            int m = grid[0].length;

            vis[i][j] = true;
            for (int k = 0; k < direcs.length; k++) {
                int x = i + direcs[k][0];
                int y = j + direcs[k][1];

                if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && grid[x][y] == 0) {
                    dfs(grid, x, y, vis);
                }
            }
        }
    }

    // 2390. Removing Stars From a String
    class LC2390 {
        public String removeStars(String s) {
            Stack<Character> st = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch != '*') {
                    st.push(ch);
                } else {
                    if (st.size() != 0 && st.peek() != '*') {
                        st.pop();
                    }
                }
            }
            if (st.size() == 0)
                return "";
            StringBuilder sb = new StringBuilder();
            while (st.size() != 0) {
                sb.append(st.pop());
            }

            sb.reverse();
            return sb.toString();
        }
    }

    /// 2218. Maximum Value of K Coins From Piles
    class LC2218 {
        List<List<Long>> psum = new ArrayList<>();

        public int maxValueOfCoins(List<List<Integer>> piles, int k) {
            if (k == 0)
                return 0;
            long[][] dp = new long[piles.size() + 1][k + 1];
            for (long d[] : dp)
                Arrays.fill(d, -1l);

            for (List<Integer> l : piles) {
                List<Long> sum = new ArrayList<>();
                long sof = 0l;
                for (int ele : l) {
                    sof += (long) ele;
                    sum.add(sof);
                }
                psum.add(sum);
            }

            if (piles.size() <= 1) {
                return piles.size() == 0 ? 0 : (int) (getSum(0, k - 1));
            }

            return (int) maxValueOfCoins_(piles, 0, k, dp);
        }

        public long getSum(int pidx, int i) {
            return psum.get(pidx).get(i);
        }

        public long maxValueOfCoins_(List<List<Integer>> piles, int pidx, int k, long[][] dp) {
            if (pidx == piles.size() || k == 0) {
                return (k == 0) ? 0 : -(long) 1e9;
            }

            if (dp[pidx][k] != -1l)
                return dp[pidx][k];

            long ans = maxValueOfCoins_(piles, pidx + 1, k, dp);

            for (int i = 1; i <= k; i++) {
                if (i - 1 < piles.get(pidx).size()) {
                    long myAns = getSum(pidx, i - 1);
                    long fAns = maxValueOfCoins_(piles, pidx + 1, k - i, dp);
                    ans = Math.max(ans, myAns + fAns);
                } else {
                    break;
                }
            }

            return dp[pidx][k] = ans;

        }
    }

    // 1639. Number of Ways to Form a Target String Given a Dictionary

    class LC1639 {
        public int numWays(String[] words, String target) {
            int n = words[0].length();
            int[][] freqArray = new int[26][n];
            for (String s : words) {
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    freqArray[ch - 'a'][i]++;
                }
            }

            long[][] dp = new long[target.length() + 1][n + 1];
            for (long[] d : dp)
                Arrays.fill(d, -1l);

            return (int) numWays_(words, target, 0, 0, dp, freqArray);
        }

        long mod = (long) 1e9 + 7;

        public long numWays_(String[] words, String tar, int tidx, int widx, long[][] dp, int[][] freqArray) {
            if (tidx == tar.length() || widx == words[0].length()) {
                return (tidx == tar.length()) ? 1l : 0l;
            }

            if (dp[tidx][widx] != -1l)
                return dp[tidx][widx];

            long ways = numWays_(words, tar, tidx, widx + 1, dp, freqArray);
            char ch = tar.charAt(tidx);

            if (freqArray[ch - 'a'][widx] > 0) {
                ways = ways % mod + ((long) freqArray[ch - 'a'][widx] % mod
                        * (long) numWays_(words, tar, tidx + 1, widx + 1, dp, freqArray) % mod) % mod;
            }

            return dp[tidx][widx] = ways % mod;

        }

    }

    class LC1431 {
        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            int max = 0;
            for (int ele : candies)
                max = Math.max(max, ele);

            ArrayList<Boolean> list = new ArrayList<>();
            for (int ele : candies) {
                list.add(max <= (ele + extraCandies));
            }

            return list;

        }
    }

    class LC879 {
        long[][][] dp;

        public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
            dp = new long[group.length + 1][n + 1][minProfit + 1];
            for (long[][] ddp : dp) {
                for (long[] d : ddp)
                    Arrays.fill(d, -1l);
            }
            return (int) profitableSchemes_(0, n, minProfit, group, profit);
        }

        long mod = (long) 1e9 + 7;

        // all profift greater than minProfit will belong to same group, as indexx can't
        // be -ve
        public long profitableSchemes_(int idx, int p, int pr, int[] gr, int[] pro) {
            if (idx == gr.length || p == 0) {
                return (pr == 0) ? 1l : 0l;
            }

            if (dp[idx][p][pr] != -1l)
                return dp[idx][p][pr];

            long ans = 0l;

            ans = (ans % mod + profitableSchemes_(idx + 1, p, pr, gr, pro) % mod) % mod;
            if (p - gr[idx] >= 0)
                ans = (ans % mod + profitableSchemes_(idx + 1, p - gr[idx], Math.max(0, pr - pro[idx]), gr, pro) % mod)
                        % mod;

            return dp[idx][p][pr] = ans;

        }
    }

    class LC1312 {
        public int minInsertions(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];

            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = i + gap; j < n; i++, j++) {
                    if (gap == 0)
                        dp[i][j] = 1;
                    else {
                        if (s.charAt(i) == s.charAt(j)) {
                            dp[i][j] = dp[i + 1][j - 1] + 2;
                        } else {
                            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                        }
                    }
                }
            }

            return n - dp[0][n - 1];
        }
    }

    class LC1416 {
        long k;

        public int numberOfArrays(String s, int K) {
            k = (long) K;
            long[] dp = new long[s.length() + 1];
            Arrays.fill(dp, -1l);

            return (int) numberOfArrays_(s, 0, dp);
        }

        long mod = (long) 1e9 + 7;

        public long numberOfArrays_(String s, int idx, long[] dp) {
            if (idx == s.length()) {
                return 1l;
            }

            if (dp[idx] != -1l)
                return dp[idx];

            long ans = 0;
            if (s.charAt(idx) == '0')
                return dp[idx] = ans % mod;
            long nums = 0;
            for (int i = idx; i < s.length(); i++) {
                nums = nums * 10l + (s.charAt(i) - '0');
                if (nums > k)
                    break;
                ans = (ans % mod + numberOfArrays_(s, i + 1, dp) % mod) % mod;

            }
            return dp[idx] = ans % mod;
        }
    }

    class LC839 {
        int[] par;

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;
            par = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
            }

            int groups = n;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (areSimilar(strs[i], strs[j])) {
                        int p1 = findPar(i);
                        int p2 = findPar(j);

                        if (p1 != p2) {
                            par[p2] = p1;
                            groups--;
                        }
                    }
                }
            }

            return groups;
        }

        public int findPar(int u) {
            if (par[u] == u)
                return u;
            else {
                int t = findPar(par[u]);
                par[u] = t;
                return t;
            }
        }

        public boolean areSimilar(String a, String b) {
            if (a.length() != b.length())
                return false;

            int n = a.length();
            int swaps = 0;
            ;

            for (int i = 0; i < n; i++) {
                if (a.charAt(i) != b.charAt(i))
                    swaps++;
                if (swaps > 2)
                    return false;
            }

            return true;
        }
    }

    class LC1557 {
        public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
            ArrayList<Integer> ans = new ArrayList<>();

            int[] indeg = new int[n];
            for (List<Integer> ed : edges) {
                int u = ed.get(0);
                int v = ed.get(1);
                indeg[v]++;
            }

            for (int i = 0; i < n; i++) {
                if (indeg[i] == 0)
                    ans.add(i);
            }

            return ans;
        }
    }

    class LC1046 {
        public int lastStoneWeight(int[] stones) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
                return stones[b] - stones[a];
            });
            int n = stones.length;
            for (int i = 0; i < n; i++)
                pq.add(i);

            while (pq.size() > 1) {
                int max = pq.remove();
                int smax = pq.peek();

                if (stones[max] == stones[smax]) {
                    pq.remove();
                } else {
                    stones[max] -= stones[smax];
                    pq.remove();
                    pq.add(max);
                }
            }

            return pq.size() > 0 ? stones[pq.peek()] : 0;

        }
    }

    class LC1697 {
        int[] par;
        int ptr = 0;

        public int findPar(int u) {
            if (par[u] == u)
                return u;
            else {
                int t = findPar(par[u]);
                par[u] = t;
                return t;
            }
        }

        public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            boolean[] ans = new boolean[queries.length];

            par = new int[n];
            for (int i = 0; i < n; i++)
                par[i] = i;
            ArrayList<int[]> edges = new ArrayList<>();
            ArrayList<int[]> qrs = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                qrs.add(new int[] { queries[i][0], queries[i][1], queries[i][2], i });
            }

            for (int i = 0; i < edgeList.length; i++) {
                edges.add(new int[] { edgeList[i][0], edgeList[i][1], edgeList[i][2] });
            }

            Collections.sort(qrs, (a, b) -> {
                return a[2] - b[2];
            });

            Collections.sort(edges, (a, b) -> {
                return a[2] - b[2];
            });

            for (int[] q : qrs) {
                int u = q[0];
                int v = q[1];
                int idx = q[3];
                buildGraph(edges, q[2]);

                int p1 = findPar(u);
                int p2 = findPar(v);

                ans[idx] = p1 == p2;
            }

            return ans;
        }

        public void buildGraph(ArrayList<int[]> edges, int lim) {

            while (ptr < edges.size() && lim > edges.get(ptr)[2]) {
                int u = edges.get(ptr)[0];
                int v = edges.get(ptr)[1];

                int p1 = findPar(u);
                int p2 = findPar(v);

                if (p1 != p2) {
                    par[p2] = p1;
                }
                ptr++;
            }
        }
    }

    public boolean LC2543(int targetX, int targetY) {
        // doing reverse operation
        while (targetX % 2 == 0)
            targetX = targetX / 2;
        while (targetY % 2 == 0)
            targetY = targetY / 2;

        while (targetY >= 1 || targetX >= 1) {
            if (targetY > targetX) {
                targetY -= targetX;
            } else if (targetY < targetX) {
                targetX -= targetY;
            } else {
                if (targetY == 1 && targetX == 1)
                    return true;
                else {
                    // if x==a && y==a && a!=1, we can only do 0 not 1
                    return false;
                }
            }
        }

        return true;
    }

    public String LC649(String senate) {
        ArrayDeque<Integer> rq = new ArrayDeque<>();
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        int n = senate.length();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R')
                rq.add(i);
            else
                dq.add(i);
        }

        while (rq.size() != 0 && dq.size() != 0) {
            int ridx = rq.remove();
            int didx = dq.remove();
            if (ridx < didx)
                rq.add(ridx + n);
            else
                dq.add(didx + n);
        }

        return rq.size() > dq.size() ? "Radiant" : "Dire";
    }

    public int LC1498(int[] nums, int target) {
        long ans = 0l;
        int n = nums.length;
        long mod = (long) 1e9 + 7;
        Arrays.sort(nums);
        long[] powers = new long[n + 1];
        powers[0] = 1l;
        for (int i = 1; i <= n; i++) {
            powers[i] = (powers[i - 1] % mod * (long) 2 % mod) % mod;
        }

        int i = 0;
        int j = n - 1;

        while (i <= j) {
            if (nums[i] + nums[j] <= target) {
                ans = (ans % mod + powers[j - i] % mod) % mod;
                i++;
            } else {
                j--;
            }
        }

        return (int) (ans % mod);
    }

    class LC934 {
        int[][]direcs ={{0,1},{1,0},{0,-1},{-1,0}};
        public int shortestBridge(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
    
            int[][]vis = new int[n][m];
            for(int[]v :vis) Arrays.fill(v,-1);
    
            Queue<Integer>q = new ArrayDeque<>();
            for(int i = 0 ;i<n;i++){
                boolean found = false;
                for(int j = 0 ;j<m;j++){
                    if(grid[i][j]==1){
                        dfs(grid,i*m+j,vis,q); 
                        found = true;
                        break;
                    }
                }
    
                if(found) break;
            }
    
            int min = n*m;
    
            int level = 0;
            while(q.size()!=0){
                int s = q.size();
                while(s-->0){
                    int idx = q.remove();
                   
                    int r = idx/m;
                    int c = idx%m;
    
                    if(grid[r][c]==1 && vis[r][c]>0) return vis[r][c]-1;
    
                    for(int k = 0 ;k<direcs.length ;k++){
                        int x = r + direcs[k][0];
                        int y = c + direcs[k][1];
                        
                        if(x>=0 && y>=0 && x<n && y<m){
                            if(vis[x][y]==-1 || vis[x][y] > level+1){
                                q.add(x*m+y);
                                vis[x][y] = level+1;
                            }
                        }
                    }
                }
    
                level++;
            }
    
            return -1;
        }
    
        public void dfs(int[][] grid,int idx,int[][]vis, Queue<Integer>q){
            int n = grid.length;
            int m = grid[0].length;
    
            int r = idx/m;
            int c = idx%m;
    
            q.add(idx);
            vis[r][c] = 0;
    
            for(int k = 0 ;k<direcs.length ;k++){
                int x = r + direcs[k][0];
                int y = c + direcs[k][1];
                        
                if(x>=0 && y>= 0 && x<n && y<m){
                    if(grid[x][y]==1 && vis[x][y]==-1){
                        dfs(grid,x*m +y,vis,q);
                    }
                }
            }
        }
    }

    class LC399 {
        public class Pair {
            String idx;
            double wt;

            Pair(String idx, double wt) {
                this.idx = idx;
                this.wt = wt;
            }
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            HashMap<String, HashSet<Pair>> graph = buildGraph(equations, values);
            double[] ans = new double[queries.size()];

            for (int i = 0; i < queries.size(); i++) {
                String src = queries.get(i).get(0);
                String des = queries.get(i).get(1);

                if (!graph.containsKey(src) || !graph.containsKey(des)) {
                    ans[i] = -1.0;
                } else {
                    HashSet<String> vis = new HashSet<>();
                    double tmp[] = dfs(graph, src, des, vis);
                    if (tmp[1] == 0)
                        ans[i] = -1;
                    else
                        ans[i] = tmp[0];
                }
            }

            return ans;
        }

        // wt,found
        public double[] dfs(HashMap<String, HashSet<Pair>> graph, String s, String d, HashSet<String> vis) {
            if (s.equals(d))
                return new double[] { (double) 1, (double) 1 };

            vis.add(s);
            for (Pair rp : graph.get(s)) {
                if (!vis.contains(rp.idx)) {
                    double fans[] = dfs(graph, rp.idx, d, vis);
                    if (fans[1] == 1)
                        return new double[] { fans[0] * rp.wt, (double) 1 };
                }
            }

            return new double[] { (double) -1, (double) 0 };
        }

        public HashMap<String, HashSet<Pair>> buildGraph(List<List<String>> equations, double[] values) {
            HashMap<String, HashSet<Pair>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                List<String> ed = equations.get(i);
                String u = ed.get(0);
                String v = ed.get(1);
                double w1 = values[i];
                double w2 = (double) 1.0 / values[i];

                graph.putIfAbsent(u, new HashSet<>());
                graph.putIfAbsent(v, new HashSet<>());

                graph.get(u).add(new Pair(v, w1));
                graph.get(v).add(new Pair(u, w2));
            }

            return graph;
        }
    }

    class LC24 {

        class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        // 24. Swap Nodes in Pairs
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode a = head;
            ListNode b = head.next;

            ListNode dummy = new ListNode(-1);
            ListNode ptr = dummy;

            while (a != null && b != null) {
                ListNode na = b.next;
                ListNode nb = na != null ? na.next : null;

                a.next = null;
                b.next = null;

                ptr.next = b;
                b.next = a;
                ptr = ptr.next.next;

                a = na;
                b = nb;
            }

            if (a != null)
                ptr.next = a;

            return dummy.next;
        }
    }

}
