package Problems.Strings;

public class Slicing {

    public static void main(String[] args) {

        String A="ASDFGHJKL";
        System.out.println(slice(A));
        System.out.println(getLastThree(A));
        String email= "rohit123@gmail.com";
        System.out.println(getUsername(email));
        String otp= "OTP-458921-VALID";
        System.out.println(getOtp(otp));

    }

    //Extract FGH
//    start → included
//    end → excluded
    private static String slice(String str){
        str=str.substring(3,6);
        return str;
    }

    //get last 3 char
    private static String getLastThree(String str){
        str=str.substring(str.length()-3);
        return str;
    }

    //"rohit123@gmail.com" takeout username
    private static String getUsername(String str){

        str=str.substring(0,str.length()-10);
        return str;
    }

    //"OTP-458921-VALID" takeout otp
    private static String getOtp(String str){
        str=str.substring(4,10);
        return str;
    }



}
