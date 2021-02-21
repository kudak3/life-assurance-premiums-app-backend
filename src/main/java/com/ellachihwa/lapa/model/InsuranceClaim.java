package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.ClaimStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class InsuranceClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Policy policy;
    private String description;
    @Enumerated
    private ClaimStatus claimStatus;



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


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "id=" + id +
                ", date=" + date +
                ", client=" + client.getFirstName() + " " + client.getLastName() +
                ", policy=" + policy.getName() + " " + policy.getId() +
                ", description='" + description + '\'' +
                ", claimStatus=" + claimStatus +
                '}';
    }
}
