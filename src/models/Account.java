package models;

public class Account {
    private String UserName;
    private String Password;
    private String Mail;
    private String control;

    public Account(String userName, String password,String mail,String control) {
        UserName = userName;
        Password = password;
        Mail = mail;
        this.control = control;

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;

    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String write(){
        return UserName +","+Password+","+Mail+","+control;
    }
}
