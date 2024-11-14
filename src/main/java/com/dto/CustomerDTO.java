package com.dto;

public class CustomerDTO {
    private Long customerID;
    private String username;
    private String email;
    private String status;

    // Constructors
    public CustomerDTO() {}

    public CustomerDTO(Long customerID, String username, String email, String status) {
        this.customerID = customerID;
        this.username = username;
        this.email = email;
        this.status = status;
    }

    // Getters and Setters
    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
