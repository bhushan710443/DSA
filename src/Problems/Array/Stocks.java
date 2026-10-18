package Problems.Array;

public class Stocks {

    public static void main(String[] args){
        int[] arr = {7, 1, 11, 3, 2, 15};

        System.out.println(bestTimeToBuy(arr));
        System.out.println(secondHeighestNumber(arr));

    }

    public static int bestTimeToBuy(int[] arr) {

        int lowValue=Integer.MAX_VALUE;
        int maxProfit=0;
        int profit=0;
        for(int a : arr){
            lowValue=Math.min(lowValue, a);
            profit= a-lowValue;
            maxProfit=Math.max(maxProfit,profit);
        }
        return maxProfit;
}

// without sorting          int[] arr = {7, 1, 11, 3, 2, 15};
public static int secondHeighestNumber(int[] arr) {

        int higest=0;
        int secHighest=0;

        for (int a : arr){

            if(a>higest){
                secHighest=higest;
                higest=a;
            }else if(a>secHighest && a<higest){
                secHighest=higest;
            }

        }
        return secHighest;
}
}
