package Recursion;

public class permutations_cominations {

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        System.out.println(combinationSingle(arr, 10, new boolean[arr.length], "", 0));
    }

    public static int combinationSingle(int[] nums, int tar, boolean[] vis, String psf, int idx) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }

        int cnt = 0;
        for (int i = idx; i < nums.length; i++) {
            if (tar - nums[i] >= 0 && !vis[i]) {
                vis[i] = true;
                cnt += combinationSingle(nums, tar - nums[i], vis, psf + "" + nums[i], i + 1);
                vis[i] = false;
            }
        }

        return cnt;
    }

    public int permutationSingle(int[] nums, int tar, boolean[] vis, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }

        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tar - nums[i] >= 0 && !vis[i]) {
                vis[i] = true;
                cnt += permutationSingle(nums, tar - nums[i], vis, psf + "" + nums[i]);
                vis[i] = false;
            }
        }
        return cnt;

    }

    public int combinationInfi(int[] nums, int tar, String psf, int idx) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }

        int cnt = 0;

        for (int i = idx; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                cnt += combinationInfi(nums, tar - nums[i], psf + "" + nums[i], i);
            }
        }

        return cnt;
    }

    public int permutationInfi(int[] nums, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;

        for (int ele : nums) {
            if (tar - ele >= 0) {
                cnt += permutationInfi(nums, tar - ele, psf + "" + ele);
            }
        }

        return cnt;
    }

    public int combinationSingle_sub(int[] nums, int tar, int idx, String psf) {
        if (idx == nums.length || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        if (tar - nums[idx] >= 0)
            cnt += combinationSingle_sub(nums, tar - nums[idx], idx + 1, psf + "" + nums[idx]);
       
            cnt += combinationSingle_sub(nums, tar, idx + 1, psf);
        return cnt;
    }

    public int permutationSingle_sub(int[] nums, int tar, int idx, String psf,boolean[]vis) {
        if (idx == nums.length || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        if (tar - nums[idx] >= 0 && vis[idx]==false){
            vis[idx]=true;
            cnt += permutationSingle_sub(nums, tar - nums[idx],0, psf + "" + nums[idx],vis);
            vis[idx]=false;
        }
           
       
            cnt += permutationSingle_sub(nums, tar, idx + 1, psf,vis);
        return cnt;
    }

    public int combinationInfi_sub(int[] nums, int tar, int idx, String psf) {
        if (idx == nums.length || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        if (tar - nums[idx] >= 0)
            cnt += combinationInfi_sub(nums, tar - nums[idx], idx , psf + "" + nums[idx]);
       
            cnt += combinationInfi_sub(nums, tar, idx + 1, psf);
        return cnt;
    }

    public int permutationInfi_sub(int[] nums, int tar, int idx, String psf) {
        if (idx == nums.length || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        if (tar - nums[idx] >= 0)
            cnt += permutationInfi_sub(nums, tar - nums[idx], 0 , psf + "" + nums[idx]);
       
            cnt += permutationInfi_sub(nums, tar, idx + 1, psf);
        return cnt;
    }

}
