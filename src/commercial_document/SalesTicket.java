package commercial_document;

import java.util.ArrayList;
import product.Product;

public class SalesTicket {

    private int id;
    private long series;
    private long number;
    private String supplier;
    private String currency;
    private String responsible;
    private ArrayList<Product> productList;
    private double total;

    public SalesTicket(int id, long series, long number, String supplier, String currency, String responsible, ArrayList<Product> productList, double total) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.supplier = supplier;
        this.currency = currency;
        this.responsible = responsible;
        this.productList = productList;
        this.total = total;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
        this.series = series;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
