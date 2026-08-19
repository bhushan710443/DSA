package Problems.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayProbs {

    public static void main(String[] args){
        int[] arr = {2, 2, 3, 4, 2, 5, 1, 2, 3, 50, 5};
        int[] arr1 = {2,7,11,15};
        int[] arr2 = {1,2,3,4};
        System.out.println(robbersProb(arr));
        System.out.println(Arrays.toString(twoSum(arr1, 9)));
        System.out.println(Arrays.toString(multiplyByothersNotItself(arr2)));
    }

    // do not use division
    public static int[] multiplyByothersNotItself(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Prefix products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Suffix products
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
           result[i]= result[i] * suffix;
           suffix= suffix* nums[i];
        }

        return result;
    }

    // robbers prob
    // you can not rob adjacent house
    // find maxAmount he can rob
    public static int robbersProb(int[] arr) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        int prev2 = 0;
        int prev1 = 0;

        for(int a : arr){
            int curr = Math.max(prev1, a + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    //Two Sum
    public static int[] twoSum(int[] arr, int target) {

        //[2,3,11,15,7]
        int [] arrr=new int[2];
        Map<Integer, Integer> map= new HashMap<>();

        for(int a: arr){
            int key=target-a;
            map.put(key,a);
            if (map.containsKey(a)) {
                arrr[0]=a;
                arrr[1]=map.get(a);
            }
        }
        return arrr;
    }


}
