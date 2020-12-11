package org.jkolla.components;

public class NormalizeTemporaryType {
    private Integer storeNo;
    private String storeManagerName;
    private Integer storeManagerNo;

    public NormalizeTemporaryType() {
    }

    public NormalizeTemporaryType(Integer storeNo, String storeManagerName, Integer storeManagerNo) {
        this.storeNo = storeNo;
        this.storeManagerName = storeManagerName;
        this.storeManagerNo = storeManagerNo;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public Integer getStoreManagerNo() {
        return storeManagerNo;
    }

    @Override
    public String toString() {
        return "StoreInfoManager{" +
                "storeNo=" + storeNo +
                ", storeManagerName='" + storeManagerName + '\'' +
                ", storeManagerNo=" + storeManagerNo +
                '}';
    }
}
