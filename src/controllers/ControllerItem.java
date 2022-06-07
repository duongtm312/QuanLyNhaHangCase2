package controllers;

import Main.Mylistener;
import io.ReaderAndWriteTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Product;
import models.Table;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerItem {
    @FXML
    private Label numberTable;

    @FXML
    private Label status;
    private Table table;

    public void setData(Table table) {
        this.table = table;
        numberTable.setText("Bàn" + table.getNumberTable());
        status.setText(table.getStatus());
        if (table.getStatus().equals("Đang sử dụng")) {
            status.setTextFill(Color.RED);
        } else if (table.getStatus().equals("Đã đặt")) {
            status.setTextFill(Color.BLUE);
        }
    }

    public void setUsing(ActionEvent event) {
        table.setStatus("Đang sử dụng");
        status.setText("Đang sử dụng");
        status.setTextFill(Color.RED);
    }

    public void setEmpty(ActionEvent event) {
        table.setStatus("Đang Trống");
        status.setText("Đang Trống");
        status.setTextFill(Color.BLACK);
    }

    public void setReserve(ActionEvent event) {
        table.setStatus("Đã đặt");
        status.setText("Đã đặt");
        status.setTextFill(Color.BLUE);
    }

    public void menuOrder(ActionEvent event) {
        table.setStatus("Đang sử dụng");
        status.setText("Đang sử dụng");
        status.setTextFill(Color.RED);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../display/order.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ControllerOrder controllerOrder = fxmlLoader.getController();
            controllerOrder.setData(table);
            Stage menuOrder = new Stage();
            menuOrder.setScene(new Scene(anchorPane));
            menuOrder.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
