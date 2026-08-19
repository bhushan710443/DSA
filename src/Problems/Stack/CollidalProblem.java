package Problems.Stack;

import java.util.Stack;

public class CollidalProblem {


    public static void main(String[] args) {

        int[] arr= {2,4,-1,3,5,-3,4};
        System.out.println(findRemainingElementsAfterCollid(arr));
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
