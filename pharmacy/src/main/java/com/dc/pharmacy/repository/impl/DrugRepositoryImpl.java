package com.dc.pharmacy.repository.impl;

import java.util.List;
import java.util.Optional;

import com.dc.pharmacy.entity.DrugDetail;
import com.dc.pharmacy.repository.DrugRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.dc.pharmacy.entity.DrugInfo;
import com.dc.pharmacy.repository.IDrugRepository;

@Repository("drugRepository")
public class DrugRepositoryImpl implements DrugRepository {

    @Autowired
    private IDrugRepository drugRepository;

    @Override
    public void createDrug(DrugInfo drugInfo) {
        drugRepository.save(drugInfo);
    }

    @Override
    public void updateDrug(DrugInfo drugInfo) {
        drugRepository.save(drugInfo);
    }

    @Override
    public void deleteDrug(Long id) {
        drugRepository.deleteById(id);;
    }

    @Override
    public List<DrugInfo> getDrugList() {
       return drugRepository.findAll();
    }

    @Override
    public List<DrugInfo> sortAndPaginatDrug() {
       Pageable pageable =  PageRequest.of(0, 5, Sort.by("description").ascending().and(Sort.by("category").descending()));
       Page<DrugInfo> page = drugRepository.findAll(pageable);
       return page.getContent();
    }

    @Override
    public Optional<DrugInfo> findByDrugId(Long id) {
        return drugRepository.findById(id);
    }

    @Override
    public List<DrugInfo> findByActiveDrug(String ndc) {
        return drugRepository.findDrugNdc(ndc,true);
    }

    @Override
    public void updateDrugStatus(String ndc, Boolean active) {
        drugRepository.updateDrugStatus(ndc, active);
    }


}
