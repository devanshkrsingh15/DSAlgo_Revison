package Arrays_Strings;

public class questions {
    // Rotate Array
    public void RotateByK(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        if (k < 0)
            k += n;

        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - k - 1);
        reverse(arr, 0, n - 1);
    }

    public void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Negative and Postive
    public void segregatePosNeg(int a[], long n) {
        int i = 0;
        int j = 0;

        while (j < n) {
            if (a[j] < 0) {
                swap(a, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }

    }

    // Sort an array of 0s, 1s and 2s
    public void sort012(int a[], int n) {
        int i = 0;
        int j = 0;
        int k = n - 1;

        while (j <= k) {
            if (a[j] == 0) {
                swap(a, i, j);
                i++;
                j++;
            } else if (a[j] == 1) {
                j++;
            } else {
                swap(a, j, k);
                k--;
            }
        }
    }
}
