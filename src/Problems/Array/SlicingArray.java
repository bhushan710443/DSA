package Problems.Array;

import java.util.Arrays;

public class SlicingArray {

    public static void main(String[] args) {

        int[] list = {2, 2, 3, 4, 2, 5, 1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(sliceTheArray(list)));
    }

    //from -> Included
    //to -> excluded
    private static int[] sliceTheArray(int[] list) {
        int [] newArr = Arrays.copyOfRange(list,2,4);
        return newArr;
    }


}
