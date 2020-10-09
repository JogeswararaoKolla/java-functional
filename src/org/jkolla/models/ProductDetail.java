package org.jkolla.models;

public class ProductDetail {
    private String productId;
    private String productMerchantId;
    private String productSubCategoryDesc;
    private Double productPriceBag;
    private Integer productWeight;

    public ProductDetail() {
    }

    public ProductDetail(String productId, String productMerchantId, String productSubCategoryDesc, Double productPriceBag, Integer productWeight) {
        this.productId = productId;
        this.productMerchantId = productMerchantId;
        this.productSubCategoryDesc = productSubCategoryDesc;
        this.productPriceBag = productPriceBag;
        this.productWeight = productWeight;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductMerchantId() {
        return productMerchantId;
    }

    public void setProductMerchantId(String productMerchantId) {
        this.productMerchantId = productMerchantId;
    }

    public String getProductSubCategoryDesc() {
        return productSubCategoryDesc;
    }

    public void setProductSubCategoryDesc(String productSubCategoryDesc) {
        this.productSubCategoryDesc = productSubCategoryDesc;
    }

    public Double getProductPriceBag() {
        return productPriceBag;
    }

    public void setProductPriceBag(Double productPriceBag) {
        this.productPriceBag = productPriceBag;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productId='" + productId + '\'' +
                ", productMerchantId='" + productMerchantId + '\'' +
                ", productSubCategoryDesc='" + productSubCategoryDesc + '\'' +
                ", productPriceBag=" + productPriceBag +
                ", productWeight=" + productWeight +
                '}';
    }
}
