package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.Plan;
import com.ellachihwa.lapa.utils.Status;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Policy {
    @Id
    private Long policyNumber;
    private Long premium;
    @Enumerated
    private Plan plan;

    @Enumerated
    private Status status;

    public Long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Long policyNumber) {
        this.policyNumber = policyNumber;
    }

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
}
