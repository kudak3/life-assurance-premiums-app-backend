package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.CoverageStatus;


import javax.persistence.*;
import java.util.Date;

@Entity
public class PolicyCoverage {
    @EmbeddedId
    private PolicyCoverageKey id;

    @ManyToOne
    @MapsId("clientId")
    private Client client;

    @ManyToOne
    @MapsId("policyId")
    private Policy policy;

    private Date date;
    @Enumerated
    private CoverageStatus status;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CoverageStatus getStatus() {
        return status;
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
}
