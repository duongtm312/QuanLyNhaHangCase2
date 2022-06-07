package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Product;
import models.Table;

public class ControllerProduct {
    @FXML
    private ImageView img;
    @FXML
    private Label name;

    @FXML
    private Label price;
    private Product product;
    public void setData(Product product) {
        this.product = product;
        name.setText(""+product.getName());
        price.setText(product.getPrice()+"VNĐ");
        Image image = new Image(getClass().getResourceAsStream(product.getImg()));
        img.setImage(image);
    }

}
