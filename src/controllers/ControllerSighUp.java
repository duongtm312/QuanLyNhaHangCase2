package controllers;

import io.ReaderAndWriteStudent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;
import validate.CheckString;

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
    private Label label;
    @FXML
    private Button button;
    private ArrayList<Account> accounts = ReaderAndWriteStudent.reader("D:\\CodeGym\\CaseModul2\\quanlynhahang\\src\\controllers\\account.csv");

    public void submitNewAcc(ActionEvent event) {
        String user = newUser.getText();
        String pass = newPass.getText();
        String passCheck = newPassCheck.getText();
        if (pass.equals(passCheck)) {
            if (checkUseNew(user)&& CheckString.CheckString(user)&&CheckString.CheckString(pass)) {
                accounts.add(new Account(user, pass));
                label.setText("Create Account Success");
                login();
                ReaderAndWriteStudent.Write(accounts, "D:\\CodeGym\\CaseModul2\\quanlynhahang\\src\\controllers\\account.csv");
            } else {
                label.setText("Account creation failed");
            }
        } else {
            label.setText("Password incorrect");
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
    public void  login(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Login.fxml"));
            Stage sighUpStage = new Stage();
            sighUpStage.initStyle(StageStyle.DECORATED);
            sighUpStage.setScene(new Scene(root));
            sighUpStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
