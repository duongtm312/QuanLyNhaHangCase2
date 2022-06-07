package controllers;

import Main.Mylistener;
import data.TableMain;
import io.ReaderAndWriteTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Product;
import models.Table;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerOrder {
    @FXML
    private GridPane grid;
    @FXML
    private Button button;
    @FXML
    private Label name;
    @FXML
    private Label menuOrder;
    @FXML
    private Label payMoney;
    private Mylistener mylistener;
    private ArrayList<Product> products = new ArrayList<>();
    private Table table;

    public void setData(Table table1) {
        table = table1;
        name.setText("Bàn " + table1.getNumberTable());
        productMenu();
    }

    private ArrayList<Product> getData() {
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        product = new Product("Cơm rang rưa bò", "CRRB", 30000, "../img/doan/comrang.jpg");
        products.add(product);
        product = new Product("Phở bò Nam Định", "PBNĐ", 40000, "../img/doan/phobo.jpg");
        products.add(product);
        product = new Product("Bún cá cay Hải Phòng", "BCCHP", 40000, "../img/doan/bunca.jpg");
        products.add(product);
        product = new Product("Gà nướng Tây Bắc", "GNTB", 150000, "../img/doan/ganuong.jpg");
        products.add(product);
        product = new Product("Lẩu lòng bò", "LLB", 300000, "../img/doan/laubo.jpg");
        products.add(product);
        product = new Product("Lẩu Thập cẩm", "LTC", 450000, "../img/doan/lauthapcam.jpg");
        products.add(product);
        product = new Product("Lẩu Hải sản", "LHC", 850000, "../img/doan/lauhaisan.jpg");
        products.add(product);
        product = new Product("Coca cola", "CCL", 10000, "../img/doan/coca.jpg");
        products.add(product);
        product = new Product("Bia tiger", "BiTG", 20000, "../img/doan/bia.jpg");
        products.add(product);
        product = new Product("Nước lọc", "NcL", 8000, "../img/doan/nuoc.jpg");
        products.add(product);
        return products;
    }

    private void setMenuOrder(Table table) {
        ArrayList<Product> products1 = table.getProducts();
        String str = "";
        String strPay = "";
        double pay = 0;
        for (Product pt : products1
        ) {
            str += pt.toString() + "\n";
            pay += pt.getPrice();
        }
        menuOrder.setText(str);
        payMoney.setText(pay + strPay + "VNĐ");
    }

    public void productMenu() {
        setMenuOrder(table);
        mylistener = new Mylistener() {
            @Override
            public void onClickLiestener(Table table) {
                setMenuOrder(table);
            }
        };

        int colum = 0;
        int row = 1;
        products.addAll(getData());
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../display/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ControllerProduct controllerProduct = fxmlLoader.getController();
                controllerProduct.setData(products.get(i), mylistener);
                controllerProduct.setTable(table);
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


    public void payOrder(ActionEvent event) {
        table.setPrd(new ArrayList<Product>());
        menuOrder.setText("");
        payMoney.setText("0.0VNĐ");
        ReaderAndWriteTable.write(TableMain.tables, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\table.csv");
    }

    public void delete(ActionEvent event) {
        int index = table.getProducts().size() - 1;
        if (index > 0) {
            table.getProducts().remove(index);
            setMenuOrder(table);
        }

    }
}
