package SearchingSorting;

import java.util.*;

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

    // 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int n = nums.length;

        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] < nums[hi])
                hi = mid;
            else
                lo = mid + 1;
        }

        return nums[lo];
    }

    // 154. Find Minimum in Rotated Sorted Array II
    public int findMinII(int[] nums) {
        int n = nums.length;

        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == nums[hi])
                hi--;
            else if (nums[mid] < nums[hi])
                hi = mid;
            else
                lo = mid + 1;
        }

        return nums[lo];
    }

    // 167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            if (arr[lo] + arr[hi] == target)
                return new int[] { lo + 1, hi + 1 };
            else if (arr[lo] + arr[hi] > target)
                hi--;
            else
                lo++;
        }

        return null;
    }

    // Printing all unique ans
    public void twoSumAllUnique(int[] arr, int tar, int lo, int hi) {

        while (lo < hi) {
            if (arr[lo] + arr[hi] == tar) {
                System.out.println(lo + "," + hi);
                lo++;
                while (lo < hi && arr[lo - 1] == arr[lo])
                    lo++;
                hi--;
                while (lo < hi && arr[hi + 1] == arr[hi])
                    hi--;
            } else if (arr[lo] + arr[hi] > tar) {
                hi--;
                while (lo < hi && arr[hi + 1] == arr[hi])
                    hi--;
            } else {
                lo++;
                while (lo < hi && arr[lo - 1] == arr[lo])
                    lo++;
            }
        }

    }


    //454. 4Sum II
     public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer>map = new HashMap<>();
        for(int a :nums1){
            for(int b:nums2){
                map.put(a+b,map.getOrDefault(a+b,0)+1);
            }
        }
        
        int ans = 0;
        for(int a:nums3){
            for(int b:nums4){
                int t = - (a+b);
                if(map.containsKey(t)) ans+=map.get(t);
            }
        }
        
        return ans;
    }

    //array is sorted
    public ArrayList<ArrayList<Integer>>  genericKSum(int[]arr,int tar,int lo,int hi,int k){
        if(k==2) return twoSum_(arr,tar,lo,hi,k);
        
        ArrayList<ArrayList<Integer>>myans = new ArrayList<>();
        int i =  lo;
        while(i<=hi){
            int ele= arr[i];
            int ntar = tar-ele;
            i++;
            ArrayList<ArrayList<Integer>>fans = genericKSum(arr,ntar,i,hi,k-1);
            buildAns(myans,fans,ele);
            while(i<=hi  && arr[i-1]==arr[i]) i++;

        }

        return myans;
    }

    private void buildAns(ArrayList<ArrayList<Integer>> myans, ArrayList<ArrayList<Integer>> fans, int ele) {
        for (ArrayList<Integer> l : fans) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(ele);
            for (int e : l) {
                tmp.add(e);
            }

            myans.add(tmp);

        }
    }

    private ArrayList<ArrayList<Integer>> twoSum_(int[] arr, int tar, int lo, int hi, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (lo < hi) {
            if (arr[lo] + arr[hi] == tar) {
                ArrayList<Integer>tmp = new ArrayList<>();
                tmp.add(arr[lo]);tmp.add(arr[hi]);
                ans.add(tmp);
                lo++;
                while (lo < hi && arr[lo - 1] == arr[lo])
                    lo++;

                hi--;
                while (lo < hi && arr[hi + 1] == arr[hi])
                    hi--;
            } else if (arr[lo] + arr[hi] > tar) {
                hi--;
            } else {
                lo++;
            }
        }

        return ans;
    }

    //658. Find K Closest Elements
    //O(K)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n =  arr.length;
        if(x<=arr[0]) return extract(arr,k,0);
        if(x>=arr[n-1]) return extract(arr,k,n-k);

        int lo = 0 ;
        int hi = n-1;

        while(lo<=hi){
            int mid = lo + (hi-lo)/2;

            if(arr[mid]<=x){
                lo =  mid+1;
            }else{
                hi = mid-1;
            }
        }

        int ipos = (lo-1>=0 && arr[lo-1]==x) ? lo-1: lo;
        int l = Math.max(0,ipos - k);
        int r = Math.max(n-1,ipos + k);

        while(r-l+1>k){
            if(x-arr[l] > arr[r] - x) l++;
            else r--;
        }
        
       
        return extract(arr,k,l);
    }

    public List<Integer> extract(int[]arr,int k,int st){
        List<Integer>ans = new ArrayList<>();
        for(int i = 0; i<k;i++){
            ans.add(arr[i+st]);
        }
        return ans;
    }


}

