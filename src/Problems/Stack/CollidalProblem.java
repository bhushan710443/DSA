package Problems.Stack;

import java.util.Stack;

public class CollidalProblem {


    public static void main(String[] args) {

        String str= "3[a2[c]]"; //accaccacc
        System.out.println(findString(str));


        int[] arr= {2,4,-1,3,5,-3,4};
        System.out.println(findRemainingElementsAfterCollid(arr));
    }




public static String findString(String str){

    Stack<Integer> noStack=  new Stack<>();
    Stack<String> charStack=  new Stack<>();

    char[] chars= str.toCharArray();
    int currentNumber = 0;
    String currentString = "";

    for(char ch : chars ){

        if(Character.isDigit(ch)){
            currentNumber = currentNumber * 10 + (ch - '0');
        }

        else if(ch=='['){
            noStack.push(currentNumber);
            charStack.push(currentString);
            currentNumber = 0;
            currentString = "";
        }

        else if(ch==']'){
            int repeat = noStack.pop();
            String previousString = charStack.pop();
            StringBuilder temp = new StringBuilder(previousString);

            for (int i = 0; i < repeat; i++){
                // Add the decoded content to our result.
                temp.append(currentString);
            }
            currentString = temp.toString();
        }

        else {
            currentString += ch;
        }

      }

    return currentString;
}




    //if - then it should be minus from the bigger
    //- signe should say its direction
    // same signe can not collide
    // same size should removed

    public static int[] findRemainingElementsAfterCollid(int[] arr){

        Stack<Integer> stack=new Stack<>();

        for(int i=0; i< arr.length ; i++){

            if(arr[i]> stack.peek()){
                int n=stack.peek();
                if (Math.signum(arr[i]) == -1) {
                   stack.push(arr[i]-n);
                }else{
                    stack.push(n+arr[i]);
                }

            } else if(arr[i] < stack.peek()) {
                int n=stack.peek();
                if (Math.signum(arr[i]) == 1) {
                    stack.push(n-arr[i]);
                }else{
                    stack.push(n+arr[i]);
                }
            }else{
                stack.pop();
            }



        }



        return arr;
    }

}
