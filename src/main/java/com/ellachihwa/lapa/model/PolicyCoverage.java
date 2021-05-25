package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.CoverageStatus;
import com.ellachihwa.lapa.utils.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class PolicyCoverage implements Serializable {
    @EmbeddedId
    private PolicyCoverageKey id = new PolicyCoverageKey();

    private String policyNumber;


    @JsonManagedReference(value="coverage")
    @ManyToOne()
    @MapsId("clientId")
    private Client client;

    @JsonManagedReference(value = "policy-cover")
    @ManyToOne()
    @MapsId("policyId")
    private Policy policy;



    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    @Enumerated
    private CoverageStatus status;

    public PolicyCoverage() {
    }

    public PolicyCoverage(Client client, Policy policy) {
        this.client = client;
        this.policy = policy;
    }

    @Column(columnDefinition = "boolean default true")
    private boolean newEntry;

    public boolean isNewEntry() {
        return newEntry;
    }

    public void setNewEntry(boolean newEntry) {
        this.newEntry = newEntry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CoverageStatus getStatus() {
        return status;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public void setStatus(CoverageStatus status) {
        this.status = status;
    }

    public PolicyCoverageKey getId() {
        return id;
    }

    public void setId(PolicyCoverageKey id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "PolicyCoverage{" +
                "id=" + id +
                ", client=" + client.getFirstName() +
                ", policy=" + policy.getName() +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
