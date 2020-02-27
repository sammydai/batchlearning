package com.ssm.batch.entity;

public class Customer {
    private Integer customerId;

    private String customerUser;

    private String customerTel;

    private String customerAddress;

    public Customer(Integer customerId, String customerUser, String customerTel, String customerAddress) {
        this.customerId = customerId;
        this.customerUser = customerUser;
        this.customerTel = customerTel;
        this.customerAddress = customerAddress;
    }

    public Customer() {
        super();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(String customerUser) {
        this.customerUser = customerUser == null ? null : customerUser.trim();
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel == null ? null : customerTel.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUser='" + customerUser + '\'' +
                ", customerTel='" + customerTel + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}