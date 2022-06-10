package controllers;

import Main.Mylistener;
import io.ReaderAndWriteAcc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;
import validate.Validate;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerSighUp {
    @FXML
    private TextField newUser;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField newPassCheck;
    @FXML
    private TextField mail;
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button button2;
    private ArrayList<Account> accounts = ReaderAndWriteAcc.reader("D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\account.csv");
    ;
    public void submitNewAcc(ActionEvent event) {
        String user = newUser.getText();
        String pass = newPass.getText();
        String passCheck = newPassCheck.getText();
        String m = mail.getText();
        if (Validate.ValidateString(user) && checkUseNew(user)) {
            if (pass.equals(passCheck) && Validate.ValidateString(pass)) {
                if (Validate.ValidateMail(m)) {
                    accounts.add(new Account(user, pass, m,user));
                    label.setText("Create Account Success");
                    ReaderAndWriteAcc.Write(accounts, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\account.csv");
                    login();
                } else {
                    label.setText("Mail is incorrect");
                }
            } else {
                label.setText("Password incorrect");
            }

        } else {
            label.setText("This account is Invalid");
        }
    }

    public boolean checkUseNew(String us) {
        boolean check = true;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(us)) {
                check = false;
                break;
            }
        }
        return check;
    }

    public void login() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../display/Login.fxml"));
            HBox hBox = fxmlLoader.load();
            ControllerLogin controllerLogin = fxmlLoader.getController();
            controllerLogin.setData(accounts);
            Stage login = new Stage();
            login.setScene(new Scene(hBox));
            login.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void  signIn(ActionEvent event){
        login();
    }

}
