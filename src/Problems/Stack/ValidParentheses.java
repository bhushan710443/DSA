package Problems.Stack;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(validParentheses("[][][{()}]")); // true
        System.out.println(validParentheses("[(])"));       // false
        System.out.println(validParentheses("((()))"));     // true
        System.out.println(validParentheses("(()"));        // false
    }

    public static boolean validParentheses(String str) {

        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {

            // If it is an opening bracket, push it.
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {

                // A closing bracket cannot match an empty stack.
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check whether the brackets match.
                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }

        // Valid only if every opening bracket was matched.
        return stack.isEmpty();
    }
}
