package commercial_document;

import product.Product;
import java.util.ArrayList;

public class Quote {

    private int id;
    private String names;
    private String responsible;
    private String currency;
    String date;
    private ArrayList<Product> productList;
    private double total;

    public Quote(int id, String names, String responsible, String currency, String date, ArrayList<Product> productList, double total) {
        this.id = id;
        this.names = names;
        this.responsible = responsible;
        this.currency = currency;
        this.date = date;
        this.productList = productList;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
