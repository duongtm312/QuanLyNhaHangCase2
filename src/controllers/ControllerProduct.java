package controllers;

import Main.Mylistener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Product;
import models.Table;

public class    ControllerProduct {
    @FXML
    private ImageView img;
    @FXML
    private Label name;

    @FXML
    private Label price;
    private Product product;
    private Table table;
    private Mylistener mylistener;

    public void setData(Product product, Mylistener mylistener) {

        this.mylistener = mylistener;
        this.product = product;
        name.setText("" + product.getName());
        price.setText(product.getPrice() + "VNĐ");
        Image image = new Image(getClass().getResourceAsStream(product.getImg()));
        img.setImage(image);

    }

    public void setTable(Table table) {
        this.table = table;
    }

    @FXML
    public void order(ActionEvent event) {
        table.setProducts(product);
        mylistener.onClickLiestener(table);
    }
}
