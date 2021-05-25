package com.ellachihwa.lapa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PolicyCoverageKey implements Serializable {

    private Long clientId;

    private Long policyId;

    public PolicyCoverageKey() {
    }

    public PolicyCoverageKey(Long clientId, Long policyId) {
        this.clientId = clientId;
        this.policyId = policyId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolicyCoverageKey)) return false;
        PolicyCoverageKey that = (PolicyCoverageKey) o;
        return getClientId().equals(that.getClientId()) && getPolicyId().equals(that.getPolicyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getPolicyId());
    }

    @Override
    public String toString() {
        return "PolicyCoverageKey{" +
                "clientId=" + clientId +
                ", policyId=" + policyId +
                '}';
    }
}
