package Problems.Stack;

import java.util.Stack;

public class RemoveAdjacentK {

    public static void main(String[] args) {
        System.out.println(removeDuplicates("ABBCCCB", 3));
    }

    static class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (!stack.isEmpty() && stack.peek().ch == c) {
                stack.peek().count++;

                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Pair p : stack) {
            for (int i = 0; i < p.count; i++) {
                sb.append(p.ch);
            }
        }

        return sb.toString();
    }

}