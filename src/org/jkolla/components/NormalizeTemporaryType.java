package org.jkolla.components;

public class NormalizeTemporaryType {
    Integer storeNo;
    String storeManagerName;
    Integer storeManagerNo;

    public NormalizeTemporaryType() {
    }

    public NormalizeTemporaryType(Integer storeNo, String storeManagerName, Integer storeManagerNo) {
        this.storeNo = storeNo;
        this.storeManagerName = storeManagerName;
        this.storeManagerNo = storeManagerNo;
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
