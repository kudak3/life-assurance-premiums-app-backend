package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import com.ellachihwa.lapa.repository.CoverageRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CoverageService {

 final
 CoverageRepository coverageRepository;

    public CoverageService(CoverageRepository coverageRepository) {
        this.coverageRepository = coverageRepository;
    }


    public List<PolicyCoverage> getCoverageList(){

        System.out.println(coverageRepository.findAll());
        return coverageRepository.findAll();
    }

    public void saveCoverage(PolicyCoverage coverage){
        coverageRepository.save(coverage);
    }

    public void deleteCoverage(PolicyCoverageKey id){
        coverageRepository.deleteById(id);
    }

    public void updateCoverage(PolicyCoverage coverage){
        coverageRepository.save(coverage);
    }



}
