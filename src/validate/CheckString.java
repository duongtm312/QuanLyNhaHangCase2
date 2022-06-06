package validate;

public class CheckString {
    public static boolean CheckString(String str){
        if (str.matches("[a-zA-Z1-9]{3,10}")){
            return true;
        }else {
            return false;
        }
    }
}
