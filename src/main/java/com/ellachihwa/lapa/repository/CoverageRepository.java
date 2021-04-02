package com.ellachihwa.lapa.repository;

import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoverageRepository extends JpaRepository<PolicyCoverage, PolicyCoverageKey> {
    List<PolicyCoverage> findPolicyCoveragesByClient_Id(Long id);
    long countPolicyCoveragesByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE PolicyCoverage p SET p.newEntry = false WHERE p.newEntry = true ")
    void updateAllCovers();
}
