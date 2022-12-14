import java.util.*;

public class WeeklyContest326 {
    // 2520. Count the Digits That Divide a Number

    public int countDigits(int num) {
        int temp = num;
        int ans = 0;

        while (temp != 0) {
            int v = temp % 10;
            if (num % v == 0)
                ans++;
            temp /= 10;
        }

        return ans;
    }

    // 2521. Distinct Prime Factors of Product of Array
    public int distinctPrimeFactors(int[] nums) {
        boolean[] isPrime = new boolean[1000 + 1];
        Arrays.sort(nums);
        ArrayList<Integer> list = getPrimeFactors(isPrime.length, isPrime);
        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            if (isPrime[ele] == false) {
                hs.add(ele);
            } else {
                if (i > 0 && ele % nums[i - 1] == 0)
                    ele = ele / nums[i - 1];
                fillUniquePrime(list, ele, hs, 0, isPrime);
            }

        }

        return hs.size();
    }

    public void fillUniquePrime(ArrayList<Integer> list, int ele, HashSet<Integer> hs, int idx, boolean[] isPrime) {
        if (ele <= 1)
            return;
        if (isPrime[ele] == false) {
            hs.add(ele);
            return;
        }

        int p = list.get(idx);

        if (ele % p == 0) {
            hs.add(p);
            while (ele % p == 0) {
                ele = ele / p;
            }
        }
        fillUniquePrime(list, ele, hs, idx + 1, isPrime);

    }

    public ArrayList<Integer> getPrimeFactors(int n, boolean[] isPrime) {
        Arrays.fill(isPrime, false);

        for (int i = 2; i <= (int) Math.ceil(Math.sqrt(n)); i++) {

            if (isPrime[i] == false) {
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == false)
                list.add(i);
        }

        return list;

    }

    // 2522. Partition String Into Substrings With Values at Most K
    public int minimumPartition(String s, int k) {
        int en = 0;
        int n = s.length();

        int ans = 0;

        while (en < n) {
            long cval = 0;
            int oen = en;

            while (oen < n) {
                char ch = s.charAt(oen);
                int val = ch - '0';
                cval = cval * 10 + val;

                if (cval > k)
                    break;

                oen++;
            }

            if (oen == en) {
                return -1;
            }
            ans++;
            en = oen;

        }

        return ans;
    }

    // 2523. Closest Prime Numbers in Range
    public int[] closestPrimes(int left, int right) {
        if (left == right)
            return new int[] { -1, -1 };
        boolean[] isPrime = new boolean[right + 1];
        getPrimeFactors(isPrime.length, isPrime);
        isPrime[0] = true;
        isPrime[1] = true;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime[i] == false)
                list.add(i);
        }

        if (list.size() <= 1)
            return new int[] { -1, -1 };
        if (list.size() == 2)
            return new int[] { list.get(0), list.get(1) };

        int minDiff = (int) 1e9;
        int n1 = -1;
        int n2 = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            int a = list.get(i);
            int b = list.get(i + 1);

            if (b - a < minDiff) {
                n1 = a;
                n2 = b;
                minDiff = b - a;
            }
        }

        return new int[] { n1, n2 };
    }
}
