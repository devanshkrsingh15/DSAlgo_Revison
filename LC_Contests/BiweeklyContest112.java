import java.util.*;

public class BiweeklyContest112 {

    // 2839. Check if Strings Can be Made Equal With Operations I
    public boolean canBeEqualI(String s1, String s2) {
        int n = s1.length();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        for (int i = 0; i < n; i++) {
            if (arr1[i] == arr2[i])
                continue;
            else if (i + 2 < 2 && arr1[i + 2] == arr2[i]) {
                swap(arr1, i, i + 2);
            } else {
                return false;
            }
        }

        return true;
    }

    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 2840. Check if Strings Can be Made Equal With Operations II
    public boolean checkStringsII(String s1, String s2) {
        // each charac cnt at even and odd places should be same
        int n = s1.length();
        int[] elist1 = generate(s1, true);
        int[] olist1 = generate(s1, false);

        int[] elist2 = generate(s2, true);
        int[] olist2 = generate(s2, false);

        return areEqual(elist1, elist2) && areEqual(olist1, olist2);

    }

    private boolean areEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }

        return true;
    }

    public int[] generate(String s, boolean even) {
        int[] arr = new int[26];
        for (int i = even ? 0 : 1; i < s.length(); i += 2) {
            char ch = s.charAt(i);
            arr[ch - 'a']++;
        }
        return arr;
    }

    // 2841. Maximum Sum of Almost Unique Subarray
    public long maxSum(List<Integer> nums, int m, int k) {
        int st = 0;
        int en = 0;
        int n = nums.size();
        long ans = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        long sof = 0;
        while (en < n) {
            int ele = nums.get(en);
            sof += (long) ele;
            map.put(ele, map.getOrDefault(ele, 0) + 1);

            while (en - st + 1 > k) {
                int sele = nums.get(st);
                sof -= (long) sele;
                map.put(sele, map.getOrDefault(sele, 0) - 1);
                if (map.get(sele) == 0)
                    map.remove(sele);
                st++;
            }

            if (en - st + 1 == k && map.keySet().size() >= m) {
                ans = Math.max(ans, sof);
            }

            en++;
        }

        return ans;

    }
}