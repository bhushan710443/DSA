package Problems.Array;

import java.util.*;

public class ArrayProb {

    public static void main(String[] args){
        int[] arr = {2, 2, 3, 4, 2, 5, 1, 2, 3, 4, 5};
        System.out.println(kthLargest(arr, 1));
        System.out.println(findDuplicates(arr));
        System.out.println(findNthDuplicates(arr, 2));
        System.out.println(findLuhn());
    }

    public static String kthLargest(int[] arr, int k) {

       Optional<Integer> a = Arrays.stream(arr)
                  .distinct()
                  .boxed()
                  .sorted(Comparator.reverseOrder())
                  .skip(k-1)
                  .findFirst();

     return a.toString();
    }


    public static Set findDuplicate(int[] arr) {

        Set<Integer> duplicates = new HashSet<>();
        Set<Integer> ar = new HashSet<>();

        for(Integer i : arr){
            ar.add(i);
        }
        for(Integer i : arr){
            if(ar.contains(i)){
                duplicates.add(i);
            }
        }
        return duplicates;
    }

    public static ArrayList findDuplicates(int[] arr) {

        ArrayList<Integer> duplicates = new ArrayList<>();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for(int num : arr){
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }


        for(int num : countMap.keySet()){
            if(countMap.get(num) > 1){
                duplicates.add(num);
            }
        }
        return duplicates;
    } 
    
    public static Integer findNthDuplicates(int[] arr, int n) {

        HashMap<Integer, Integer> countMap = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : countMap.keySet()) {
            if (countMap.get(num) > 1) {
                count++;
                if (count == n) {
                    return num;
                }
            }
        }
        return null;
    }


    //luhn number
//    The algorithm:
//    Takes a number
//    Doubles every second digit from the right
//    Adds all digits together
//    If total is divisible by 10, it’s valid


    public static boolean findLuhn()  {
        int []  arr= {4,5,3,9,1,4,8,8,0,3,4,3,6,4,6,7};
        int total=0;

       for(int a=arr.length-2; a>=0; a-=2){
            arr[a]=arr[a]*2;
            if(arr[a]>9){
                arr[a]=arr[a]-9;
            }
       }

        for (int i : arr) {
            total += i;
        }

       if(total%10==0){
           return true;
       }
        return false;
    }


}
