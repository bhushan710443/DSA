package Problems.Sorting;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args){

        int[] arr={5,6,3,1,4,9,5};
        System.out.print("Selection Sort = ");
        System.out.println(Arrays.toString(selectionSort(arr)));

        int[] arr1={5,6,3,1,4,9,5};
        System.out.print("Insertion Sort = ");
        System.out.println(Arrays.toString(insertionSort(arr1)));

        int[] arr2={5,6,3,1,4,9,5,10};
        System.out.print("Merge Sort = ");
        int left=0;
        int right= arr.length-1;
        System.out.println(Arrays.toString(mergeSort(arr2,left,right)));

        int[] arr3={5,6,3,1,4,9,5,10};
        System.out.print("Quick Sort = ");
        int l=0;
        int r= arr.length-1;
        System.out.println(Arrays.toString(quickSort(arr3,l,r)));

        int[] arr4={5,6,3,1,4,9,5,10};
        System.out.print("Heap Sort = ");
        System.out.println(Arrays.toString(heapSort(arr4)));


    }


    public static int[] heapSort(int[] arr) {
        int n = arr.length;
        // Build heap
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Heapify reduced heap
            heapify(arr, i, 0);
        }

        return arr;
    }


  public static void heapify(int[] arr, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // left child larger?
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // right child larger?
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // swap if root not largest
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // recursively heapify affected subtree
            heapify(arr, n, largest);
        }
    }



    public static int[] quickSort(int[] arr, int low, int high){
        if (low<high){
           int pi= partition(arr,low, high);
            quickSort(arr,low, pi-1);
            quickSort(arr,pi+1, high);
        }
        return arr;
    }

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // place pivot correctly
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static int[] mergeSort(int[] arr, int left, int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr, left, mid, right);
        }
        return arr;
    }

    // Function to merge two subarrays
   static void merge(int[] arr, int left, int mid, int right) {

        //find numbers
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        // Merge temp arrays
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }




    // insertion Sort o(n^2)
    // in best case  o(n)
    public static int[] insertionSort(int[] arr){
        for (int i=1; i<arr.length;i++){
            int current=arr[i];
            int j= i-1;
            while( j>=0 && arr[j]>current){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=current;
        }
        return arr;
    }

   //selectionSort o(n^2)
    public static int[] selectionSort(int[] arr){
        int i=0;
        while(i < arr.length){
            for (int j=(i+1);j < arr.length;j++) {
                if (arr[j] < arr[i]) {
                     int temp = arr[i];
                     arr[i] = arr[j];
                     arr[j] = temp;
                }
            }
           i++;
        }
        return arr;
    }


}
