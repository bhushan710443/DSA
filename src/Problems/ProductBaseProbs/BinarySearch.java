package Problems.ProductBaseProbs;

public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int[] arr1 = {3, 4, 5, 1, 2};
        binarySearch(arr, 1);
        searchInRotatedSortedArray(arr1, 1);
        findMin(arr1);
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // avoids overflow
            int value = nums[mid];

            if (value == target) {
                System.out.println(mid);
                return mid;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    // when two different sequence is in sorted manner.
    //int[] arr1 = {3, 4, 5, 1, 2};
    public static int searchInRotatedSortedArray(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                System.out.println(mid);
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Minimum is in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // Minimum is at mid or in the left half
            else {
                right = mid;
            }
        }

        System.out.println(nums[left]);
        return nums[left];
    }



}
