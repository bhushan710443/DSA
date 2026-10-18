package Problems.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsArray {

    public static void main(String[] args) {

        int[] arr= {1,2,25,24,12,4,7,25};

        System.out.println( findSecondHighestNumberInList(arr));
        System.out.println( Arrays.deepToString(findDuplicateNumbersInList(arr)));
        System.out.println( findEvenAndOddNumbersInList(arr));
        System.out.println( findMAXNumberInList(arr));
        System.out.println( findSUMNumberInList(arr));
        System.out.println( findAVGNumberInList(arr));
        System.out.println( findNumbersStartingWithDigit1(arr));
        System.out.println( findTopThreeLargestNumbers(arr));
    }

//    Find the second-highest number in a list.
     static int findSecondHighestNumberInList(int[] arr){
          return Arrays.stream(arr)
                  .distinct()
                  .boxed()
                  .sorted(Comparator.reverseOrder())
                  .skip(1)
                  .findFirst()
                  .orElse(0);

    }

    //    Find duplicate numbers in a list.
     static Object[] findDuplicateNumbersInList(int[] arr){

           return Arrays.stream(arr)
                  .boxed()
                  .collect(Collectors.groupingBy(
                          Function.identity(),
                          LinkedHashMap::new,
                          Collectors.counting()
                  ))
                  .entrySet()
                  .stream()
                  .filter(a->a.getValue()>1)
                   .distinct()
                   .toArray();
    }

    //    Find even and odd numbers.
     static Map<String,List<Integer>> findEvenAndOddNumbersInList(int[] arr){
         return Arrays.stream(arr)
                .boxed()
                 .collect(Collectors.groupingBy(
                         a-> a%2==0 ? "EVEN" : "ODD"
                 ));
     }

     //    Find MAX numbers.
     static int findMAXNumberInList(int[] arr){
         return Arrays.stream(arr)
                 .max()
                 .orElse(0);
     }

     //    Find SUM numbers.
     static int findSUMNumberInList(int[] arr){
         return Arrays.stream(arr)
                 .sum();

     }

     //    Find AVG numbers.
     static double findAVGNumberInList(int[] arr){
         return Arrays.stream(arr)
                 .average()
                 .orElse(0);

     }

     //    Find numbers starting with digit 1.
     static List<Integer> findNumbersStartingWithDigit1(int[] arr){
         return Arrays.stream(arr)
                 .boxed()
                 .filter(num -> String.valueOf(num).startsWith("1"))
                 .toList();

     }

     //    Find the top three largest numbers.
     static List<Integer> findTopThreeLargestNumbers(int[] arr){
         return Arrays.stream(arr)
                 .boxed()
                 .distinct()
                 .sorted(Comparator.reverseOrder())
                 .limit(3)
                 .toList();

     }


}
