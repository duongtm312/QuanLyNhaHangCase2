package models;

public class Table {
    private int numberTable;
    private String status;
    private double bill;

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
}
