package com.ellachihwa.lapa.repository;

import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageRepository extends JpaRepository<PolicyCoverage, PolicyCoverageKey> {
}
