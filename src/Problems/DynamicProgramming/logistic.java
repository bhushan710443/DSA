package Problems.DynamicProgramming;

import java.util.Arrays;

public class logistic {

    public static void main(String[] args) {

//        A warehouse truck departs every evening. All cartons are the same size — one carton takes one slot — but carton
//        types hold different unit counts.
//                Given boxTypes[i] = [cartonsAvailable, unitsPerCarton] and truckSize (max cartons),
//        return the maximum units loadable. Partial types allowed.
//                Up to 1,000 types; counts and units ≤ 1,000; truckSize ≤ 10⁶.
//
//        Plain Text
//                [[1,3],[2,2],[3,1]], 4 → 8
//                [[5,10],[2,5],[4,7],[3,9]], 10 → 91
//                [[2,4]], 100 → 8

        int[][] boxTypes1 = {{1, 3}, {2, 2},{3, 1}};
        System.out.println(maximumUnits(boxTypes1, 4));
        // 8

        int[][] boxTypes2 = {{5, 10}, {2, 5}, {4, 7}, {3, 9}};
        System.out.println(maximumUnits(boxTypes2, 10));
        // 91

        int[][] boxTypes3 = {{2, 4}};
        System.out.println(maximumUnits(boxTypes3, 100));
        // 8
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {

        // int[][] boxTypes2 = {{5, 10}, {2, 5}, {4, 7}, {3, 9}};
        // Sort by units per carton in descending order
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int totalUnits=0;

        for(int[] box : boxTypes){

            int cartoonsAvailable=box[0];
            int numberOfUnits=box[1];

            int currentUnit=Math.min(cartoonsAvailable, truckSize);

            totalUnits = totalUnits + (currentUnit * numberOfUnits);

            truckSize = truckSize - currentUnit;
        }
        return totalUnits;
    }
}