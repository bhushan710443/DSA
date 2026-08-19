package Problems.HashMap;

import java.util.HashMap;

public class HashMapQuestions {

    public static void main(String[] args) {
        int[] list = {2, 2, 3, 4, 2, 5, 1, 2, 3, 4, 5};
        System.out.println("HashMap Questions");
        System.out.println(findKThDublicate(list, 3));
    }

    // find kth dublicate element
    public static int findKThDublicate(int[] arr, int k) {

        HashMap<Integer, Integer> countMap = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : countMap.keySet()) {
            if (countMap.get(num) > 1) {
                count++;
            }
            if (count == k) {
                return num;
            }
        }
        return -1;
    }
}
