package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Table;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRes implements Initializable {

    @FXML
    private GridPane grid;
    private ArrayList<Table> tables = new ArrayList<>();

    private ArrayList<Table> getData() {
        ArrayList<Table> tables1 = new ArrayList<>();
        Table table;
        for (int i = 0; i < 10; i++) {
            table = new Table(i, "Trống", 1000);
            tables1.add(table);
        }
        return tables1;
    }
public void test(ActionEvent event){

}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tables.addAll(getData());
        int colum = 0;
        int row = 1;

        try {
            for (int i = 0; i < tables.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ControllerItem controllerItem = new ControllerItem();
//                controllerItem.setData(tables.get(i));
                if (colum==3){
                    colum=0;
                    row++;
                }
                grid.add(anchorPane,colum++,row);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
