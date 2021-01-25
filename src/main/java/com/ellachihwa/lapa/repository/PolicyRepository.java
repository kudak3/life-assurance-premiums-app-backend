package com.ellachihwa.lapa.repository;

import com.ellachihwa.lapa.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {
}
