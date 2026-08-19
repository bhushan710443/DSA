package Problems.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {

    public static void main(String[] args){



        int[] list = {2, 1, 3, 5, 2, 6, 1, 2, 2, 4, 5};
        int[] list1 = {-2, 1, -3, 5, 1, -6, 1, 2, 2, 4, -5};
        int[] list2 = {2, 1, 3, 5, 1};

        System.out.println(Arrays.toString(productOfArrayExceptSelf(list2)));
        System.out.println(maxSumOfSubArray(list1));
        System.out.println(maxSum(list,4));
        System.out.println(maxConsucutive(list,3, 9));
    }

    public static int[] productOfArrayExceptSelf(int[] arr){
         int prod=1;

        for (int a: arr){
            prod=prod*a;
        }
       for(int i=0; i<arr.length;i++){
           arr[i]=prod/arr[i];
       }
        return arr;
    }

    public static int maxSumOfSubArray(int[] arr){
        int curr=0;
        int max=0;
        for(int a : arr){
            curr=Math.max(a,curr+a);
            max=Math.max(max, curr);
        }
        return max;
    }


    public static List maxSum(int[] arr, int maxVal){
        ArrayList<Integer> subArr=new ArrayList<>();

        int sum=0;
        int maxSum=0;

        for(int b=0;b<maxVal;b++){
            sum=sum+arr[b];
            maxSum=Math.max(sum, maxSum);

        }
        int a=0;
        int b;
        for( b=maxVal; b< arr.length;b++){
            sum=sum+arr[b]-arr[a];
            maxSum=Math.max(sum, maxSum);
            a++;
        }

        subArr.add(a);
        subArr.add(b);

        return subArr;
    }


    public static int average(int[] arr, int maxVal){
        ArrayList<Integer> subArr=new ArrayList<>();

        int sum=0;
        int maxSum=0;

        for(int b=0;b<maxVal;b++){
            sum=sum+arr[b];
            maxSum=Math.max(sum, maxSum);

        }
        int a=0;
        for(int b=maxVal; b< arr.length;b++){
            sum=sum+arr[b]-arr[a];
            maxSum=Math.max(sum, maxSum);
            a++;
        }

        return maxSum;
    }





















    // Find all subarrays of size maxSize whose sum equals maxVal
    public static List<List<Integer>> maxConsucutive(int[] arr, int maxSize, int maxVal) {
        List<List<Integer>> result = new ArrayList<>();
        if (arr == null || arr.length < maxSize || maxSize <= 0) return result;
        int sum = 0;
        // Initial window sum
        for (int i = 0; i < maxSize; i++) {
            sum += arr[i];
        }
        if (sum == maxVal) {
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < maxSize; i++) sub.add(arr[i]);
            result.add(sub);
        }
        // Slide the window
        for (int i = maxSize; i < arr.length; i++) {
            sum += arr[i] - arr[i - maxSize];
            if (sum == maxVal) {
                List<Integer> sub = new ArrayList<>();
                for (int j = i - maxSize + 1; j <= i; j++) sub.add(arr[j]);
                result.add(sub);
            }
        }
        return result;
    }
}
