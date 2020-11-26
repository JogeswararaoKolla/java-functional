package org.jkolla.models;


public class StoreInfo {
    Integer storeNo;
    String address;
    String city;
    String state;
    String zipcode;
    String storeManager1;
    String storeManager2;
    String storeManager3;

    public StoreInfo(Integer storeNo, String address, String city, String state, String zipcode, String storeManager1, String storeManager2, String storeManager3) {
        this.storeNo = storeNo;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.storeManager1 = storeManager1;
        this.storeManager2 = storeManager2;
        this.storeManager3 = storeManager3;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStoreManager1() {
        return storeManager1;
    }

    public String getStoreManager2() {
        return storeManager2;
    }

    public String getStoreManager3() {
        return storeManager3;
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "storeNo=" + storeNo +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", storeManager1='" + storeManager1 + '\'' +
                ", storeManager2='" + storeManager2 + '\'' +
                ", storeManager3='" + storeManager3 + '\'' +
                '}';
    }
}
