package controllers;

import Main.Mylistener;
import data.TableMain;
import io.ReaderAndWriteTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.Bill;
import models.Table;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRes implements Initializable {
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label wellCome;
    @FXML
    private Button logOut;
    @FXML
    private Label sales;


    public ArrayList<Table> tables = TableMain.tables;
    public ArrayList<Bill> bills = ReaderAndWriteTable.readerBill("D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\bill.csv");
    private Mylistener mylistener;

    public void test(ActionEvent event) {

    }


    public void write() {
        ReaderAndWriteTable.write(tables, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\table.csv");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resDisplay();
    }

    public void addTable(ActionEvent event) {
        Table table = new Table(tables.size() + 1, "Đang Trống", 0);
        tables.add(table);
        write();
        grid.getChildren().clear();
        resDisplay();
    }

    public void deleteTable(ActionEvent event) {
        if (tables.size() > 1) {
            tables.remove(tables.size() - 1);
            for (Table t : tables
            ) {
                System.out.println(t);
            }
            grid.getChildren().clear();
            resDisplay();
            write();
        }
    }

    public void resDisplay() {
        mylistener = new Mylistener<ArrayList<Bill>>() {
            @Override
            public void onClickLiestener(ArrayList<Bill> o) {
                setBills(o);
            }
        };
        grid.getChildren().removeAll();
        int colum = 0;
        int row = 1;
        try {
            for (int i = 0; i < tables.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../display/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ControllerItem controllerItem = fxmlLoader.getController();
                controllerItem.setData(tables.get(i), mylistener);
                if (colum == 3) {
                    colum = 0;
                    row++;
                }
                grid.add(anchorPane, colum++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(String text) {
        wellCome.setText("WellCome: " + text);
    }

    public void logOut(ActionEvent event) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../display/Login.fxml"));
            HBox hBox = fxmlLoader.load();
            Stage login = new Stage();
            login.setScene(new Scene(hBox));
            login.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBills(ArrayList<Bill> bill) {
        bills = bill;
    }
    public void displaySales(){
        String str = "Doanh thu tháng 6 : \n";
        double sale =0;

        for (Bill b:bills
             ) {
            sale+=b.getSales();
        }
        sales.setText(str+sale+"VNĐ");
    }

}
