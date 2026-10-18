package Problems.Strings;

public class ReverseStrings {

    public static void main(String[] args) {
        String str = "Hello I Am Bhushan";
        System.out.println(str);
        System.out.println(reverseFullString(str));
        System.out.println(shuffalString(str));
        System.out.println(reverseOnlyWords(str));
    }

    public static String reverseFullString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    //output Bhushan Am I Hello
    public static String shuffalString(String str) {
        String[] words = str.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if(i != 0){
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    //output = olleH I mA nahsuhB
    public static String reverseOnlyWords(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            result.append(word.reverse());
            if (i != words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

}