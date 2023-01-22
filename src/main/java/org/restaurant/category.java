package org.restaurant;

public class category {

    private String productId;
    private String productName;
    private String type;
    private Integer price;
    private String status;

    public category(String productId, String productName, String type, Integer price, String status) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public String getProductId() {return productId;}

    public String getProductName() {
        return productName;
    }

    public String getType() {return type;}

    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}