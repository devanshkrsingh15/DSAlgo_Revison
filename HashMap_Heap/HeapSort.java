package HashMap_Heap;

public class HeapSort {

    // TC: O(NLOGN) SC: O(1)

    public void HeapSort_(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            downHeapify(arr, i, isMax, n - 1);
        }

        int li = n - 1;
        while (li >= 0) {
            swap(arr, 0, li);
            li--;
            downHeapify(arr, 0, isMax, li);
            
        }
    }

    private boolean compareTo(int[] arr, int max, int lci, boolean isMax) {
        if (isMax)
            return arr[max] < arr[lci];
        else
            return arr[max] > arr[lci];
    }

    private boolean isValid(int i, int n) {
        return i <= n;
    }

    private void downHeapify(int[] arr, int pi, boolean isMax, int n) {
        int max = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (isValid(lci, n) && compareTo(arr, max, lci, isMax))
            max = lci;

        if (isValid(rci, n) && compareTo(arr, max, rci, isMax))
            max = rci;

        if (pi != max) {
            swap(arr, pi, max);
            downHeapify(arr, max, isMax, n);
        }

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void HeapSort(int[] arr) {
        HeapSort_(arr, true);
    }
}
