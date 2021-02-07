package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.CoverageStatus;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class PolicyCoverage {
    @EmbeddedId
    private PolicyCoverageKey id = new PolicyCoverageKey();

    @ManyToOne
    @MapsId("clientId")
    private Client client;

    @ManyToOne
    @MapsId("policyId")
    private Policy policy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Enumerated
    private CoverageStatus status;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    @Override
    public String toString() {
        return "PolicyCoverage{" +
                "id=" + id +
                ", client=" + client.getFirstName() + " " + client.getLastName() +
                ", policy=" + policy.getName() + " " + policy.getId() +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
