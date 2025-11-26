package product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int id;
    private String documentType;
    private String product;
    private double purchasePrice;
    private int quantity;
    private String stock;
    private double unitPrice;

    public Product(int id, String documentType, String product, double purchasePrice, int quantity, String stock) {
        this.id = id;
        this.documentType = documentType;
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.stock = stock;
    }

    public Product(String documentType, String product, int quantity, double unitPrice) {
        this.documentType = documentType;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPurchasePrice() {
        if (purchasePrice == 0 && unitPrice != 0) {
            return roundTo2Decimals(unitPrice * quantity);
        }
        return roundTo2Decimals(purchasePrice);
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUnitPrice() {
        if (unitPrice == 0 && purchasePrice != 0) {
            return roundTo2Decimals(purchasePrice / quantity);
        }
        return roundTo2Decimals(unitPrice);
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = roundTo2Decimals(unitPrice);
    }

    public static Integer tryParseQuantity(String quantityText) {
        int result;
        try {
            result = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

    public static Double tryParsePrice(String purchasePriceText) {
        double result;
        try {
            result = Double.parseDouble(purchasePriceText);
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

    public static String calculateStock(int quantity) {
        if (quantity < 1) {
            return "Out of stock";
        } else if (quantity < 11) {
            return "Running low";
        }
        return "In stock";
    }

    public String getStock() {
        return calculateStock(getQuantity());
    }

    public double getSubtotal() {
        return roundTo2Decimals(getPurchasePrice() * 1.18);
    }

    public double getTax() {
        return roundTo2Decimals(getSubtotal() * 0.18);
    }

    public double getTotal() {
        return roundTo2Decimals(getSubtotal() + getTax());
    }

    public double roundTo2Decimals(double price) {
        if (Double.isNaN(price) || Double.isInfinite(price)) {
            return 0;
        }
        return ((new BigDecimal(price)).setScale(2, RoundingMode.HALF_UP)).doubleValue();
    }
}
