package SearchingSorting;

public class questions {
    // 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int lo = 0;
        int hi = n * m - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int r = mid / m;
            int c = mid % m;
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return false;
    }

    // Count Inversions (GFG)
    /*
     * Inversion Count: For an array, inversion count indicates how far (or close)
     * the array is from being sorted. If array is already sorted then the inversion
     * count is 0. If an array is sorted in the reverse order then the inversion
     * count is the maximum.
     * Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i <
     * j.
     */
    long icount = 0;

    public long inversionCount(long arr[], long n) {
        if (n == 0)
            return 0;
        int len = arr.length;
        inversionCountHelper(arr, 0, len - 1);
        return icount;
    }

    private long[] inversionCountHelper(long[] arr, int lo, int hi) {
        if (lo == hi)
            return new long[] { arr[lo] };

        int mid = lo + (hi - lo) / 2;

        long[] left = inversionCountHelper(arr, lo, mid);
        long[] right = inversionCountHelper(arr, mid + 1, hi);

        return mergeAndCalculate(left, right);

    }

    private long[] mergeAndCalculate(long[] left, long[] right) {
        int n = left.length;
        int m = right.length;
        long[] arr = new long[n + m];

        int i = 0;
        int j = 0;
        int ptr = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[ptr++] = left[i];
                i++;
            } else {
                arr[ptr++] = right[j];
                icount += (long) (n - i);
                j++;
            }
        }

        while (i < left.length)
            arr[ptr++] = left[i++];
        while (j < right.length)
            arr[ptr++] = right[j++];

        return arr;

    }

    // 240. Search a 2D Matrix II
    public boolean searchMatrixII(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int i = 0;
        int j = m - 1;

        while (i < n && j >= 0) {
            if (matrix[i][j] == target)
                return true;

            if (matrix[i][j] < target)
                i++;
            else
                j--;

        }

        return false;
    }

    // 33. Search in Rotated Sorted Array
    public int SearchInRotatedSortedArray(int[] nums, int target) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target <= nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

        }

        return -1;
    }

    // 81. Search in Rotated Sorted Array II
    public boolean SearchInRotatedSortedArray_Dup(int[] nums, int target) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target)
                return true;

            if (nums[lo] == nums[mid])
                lo++;
            else if (nums[lo] < nums[mid]) {
                if (nums[lo] <= target && target <= nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

        }

        return false;
    }


    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int n = nums.length;
        
        int lo = 0;
        int hi = n-1;
        
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            
            if(nums[mid]<nums[hi]) hi = mid;
            else lo = mid+1;
        }
        
        return nums[lo];
    }

    //154. Find Minimum in Rotated Sorted Array II
    public int findMinII(int[] nums) {
        int n = nums.length;
        
        int lo = 0;
        int hi = n-1;
        
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid]==nums[hi]) hi--;
            else if(nums[mid]<nums[hi]) hi = mid;
            else lo = mid+1;
        }
        
        return nums[lo];
    }

}
