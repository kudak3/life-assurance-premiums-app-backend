package com.ellachihwa.lapa.dto;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Policy;

public class CoverDto {
    Client client;
    Policy policy;

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
        return "CoverDto{" +
                "client=" + client +
                ", policy=" + policy +
                '}';
    }
}
