package controllers;

import Main.Mylistener;
import data.TableMain;
import io.ReaderAndWriteTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Bill;
import models.Product;
import models.Table;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
    @FXML
    private TextField textIdPr;
    @FXML
    private HBox hBoxInLink;

    @FXML
    private HBox hBoxInName;

    @FXML
    private TextField nameAdd;

    @FXML
    private TextField nameIdAdd;
    @FXML
    private TextField linkAdd;
    @FXML
    private TextField priceAdd;
    private Mylistener mylistener;
    private ArrayList<Product> products = ReaderAndWriteTable.readerPr("D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\product.csv");
    public ArrayList<Bill> bills = ReaderAndWriteTable.readerBill("D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\bill.csv");
    private Table table;
    private Mylistener mylistener2;
    private Mylistener mylistener3;

    public void setData(Table table1, Mylistener mylistener, Mylistener mylistenerRes) {
        table = table1;
        name.setText("Bàn " + table1.getNumberTable());
        productMenu();
        mylistener2 = mylistener;
        mylistener3 = mylistenerRes;
    }

    private double pay = 0;

    private void setMenuOrder(Table table) {
        ArrayList<Product> products1 = table.getProducts();
        String str = "";
        String strPay = "";
        pay = 0;
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
        mylistener = new Mylistener<Table>() {
            @Override
            public void onClickLiestener(Table table) {
                setMenuOrder(table);
            }
        };
        displayProduct();

    }

    public void displayProduct() {
        grid.getChildren().clear();
        int colum = 0;
        int row = 1;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thanh toán");
        alert.setHeaderText("Thanh toán " + "Bàn " + table.getNumberTable() + " : " + pay + "VNĐ");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            Bill bill = new Bill(new Date(), pay);
            bills.add(bill);
            pay = 0;
            table.setPrd(new ArrayList<Product>());
            menuOrder.setText("");
            payMoney.setText("0.0VNĐ");
            mylistener2.onClickLiestener(table);
            mylistener3.onClickLiestener(bills);
            write();
            ReaderAndWriteTable.writeBill(bills, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\bill.csv");
        }

    }

    public void delete(ActionEvent event) {
        int index = table.getProducts().size();
        if (index > 0) {
            table.getProducts().remove(index - 1);
            setMenuOrder(table);
        }
        write();
    }

    public void write() {
        ReaderAndWriteTable.write(TableMain.tables, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\table.csv");
    }

    public void deleteProduct(ActionEvent event) {
        String id = textIdPr.getText();
        int check = checkProduct(id);
        if (check != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa sản phẩm");
            alert.setHeaderText("Xóa sản phẩm " + products.get(check).getName());
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                products.remove(check);
                textIdPr.setText("");
                ReaderAndWriteTable.writePr(products, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\product.csv");
                displayProduct();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sai mã sản phẩm");
            alert.setHeaderText("Mãi sản phẩm không tồn tại");
            alert.show();
        }
    }

    public int checkProduct(String id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getNameVt().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void disPlayAdd(ActionEvent event) {
        hBoxInLink.setVisible(true);
        hBoxInName.setVisible(true);
    }
    public void addProduct(ActionEvent event){
        String id = nameIdAdd.getText();
        String name = nameAdd.getText();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Chức năng đang...");
//            alert.setHeaderText("Chức năng đang được hoàn thiện xin cảm ơn!!!");
//            alert.show();

        double price =-1;
        try {
            price = Double.parseDouble(priceAdd.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sai định dạng giá");
            alert.setHeaderText("Sai định dạng giá");
            alert.show();
        }
        String link = "../img/doan/load.gif";
        if (checkProduct(id)==-1&&price!=-1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thêm sản phẩm");
            alert.setHeaderText("Thêm sản phẩm " + name);
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                products.add(new Product(name,id,price,link));
                nameIdAdd.setText("");
                nameAdd.setText("");
                linkAdd.setText("");
                priceAdd.setText("");
                hBoxInLink.setVisible(false);
                hBoxInName.setVisible(false);
                ReaderAndWriteTable.writePr(products, "D:\\CodeGym\\CaseModul2\\QuanLyNhaHangCase2\\src\\data\\product.csv");
                displayProduct();
            }


        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mã sản phẩm tồn tại");
            alert.setHeaderText("Mã sản phẩm tồn tại");
            alert.show();
        }


    }
}
