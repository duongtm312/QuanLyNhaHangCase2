package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Table;

public class ControllerItem {
    @FXML
    private Label numberTable;

    @FXML
    private Label status;

    @FXML
    private Label Bill;
    private Table table;

    public void setData(Table table) {
        this.table = table;
       numberTable.setText(""+table.getNumberTable());
       status.setText(table.getStatus());
       Bill.setText(""+table.getBill());
    }

}
