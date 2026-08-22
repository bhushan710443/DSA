package Problems.ProductBaseProbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {

    public static void main(String[] args) {

        String s="AABCBAA";
        String s1="aabcbaad";
        System.out.println(s + " is palindrome: " + vaidPalindrome(s));
        //can we make it palindrome by shuffeling the chars
        System.out.println("From this string " + s1 + " we can " + canMakePalindrome(s1) + " make palindrome");
        int[] nums={-1, 0, 1, 2, -1, -4};
        System.out.println("3 sum " + threeSum(nums) );

        //container with most water trap
        int[] num={1, 0, 1, 2, 1, 4};
        System.out.println("Container with most water trap " + containerWithMostWaterTrap(num));

        //Trapping Rain Water
        System.out.println("Trapping Rain Water " + trappingRainWater(num));
    }

    public static boolean  vaidPalindrome(String s) {
        int right=s.length() - 1;
        int left=0;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // can we make it palindrome by shuffeling the chars
    public static String canMakePalindrome(String s) {

        int[] chars = new int[26];
        int count=0;

        s = s.toLowerCase();
        for (int i=0; i<s.length();i++){
            chars[s.charAt(i)- 'a']++;
        }

       for (int i=0; i<26;i++){
           if(chars[i]%2!=0){
               count++;
           }
           if(count>1){
               return "NOT";
           }
       }
       return "";
    }


    // 3Sum
//    Input:  [-1, 0, 1, 2, -1, -4]

    public static List<List<Integer>> threeSum(int[] arr) {

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);


        for(int i=0; i< arr.length-2;i++){

            int sum=0;
            int left=i+1;
            int right= arr.length-1;

            while(left<right){

                sum= arr[i] + arr[left] +arr[right];

                if(sum==0){
                    res.add(List.of(arr[i], arr[left], arr[right]));

                    while(arr[left]== arr[left+1]){
                        left++;
                    }

                    while(arr[right]== arr[right-1]){
                        right--;
                    }

                    left++;
                    right--;

                }else if(sum>0){
                    right--;
                }else{
                    left++;
                }
            }
        }
       return res;
    }



    // Container with most water trap
//    int[] num={1, 0, 1, 2, 1, 4};
    public static int containerWithMostWaterTrap(int[] arr) {

        int right= arr.length-1;
        int left= 0;
        int maxArea=0;
        int area=0;

        while(left<right){
           int width= right-left;
           int height= Math.min(arr[right], arr[left]);
           area= width * height;
           maxArea=Math.max(area, maxArea);

           if(arr[right]<arr[left])
             right--;
           else
            left++;
        }

       return maxArea;
    }


    // Trapping Rain Water
//    int[] num={1, 0, 1, 2, 1, 4};
    public static int trappingRainWater(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }

                left++;

            } else {

                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }

                right--;
            }
        }

        return water;
    }






}
