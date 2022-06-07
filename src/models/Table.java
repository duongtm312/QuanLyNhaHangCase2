package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {
    private int numberTable;
    private String status;
    private double bill;
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Table(int numberTable, String status, double bill) {
        this.numberTable = numberTable;
        this.status = status;
        this.bill = bill;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBill() {
        return String.valueOf(bill);
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Table{" +
                "numberTable=" + numberTable +
                ", status='" + status + '\'' +
                ", bill=" + bill +
                '}';
    }
}
