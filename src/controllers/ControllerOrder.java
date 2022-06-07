package controllers;

import io.ReaderAndWriteTable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerOrder implements Initializable {
    @FXML
    private GridPane grid;
    private ArrayList<Product> products = new ArrayList<>();

    private ArrayList<Product> getData() {
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        product = new Product("Cơm rang rưa bò", 30000, "../img/doan/comrang.jpg");
        products.add(product);
        product = new Product("Phở bò Nam Định", 40000, "../img/doan/phobo.jpg");
        products.add(product);
        product = new Product("Bún cá cay Hải Phòng", 40000, "../img/doan/bunca.jpg");
        products.add(product);
        product = new Product("Gà nướng Tây Bắc", 150000, "../img/doan/ganuong.jpg");
        products.add(product);
        product = new Product("Lẩu lòng bò", 300000, "../img/doan/laubo.jpg");
        products.add(product);
        product = new Product("Lẩu Thập cẩm", 450000, "../img/doan/lauthapcam.jpg");
        products.add(product);
        product = new Product("Lẩu Hải sản", 850000, "../img/doan/lauhaisan.jpg");
        products.add(product);
        product = new Product("Coca cola", 10000, "../img/doan/coca.jpg");
        products.add(product);
        product = new Product("Bia tiger", 20000, "../img/doan/bia.jpg");
        products.add(product);
        product = new Product("Nước lọc", 8000, "../img/doan/nuoc.jpg");
        products.add(product);
        return products;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int colum = 0;
        int row = 1;
        products.addAll(getData());
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../display/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ControllerProduct controllerProduct = fxmlLoader.getController();
                controllerProduct.setData(products.get(i));
                if (colum == 3) {
                    colum = 0;
                    row++;
                }
                grid.add(anchorPane, colum++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
