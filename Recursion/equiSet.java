package Recursion;

public class equiSet {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 60, 70, 80, 90 };

        System.out.println(equiSet(arr, 1, arr[0], 0, arr[0] + " ", ""));

    }

    private static int equiSet(int[] arr, int idx, int s1, int s2, String p1, String p2) {
        if (idx == arr.length) {
            if (s1 == s2) {
                System.out.println(p1 + " = " + p2);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        cnt += equiSet(arr, idx + 1, s1, s2 + arr[idx], p1, p2 + arr[idx] + " ");
        cnt += equiSet(arr, idx + 1, s1 + arr[idx], s2, p1 + arr[idx] + " ", p2);

        return cnt;
    }
}
