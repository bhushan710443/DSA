package Problems.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsStrings {

    public static void main(String[] args) {

        String str = "BHUSHANA";
        System.out.println(findDublicateChar(str));

//      Find the first non-repeating character in a string.
        findFirstNonRepeating(str);
        firstRepeatingCharacterInString(str);
//        Count the frequency of each character in a string.
        System.out.println(countTheFrequencyOfEachCharacterInString(str));

        String txt="Bhushan Dattatrya Varpe Is my name And I am Java Developer";

        // find the words with more than 5 unique caracters with streams
        // all the words should be in upper case
        write(txt);


//        Find duplicate words in a sentence.
        String str1= "India is my country , I Love India ..  India Country";
        findDuplicateWordsInSentence(str1);

//        Find the longest word in a sentence.
        findTheLongestWordInSentence(txt);


        sortStringsByLength(txt);
        sortStringsByLengthInReverse(txt);

        String[] arr= {"India", "is", "my", "country", "I", "Love", "India", "India", "Country"};
        findWordsContainingUniqueCharacters(arr);
    }


    public static String findDublicateChar(String str){

        return str.chars()
                .mapToObj(a -> (char) a)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(a -> a.getValue()>1)
                .map(entry -> entry.getKey().toString())
                .collect(Collectors.joining(", "));

    }

//    Find the first repeating character in a string.
    public static void firstRepeatingCharacterInString(String str){


        Character ch=str.chars()
                .mapToObj(a -> (char) a)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(a -> a.getValue()>1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("Find the first repeating character in a string. = "+ch);
    }


 public static void findFirstNonRepeating(String str){

     str.chars()
                .mapToObj(a -> (char) a)
                .collect(Collectors.groupingBy(
                       Function.identity(),
                       LinkedHashMap:: new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(n -> n.getValue()==1)
                .forEach(System.out::println);

    }

    public static Map<Character, Long> countTheFrequencyOfEachCharacterInString(String str){

        Map<Character, Long> map= str.chars()
                .mapToObj(a->(char) a )
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        return map;
    }


    //        // find the words with more than 5 unique caracters with streams
    //        // all the words should be in upper case
//    String txt="Bhushan Dattatrya Varpe Is my name And I am Java Developer";

    public static void write(String str){
        System.out.println("START");
        Arrays.stream(str.split(" "))
                .filter(word->word.chars().distinct().count()>=5)
                .map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("END");
    }

    // find max salary of employee
    public static void findDuplicateWordsInSentence(String str){

        Arrays.stream(str.split(" "))
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(a-> a.getValue()>1)
                .forEach(System.out::println);

    }


    // Find the longest word in a sentence.
    public static void findTheLongestWordInSentence(String str){

        String longestWord = Arrays.stream(str.split("\\s+"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        System.out.println(longestWord);
    }

    // Sort strings by length.
    public static void sortStringsByLength(String str){
         List<String> a=Arrays.stream(str.split(" "))
                 .sorted(Comparator.comparingInt(String::length))
                 .toList();
         System.out.println(a.toString());
    }

    // Sort strings by length.in reverse
    public static void sortStringsByLengthInReverse(String str){
         Arrays.stream(str.split(" "))
                 .sorted(Comparator.comparingInt(String::length).reversed())
                 .forEach(System.out::println);
    }


    // Find words containing only unique characters.
    public static void findWordsContainingUniqueCharacters(String[] arr){

            List<String> arrr= Arrays.stream(arr)
                    .map(String::toUpperCase)
                    .filter(word-> word.chars().distinct().count()==word.length())
                    .toList();
            System.out.println(arrr.toString());
    }
}
