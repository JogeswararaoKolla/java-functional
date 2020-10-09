package org.jkolla.models;

import java.util.List;

public class Product {
    private String productId;
    private String productName;
    private String productBestByDate;
    private List<ProductDetail> productDetails;

    public Product() {
    }

    public Product(String productId, String productName, String productBestByDate, List<ProductDetail> productDetails) {
        this.productId = productId;
        this.productName = productName;
        this.productBestByDate = productBestByDate;
        this.productDetails = productDetails;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBestByDate() {
        return productBestByDate;
    }

    public void setProductBestByDate(String productBestByDate) {
        this.productBestByDate = productBestByDate;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBestByDate='" + productBestByDate + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
