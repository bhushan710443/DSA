package Problems.Array;

import java.util.*;

public class ArrayProbs {

    public static void main(String[] args){
        int[] arr = {2, 2, 3, 4, 2, 5, 1, 2, 3, 50, 5};
        int[] arr1 = {12,7,11,13};
        int[] arr2 = {1,2,3,4};
        int[] arr3 = {1,2,-1,2,3,-2,4};
        int[] arr4 = {1,2,-1,2,3, 26, 24};

        System.out.println(robbersProb(arr1));

        System.out.println(Arrays.deepToString(new List[]{twoSum(arr4, 25)}));

        System.out.println(Arrays.toString(multiplyByothersNotItself(arr2)));
        System.out.println(Arrays.toString(twoSumForSubArray(arr3, 2)));

    }

    //Two Sum
    public static int[] twoSumForSubArray(int[] arr, int target) {

//      {1,2,-1,2,3,-2,4};
        Map<Integer, Integer> map= new HashMap<>();
        int sum=0;
        int count=0;
        map.put(0,1);

        for(int a: arr){

            sum=sum+a;
            if(map.containsKey(sum-target)){
                count=count+ map.get(sum-target);
            }
            map.put(sum,a);
        }
        return null;
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
    // int[] arr1 = {12,7,11,13};
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
    public static List<List<Integer>> twoSum(int[] arr, int target) {

        //[2,3,11,15,7]
        List<List<Integer>> list= new ArrayList<>();
        Map<Integer, Integer> map= new HashMap<>();

        for(int num: arr){
            int key=target-num;
            map.put(key,num);
            if (map.containsKey(num)) {
               list.add(List.of(map.get(num),num));
            }
        }
        return list;
    }

}
