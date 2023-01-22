import java.util.*;

public class WeeklyContest327 {
    // 2529. Maximum Count of Positive Integer and Negative Integer
    public int maximumCount(int[] nums) {
        int n = nums.length;

        int lo = 0;
        int hi = n - 1;
        int FzeroPos = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == 0) {
                FzeroPos = mid;
                hi = mid - 1;
            } else if (nums[mid] > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (FzeroPos == -1) {
            return Math.max(hi + 1, n - lo);
        }

        lo = 0;
        hi = n - 1;
        int LzeroPos = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == 0) {
                LzeroPos = mid;
                lo = mid + 1;
            } else if (nums[mid] > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return Math.max(FzeroPos, n - 1 - LzeroPos);

    }

    // 2530. Maximal Score After Applying K Operations
    public long maxKelements(int[] nums, int k) {
        double ans = 0;

        PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> {
            return (int) (b - a);
        });

        for (int ele : nums)
            pq.add((double) ele);

        while (k-- > 0) {
            double rp = pq.remove();
            ans += rp;

            pq.add((double) Math.ceil((double) (rp) / 3));
        }

        return (long) ans;
    }

    // 2531. Make Number of Distinct Characters Equal
    public boolean isItPossible(String word1, String word2) {

        if (word1.equals(word2))
            return true;
        int[] arr1 = findFreq(word1);
        int[] arr2 = findFreq(word2);

        if (Math.abs(getCount(arr1) - getCount(arr2)) > 2)
            return false;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr1[i] == 0 || arr2[j] == 0)
                    continue;

                arr1[j]++;
                arr1[i]--;

                arr2[i]++;
                arr2[j]--;

                if (getCount(arr1) == getCount(arr2))
                    return true;

                arr1[j]--;
                arr1[i]++;

                arr2[i]--;
                arr2[j]++;

            }
        }

        return false;

    }

    private int getCount(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] >= 1)
                cnt++;
        }

        return cnt;
    }

    private int[] findFreq(String word) {
        int[] arr = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            arr[ch - 'a']++;
        }

        return arr;
    }


    
}

//2421. Number of Good Paths
class Solution {
    public int findPar(int u,int[]par){
        if(par[u]==u) return u;
        else{
            int t= findPar(par[u],par);
            par[u] = t;
            return t;

        }
    }
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        int[]par =new int[n];
        int[]size = new int[n];

        for(int i = 0 ; i<n;i++){
            par[i] = i;
            size[i] = 1;
        }

        ArrayList<int[]>list = new ArrayList<>();
        for(int[]ed: edges) list.add(new int[]{ed[0],ed[1]});

        Collections.sort(list,(a,b)->{
            int v1 = Math.max(vals[a[0]],vals[a[1]]);
            int v2 = Math.max(vals[b[0]],vals[b[1]]);

            if(v1<v2){
                return -1;
            }else if(v1>v2){
                return 1;
            }else{
                return 0;
            }

        });

        int ans = 0 ;

        for(int[]ed :list){
            int u = ed[0];
            int v = ed[1];

            int p1 = findPar(u,par);
            int p2 = findPar(v,par);

            if(p1!=p2){
                if(vals[p1]==vals[p2]){
                    ans += size[p1]*size[p2];
                    size[p1] +=  size[p2];
                    par[p2] = p1;
                }else if(vals[p1]>vals[p2]){ 
                    par[p2] = p1;
                }else{ 
                    par[p1] = p2;

                }
            }
        }

        return ans + n;
    }
}