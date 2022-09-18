package SearchingSorting;

public class QuickSort {
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //if pidx is last index => then arr[pidx] will be at correct position wrt  to sorted array 
    public int segregate(int[] arr, int pidx,int lo,int hi) {
        int n = arr.length;
        int i = lo;
        int j = lo;

        swap(arr,pidx,hi); //this is req if we want to take pidx as any index
        while (j <= hi) {
            if (arr[j] > arr[pidx])
                j++;
            else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }

        return i-1;
    }

    public void quickSort(int[]arr,int lo,int hi){
        if(lo>hi) return;
        if(lo==hi) return;

        int pidx = segregate(arr,hi,lo,hi);
        quickSort(arr,lo,pidx-1);
        quickSort(arr,pidx+1,hi);
    }

}
