package com.ellachihwa.lapa.repository;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {
    long countInsuranceClaimsByNewEntryIsTrue();

    List<InsuranceClaim> findInsuranceClaimsByPolicyCoverageClient(Client client);

    @Transactional
    @Modifying
    @Query("UPDATE InsuranceClaim c SET c.newEntry = false WHERE c.newEntry = true ")
    void updateAllClaims();
}
