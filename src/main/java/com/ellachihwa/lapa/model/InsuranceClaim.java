package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.ClaimStatus;
import com.ellachihwa.lapa.utils.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class InsuranceClaim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;


    @OneToOne
    private PolicyCoverage policyCoverage;

    private String description;
    private Long amount;
    @Enumerated
    private ClaimStatus claimStatus;

    @Column(columnDefinition = "boolean default true")
    private boolean newEntry;


    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isNewEntry() {
        return newEntry;
    }

    public void setNewEntry(boolean newEntry) {
        this.newEntry = newEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public PolicyCoverage getPolicyCoverage() {
        return policyCoverage;
    }

    public void setPolicyCoverage(PolicyCoverage policyCoverage) {
        this.policyCoverage = policyCoverage;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "id=" + id +
                ", date=" + date +
                ", policyCoverage=" + policyCoverage +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", claimStatus=" + claimStatus +
                ", newEntry=" + newEntry +
                '}';
    }
}
