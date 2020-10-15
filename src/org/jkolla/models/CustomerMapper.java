package org.jkolla.models;

public class CustomerMapper {
    private Integer customerId;
    private String firstName;
    private String city;

    public CustomerMapper() {
    }

    public CustomerMapper(Integer customerId, String firstName, String city) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.city = city;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CustomerMapper{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
