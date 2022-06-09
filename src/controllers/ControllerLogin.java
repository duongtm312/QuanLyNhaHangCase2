package controllers;

import Main.Mylistener;
import io.ReaderAndWriteAcc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    private ArrayList<Account> accounts = ReaderAndWriteAcc.reader("D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\account.csv");
    public void submit(ActionEvent event){
        String us = userName.getText();
        String ps = passWord.getText();
        if (CheckAcc(us,ps)){
            sighIn();
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../display/SighUp.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Stage sighup = new Stage();
            sighup.setScene(new Scene(anchorPane));
            sighup.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sighIn(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../display/restaurant.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ControllerRes controllerRes = fxmlLoader.getController();
            controllerRes.setData(userName.getText());
            Stage sighUpStage = new Stage();
            sighUpStage.initStyle(StageStyle.DECORATED);
            sighUpStage.setScene(new Scene(anchorPane));
            sighUpStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
