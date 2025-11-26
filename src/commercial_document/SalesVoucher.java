package commercial_document;

import java.time.LocalDate;

public class SalesVoucher {

    private int id;
    private LocalDate registrationDate;
    private String voucherType;
    private long series;
    private int number;
    private String client;
    private double total;

    public SalesVoucher(int id, LocalDate registrationDate, String voucherType, long series, int number, String client, double total) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.voucherType = voucherType;
        this.series = series;
        this.number = number;
        this.client = client;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
