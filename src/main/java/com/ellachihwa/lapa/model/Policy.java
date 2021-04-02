package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.Plan;
import com.ellachihwa.lapa.utils.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "policyCoverageList" })
@Entity
public class Policy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Long premium;
    @Enumerated
    private Plan plan;



    @JsonBackReference(value = "policy-cover")
    @OneToMany(mappedBy = "policy")
    List<PolicyCoverage> policyCoverageList;

    @Enumerated
    private Status status;
    private Long totalPayment;
    private Long coverageAmount;
    //term
    private Long duration;

    @OneToMany(mappedBy = "policy")
    private List<InsuranceClaim> claims = new ArrayList<>();


    public Long getPremium() {
        return premium;
    }

    public void setPremium(Long premium) {
        this.premium = premium;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Long amount) {
        this.totalPayment = amount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<PolicyCoverage> getPolicyCoverageList() {
        return policyCoverageList;
    }

    public void setPolicyCoverageList(List<PolicyCoverage> policyCoverageSet) {
        this.policyCoverageList = policyCoverageSet;
    }

    public Long getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(Long coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public List<InsuranceClaim> getClaims() {
        return claims;
    }

    public void setClaims(List<InsuranceClaim> claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", premium=" + premium +
                ", plan=" + plan +
                ", policyCoverageSet=" + policyCoverageList +
                ", status=" + status +
                ", amount=" + totalPayment +
                ", duration=" + duration +
                ", claims=" + claims +
                '}';
    }
}
