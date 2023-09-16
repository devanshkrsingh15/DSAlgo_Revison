import java.util.*;

public class WeeklyContest361 {
    // 2843. Count Symmetric Integers
    public int countSymmetricIntegers(int low, int high) {
        int cnt = 0;

        for (int i = low; i <= high; i++) {
            if (isSymmetric(i)) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean isSymmetric(int i) {
        int tot = 0;
        int n = i;
        while (n != 0) {
            tot++;
            n = n / 10;
        }

        if (tot % 2 == 1)
            return false;
        int fn = 0;
        int hlen = tot / 2;
        int ln = 0;

        while (i != 0) {
            int e = i % 10;
            i = i / 10;
            if (hlen > 0) {
                hlen--;
                fn += e;
                ;
            } else {
                ln += e;
            }
        }

        return fn == ln;
    }

    // 2844. Minimum Operations to Make a Special Number
    public int minimumOperations(String num) {
        int n = num.length();
        if (n == 1)
            return Integer.parseInt(num) % 25 == 0 ? 0 : 1;

        int ans = n;

        int lfcnt = 0;

        for (int i = n - 1; i >= 0; i--) {
            char ch = num.charAt(i);
            if (ch == '0') {
                int midcnt = 0;
                ans = Math.min(ans, n - 1);
                for (int j = i - 1; j >= 0; j--) {
                    char chj = num.charAt(j);
                    if (chj == '0' || chj == '5') {
                        int removals = lfcnt + midcnt;
                        ans = Math.min(ans, removals);
                        break;
                    }
                    midcnt++;
                }
            } else if (ch == '5') {
                int midcnt = 0;
                for (int j = i - 1; j >= 0; j--) {
                    char chj = num.charAt(j);
                    if (chj == '7' || chj == '2') {
                        int removals = lfcnt + midcnt;
                        ans = Math.min(ans, removals);

                        break;
                    }
                    midcnt++;
                }
            }

            lfcnt++;
        }

        return ans;
    }

    //2845. Count of Interesting Subarrays
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long ans= 0 ;
        HashMap<Long,Integer>map= new HashMap<>();
        int n= nums.size();
        long cnt = 0;
        for(int i  = 0; i < n; i++){
            int c = nums.get(i)%modulo == k ? 1 : 0;
            cnt += c;

            long r = cnt%modulo;

            if(r==(long)k){
                ans++;
                map.put(r,i);
            }else{
                if(map.contians)
            }
        }
    }

}
