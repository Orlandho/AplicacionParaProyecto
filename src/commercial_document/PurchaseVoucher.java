package commercial_document;

import java.time.LocalDate;

public class PurchaseVoucher {

    private int id;
    private LocalDate registrationDate;
    private String voucherType;
    private long series;
    private int number;
    private String supplier;
    private double total;

    public PurchaseVoucher(int id, LocalDate registrationDate, String voucherType, long series, int number, String supplier, double total) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.voucherType = voucherType;
        this.series = series;
        this.number = number;
        this.supplier = supplier;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
