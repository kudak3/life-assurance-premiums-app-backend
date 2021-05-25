package com.ellachihwa.lapa.dto;


public class ClaimDto {


    private String policyNumber;
    private String description;
    private Long amount;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.Long getAmount() {
        return amount;
    }

    public void setAmount(java.lang.Long amount) {
        this.amount = amount;
    }
}
