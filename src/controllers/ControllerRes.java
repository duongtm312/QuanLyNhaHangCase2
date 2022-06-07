package controllers;

import Main.Mylistener;
import data.TableMain;
import io.ReaderAndWriteTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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
    public ArrayList<Table> tables = TableMain.tables;
    public void test(ActionEvent event) {

    }

    public void write(ActionEvent event) {
        ReaderAndWriteTable.write(tables, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\table.csv");
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
        grid.getChildren().removeAll();
        int colum = 0;
        int row = 1;
        try {
            for (int i = 0; i < tables.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../display/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ControllerItem controllerItem = fxmlLoader.getController();
                controllerItem.setData(tables.get(i));
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

}
