package Problems.Integer;

public class Integers {

    public static void main(String[] args){

        System.out.println(reverseInt(123)); //321

        double x = 2.00000;
        int n = 10;
        System.out.println(myPow(x, n));
    }

    public static int reverseInt(int x) {
        int digit;
        int result = 0;
        while (x != 0) {
            digit = x % 10;
            x=x/10;

            // Check integer overflow
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }

            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }
        return result;
    }




    public static double myPow(double x, int n) {

        long power = n;

        // Handle negative exponent
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1.0;

        while (power > 0) {

            // If power is odd, multiply result by x
            if (power % 2 == 1) {
                result = result * x;
            }

            // Square x
            x = x * x;

            // Divide power by 2
            power = power / 2;
        }

        return result;
    }


}


