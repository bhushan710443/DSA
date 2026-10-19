package Problems.Sorting;

import java.util.Arrays;

public class DutchNationalFlagAlgorithm {

    public static void main(String[] args) {

        int[] input = {2, 0, 2, 1, 1, 0};
        System.out.println(Arrays.toString(sortThis(input)));

    }

    //        Output: [0, 0, 1, 1, 2, 2]
    public static int[] sortThis(int[] nums){

        int low=0;
        int high=nums.length-1;
        int mid= 0;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }

        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
