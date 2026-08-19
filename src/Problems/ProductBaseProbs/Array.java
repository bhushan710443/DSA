package Problems.ProductBaseProbs;

import java.util.*;

public class Array {

    public static void main(String[] args) {

        int[] arr1 = {2, 7, 11, 15};
        String[] arr2 = {"eat", "tea", "tan", "ate", "nat", "bat"};

       twoSum(arr1, 9);
       twoSumPointer(arr1, 15);
       System.out.println(groupAnagrams(arr2));
       productOfArrayExceptSelf(new int[]{1, 2, 3, 4});
       longestConsecutiveSequence(new int[]{4, 2, 3, 1, 7});
       subarraySumEqualsK(new int[]{4, 2, 3, 1, 7},7);

    }

    public static void twoSum(int[] nums, int target) {

        Map<Integer,Integer> map=new HashMap<>();

       for (int i=0; i<nums.length; i++){

           map.put( nums[i],target-nums[i]);

           if(map.containsKey(target-nums[i])){
               System.out.println("Pair found: " + map.get(target-nums[i]) + ", " + (target-nums[i]));
           }
       }
    }

    public static void twoSumPointer(int[] nums, int target) {

        Map<Integer,Integer> map=new HashMap<>();

       for (int i=0; i<nums.length; i++){

           map.put(target-nums[i], i);

           if(map.containsKey(nums[i])){
               System.out.println("Pair found: " + map.get(nums[i]) + ", " + i);
           }
       }
    }


    public static ArrayList<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map=new HashMap<>();

        for(String str: strs){
          char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key= new String(charArray);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());

    }

//  int[] arr1 = {2, 7, 11, 15};
    public static void productOfArrayExceptSelf(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        // Left side product
        int left = 1;

        for (int i = 0; i < n; i++) {
            ans[i] = left;
            left = left * nums[i];
        }

        // Right side product
        int right = 1;

        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right = right * nums[i];
            System.out.println(ans[i]);
        }
    }

//    {4, 2, 3, 1, 7}
    public static void longestConsecutiveSequence(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for(int num : nums){

            if(!set.contains(num-1)){
                int currentNum = num;
                int currentStreak = 1;

                while(set.contains(currentNum + 1)){
                    currentNum += 1;
                    currentStreak += 1;
                }

                System.out.println("Longest consecutive sequence length: " + currentStreak);
            }
        }
    }

    //    {4, 2, 3, 1, 7}

    public static void subarraySumEqualsK(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(k- nums[i], i );
        }

        for(int num: nums){
            if(map.containsKey(num)){
                System.out.println("Subarray found ending at index: " + nums[map.get(num)]);
            }
        }

    }

}