class fourSum{
    public List<List<Integer>> threeSum(int[] nums, int tar,int lo,int hi) {
        List<List<Integer>> list = new ArrayList<>();
        int i =lo;
        while (i <= hi) {
            int ele = nums[i];
            int ntar = tar - ele;
            i++;
            List<List<Integer>> twoSumAns = twoSum(nums, ntar, i, hi);
            builAns(list, twoSumAns, nums, ele);
            while (i <= hi && nums[i] == nums[i - 1])
                i++;
        }

        return list;

    }

    private void builAns(List<List<Integer>> list, List<List<Integer>> twoSumAns, int[] nums,
            int ele) {
        for (List<Integer> l : twoSumAns) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(ele);
            for (int e : l) {
                tmp.add(e);
            }

            list.add(tmp);

        }

    }

    private List<List<Integer>> twoSum(int[] arr, int tar, int lo, int hi) {
        List<List<Integer>> ans = new ArrayList<>();
        while (lo < hi) {
            if (arr[lo] + arr[hi] == tar) {
                addEle(ans, arr[lo], arr[hi]);
                lo++;
                while (lo < hi && arr[lo - 1] == arr[lo])
                    lo++;

                hi--;
                while (lo < hi && arr[hi + 1] == arr[hi])
                    hi--;
            } else if (arr[lo] + arr[hi] > tar) {
                hi--;
            } else {
                lo++;
            }
        }

        return ans;

    }

    private void addEle(List<List<Integer>> ans, int... arr) {
        List<Integer> tmp = new ArrayList<>();
        for (int ele : arr) {
            tmp.add(ele);
        }
        ans.add(tmp);
    }
   
    public List<List<Integer>> fourSum(int[] nums, int tar) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int ele = nums[i];
            int ntar = tar - ele;
            i++;
            List<List<Integer>> threeSumAns = threeSum(nums, ntar, i, n-1);
            builAns(list, threeSumAns, nums, ele);
            while (i < n && nums[i] == nums[i - 1])
                i++;
        }

        return list;
        
    }
}

class threeSum {
    //nums should be sorted
    public List<List<Integer>> threeSum(int[] nums, int tar) {
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int ele = nums[i];
            int ntar = tar - ele;
            i++;
            ArrayList<ArrayList<Integer>> twoSumAns = twoSum(nums, ntar, i, n - 1);
            builAns(list, twoSumAns, nums, ele);
            while (i < n && nums[i] == nums[i - 1])
                i++;
        }

        return list;

    }

    private void builAns(List<List<Integer>> list, ArrayList<ArrayList<Integer>> twoSumAns, int[] nums,
            int ele) {
        for (ArrayList<Integer> l : twoSumAns) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(ele);
            for (int e : l) {
                tmp.add(e);
            }

            list.add(tmp);

        }

    }

    private ArrayList<ArrayList<Integer>> twoSum(int[] arr, int tar, int lo, int hi) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (lo < hi) {
            if (arr[lo] + arr[hi] == tar) {
                addEle(ans, arr[lo], arr[hi]);
                lo++;
                while (lo < hi && arr[lo - 1] == arr[lo])
                    lo++;

                hi--;
                while (lo < hi && arr[hi + 1] == arr[hi])
                    hi--;
            } else if (arr[lo] + arr[hi] > tar) {
                hi--;
            } else {
                lo++;
            }
        }

        return ans;

    }

    private void addEle(ArrayList<ArrayList<Integer>> ans, int... arr) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int ele : arr) {
            tmp.add(ele);
        }
        ans.add(tmp);
    }
}
