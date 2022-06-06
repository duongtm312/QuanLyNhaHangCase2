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

import java.io.IOException;
import java.util.ArrayList;

public class ControllerLogin {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Label accountDNE;
    @FXML
    private Button button;
    private ArrayList<Account> accounts = ReaderAndWriteStudent.reader("D:\\CodeGym\\CaseModul2\\quanlynhahang\\src\\controllers\\account.csv");
    public void submit(ActionEvent event){
        String us = userName.getText();
        String ps = passWord.getText();
        if (CheckAcc(us,ps)){
            System.out.println("In");
        }else {
            accountDNE.setVisible(true);
        }
    }
    public boolean CheckAcc(String user,String ps){
        for (int i = 0; i <accounts.size() ; i++) {
            if (accounts.get(i).getUserName().equals(user)){
                if (accounts.get(i).getPassword().equals(ps)){
                    return true;
                }
            }
        }
        return false;
    }
    public void  sighUp(ActionEvent event){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../item.fxml"));
            Stage sighUpStage = new Stage();
            sighUpStage.initStyle(StageStyle.DECORATED);
            sighUpStage.setScene(new Scene(root));
            sighUpStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
