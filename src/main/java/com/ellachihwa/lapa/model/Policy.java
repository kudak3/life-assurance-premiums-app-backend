package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.Plan;
import com.ellachihwa.lapa.utils.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Long premium;
    @Enumerated
    private Plan plan;

    @OneToMany(mappedBy = "policy")
    Set<PolicyCoverage> policyCoverageSet;

    @Enumerated
    private Status status;
    private Long amount;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Set<PolicyCoverage> getPolicyCoverageSet() {
        return policyCoverageSet;
    }

    public void setPolicyCoverageSet(Set<PolicyCoverage> policyCoverageSet) {
        this.policyCoverageSet = policyCoverageSet;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", premium=" + premium +
                ", plan=" + plan +
                ", policyCoverageSet=" + policyCoverageSet +
                ", status=" + status +
                ", amount=" + amount +
                ", duration=" + duration +
                ", claims=" + claims +
                '}';
    }
}
