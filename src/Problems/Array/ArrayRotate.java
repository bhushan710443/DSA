package Problems.Array;

import java.util.Arrays;

public class ArrayRotate {

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8,9};

        System.out.println(Arrays.toString(rotateArrayByK(arr, 4)));
    }


    // 3 reverse tecq first reverse all
    // reverse first k
    // then reverse remaining
    public static int[] rotateArrayByK(int[] nums, int k) {

        k=k%nums.length;

        reverse(nums,0, nums.length-1 );
        reverse(nums,0, k-1);
        reverse(nums,k, nums.length-1);
        return nums;
    }

//    int[] arr = {1,2,3,4,5,6,7,8,9};
    public static int[] reverse(int[] nums, int left, int right) {
        while (left<right){
            int temp= nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }

        return nums;
    }





}
