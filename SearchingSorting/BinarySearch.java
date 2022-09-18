package SearchingSorting;

/*
 * All dry run your code in the following cases
 *  1) Data not present
 *  2) Data present at 0,n-1, and in between pos
 */
public class BinarySearch {
    public int binarySearch(int[] arr, int si, int ei, int ele) {
        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (arr[mid] == ele)
                return mid;
            else if (arr[mid] > ele)
                ei = mid - 1;
            else
                si = mid + 1;
        }

        return -1;
    }

    // 34. Find First and Last Position of Element in Sorted Array
    public int firstIndex(int[] arr, int ele) {
        int n = arr.length;
        int ans = -1;
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == ele) {
                ans = mid;
                hi = mid - 1;
            } else if (arr[mid] > ele) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public int lastIndex(int[] arr, int ele) {
        int n = arr.length;
        int ans = -1;
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == ele) {
                ans = mid;
                lo = mid + 1;
            } else if (arr[mid] > ele) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public int insertPos(int[] arr, int ele) {
        int lo = 0;
        int hi = arr.length - 1;
        int pos = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == ele) {
                pos = mid;
                lo = mid + 1;
            } else if (arr[mid] > ele) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (pos != -1)
            return pos;
        return lo;
    }

    // last position
    public int insertPos_Better(int[] arr, int ele) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= ele) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return (lo - 1 >= 0 && arr[lo - 1] == ele) ? lo - 1 : lo;
    }

    public int findNearestElement(int[] arr, int ele) {
        int n = arr.length;

        if (ele <= arr[0] || ele >= arr[n - 1]) {
            return ele <= arr[0] ? arr[0] : arr[n - 1];
        }

        // arr[hi] <= ele < arr[lo];
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= ele) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ele - arr[hi] <= arr[lo] - ele ? hi : lo;
    }

}
