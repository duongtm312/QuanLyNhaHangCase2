package models;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    Date date;
    double sales;

    public Bill(Date date, double sales) {
        this.date = date;
        this.sales = sales;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
