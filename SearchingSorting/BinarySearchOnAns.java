package SearchingSorting;

import java.util.Arrays;

public class BinarySearchOnAns {
    // 875. Koko Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = -(int) 1e9;
        for (int ele : piles) {
            hi = Math.max(hi, ele);
        }
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(piles, h, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public boolean isPossible(int[] p, int max, int sp) {
        int cnt = 0;
        for (int ele : p) {
            cnt += (int) Math.ceil((double) (ele * 1.0 / sp * 1.0));
            if (cnt > max)
                return false;
        }
        return true;
    }

    // Maximum Area Serving Cake =>
    // https://leetcode.com/discuss/interview-question/348510/Google-or-OA-2019-or-Maximum-Area-Serving-Cake
    public double MaximumAreaServingCake(int[] radii, int numberOfGuests) {
        int n = radii.length;
        double areas[] = new double[n];
        int i = 0;
        for (int r : radii) {
            areas[i++] = Math.PI * r * r;
        }

        Arrays.sort(areas);
        double lo = 0;
        double hi = areas[n - 1];
        double ans = -1.0;
        while (hi - lo >= 0.00001) {
            double mid = lo + (hi - lo) / 2;

            if (isPossibleToServe(areas, mid, numberOfGuests)) {
                ans = mid;
                lo = mid + 0.00001;
            } else {
                hi = mid - 0.00001;
            }
        }

        return ans;

    }

    private boolean isPossibleToServe(double[] areas, double mid, int numberOfGuests) {
        int cnt = 0;
        for (int i = areas.length - 1; i >= 0; i--) {
            double a = areas[i];
            cnt += Math.floor(a / mid);
            if (cnt >= numberOfGuests)
                return true;

        }
        return false;

    }

    // 1011. Capacity To Ship Packages Within D Days
    public int shipWithinDays(int[] weights, int days) {
        int lo = 1;
        int hi = 0;
        for (int ele : weights) {
            lo = Math.max(lo, ele);
            hi += ele;
        }

        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossileToShip(weights, days, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }

        return ans;
    }

    private boolean isPossileToShip(int[] weights, int days, int mid) {
        int cd = 1;
        int mw = 0;
        for (int ele : weights) {
            mw += ele;
            if (mw > mid) {
                cd++;
                mw = ele;
            }
        }

        return cd <= days;

    }

    // 774. Minimize Max Distance to Gas Station
    public double minmaxGasDist(int[] stations, int K) {
        double si = 0.0;
        double ei = (double) 1e9;
        double ans = -1.0;

        while (ei - si >= (double) 1e-5) {
            double mid = si + (ei - si) / 2; // gap (max)
            if (canPlaceMore(stations, K, mid)) {
                // max diff ie penalty is high
                si = mid + (double) 1e-5;
            } else {
                ans = ei;
                ei = mid - (double) 1e-5;
            }

        }

        return ans;
    }

    private boolean canPlaceMore(int[] stations, int k, double gap) {
        int n = stations.length;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            cnt += (int) ((stations[i] - stations[i - 1]) * 1.0 / gap);
            if (cnt > k)
                return true;
        }
        return false;
    }

}
