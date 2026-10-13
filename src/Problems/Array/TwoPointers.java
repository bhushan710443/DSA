package Problems.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TwoPointers {

    public static void main(String[] args){
        int[] list1 = {1, 2, 4, 5, 6, 7, 8, 9, 10};
        int[] list  = {2, 2, 3, 4, 2, 5, 1, 2, 3, 4, 5};

        System.out.println(twoSumSorted(list1, 10));
        System.out.println(Arrays.toString(reverseArray(list)));
        System.out.println(maxWaterContainer(list));

        //find max substring with not repeating subarray
        System.out.println(maxNonRepeatingCharSubString("abcdabcdrghssaa"));

    }

    //find max substring with not repeating subarray
    public static String maxNonRepeatingCharSubString(String s) {

       char[] ch= s.toCharArray();

       int left=0;
       int pointer=0;
        HashMap<Character, Integer> map= new HashMap<>();

         while(pointer<ch.length){
             map.put(ch[left],1);
             if(map.containsKey(ch[left])){
             }
         }

        return null;
    }



        // 1. Two Sum - Sorted Array
    // Given a sorted array, find two numbers that add up to target.
    // Returns the 1-based indices of the two numbers, or null if not found.
    // Time: O(n), Space: O(1)
    public static ArrayList twoSumSorted(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        ArrayList<Integer> result = new ArrayList<>();

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                result.add(arr[left]);
                result.add(arr[right]);
                return result;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }



    // 2. Reverse Array In-Place
    // Reverses the array without using extra space.
    // Time: O(n), Space: O(1)
    public static int[] reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return arr;
    }

    // 3. Is Palindrome
    // Checks if a string is a palindrome (ignores non-alphanumeric characters, case-insensitive).
    // Time: O(n), Space: O(
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 4. Container With Most Water
    // Given heights of walls, find two lines that form the container holding the most water.
    // Returns the maximum area.
    // Time: O(n), Space: O(1)
    public static int findMaxWaterContainer(int[] heights) {
        int left = 0, right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // 5. Remove Duplicates from Sorted Array
    // Removes duplicates in-place and returns the count of unique elements.
    // Time: O(n), Space: O(1)
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;

        int slow = 0;

        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow]) {
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return slow + 1;
    }

    // 6. Three Sum
    // Finds all unique triplets in the array that sum to zero.
    // Time: O(n²), Space: O(1) excluding output
    public static int[][] threeSum(int[] arr) {
        Arrays.sort(arr);
        java.util.ArrayList<int[]> result = new java.util.ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue; // skip duplicates

            int left = i + 1, right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    result.add(new int[]{arr[i], arr[left], arr[right]});
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result.toArray(new int[0][]);
    }

//    public static Integer waterRetention() {
//        //  Input:  [0,1,0,2,1,0,1,3,2,1,2,1]
//        //  Output: 6
//
//        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int i =0;
//        int j =
//
//
//        while()
//
//
//
//            return null;
//    }

//    int[] list = {1, 2, 3, 5, 2, 5, 1, 2, 3, 4, 5};
    public static int maxWaterContainer(int[] arr) {
        int num=0;
        int maxNum=0;
        int left = 0, right = arr.length - 1;

        while (left < right) {

            num=arr[left]*arr[right];
            if(maxNum<num)maxNum=num;
            if(arr[left]<arr[right]){
                left++;
            }
            if((arr[left]>arr[right]) || (arr[left]==arr[right])){
                right--;
            }

        }
        return maxNum;
    }
}
