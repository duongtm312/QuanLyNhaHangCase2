package validate;

public class Validate {
    public static boolean ValidateString(String str){
        if (str.matches("[a-zA-Z1-9]{6,10}")){
            return true;
        }else {
            return false;
        }
    }
    public static boolean ValidateMail(String mail){
        if (mail.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z]+){1,2}$")){
            return true;
        }else {
            return false;
        }
    }
}
