package com.dc.pharmacy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.pharmacy.entity.DrugInfo;
import com.dc.pharmacy.repository.DrugRepository;
import com.dc.pharmacy.service.IDrugService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("drugService")
public class DrugServiceImpl implements IDrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public String getDrugInfo() {
        throw new UnsupportedOperationException("Unimplemented method 'getDrugInfo'");
    }

    @Override
    public void createDrug(DrugInfo drugInfo) {
        drugRepository.createDrug(drugInfo);
    }

    @Override
    public void updateDrug(DrugInfo drugInfo) {
        drugRepository.updateDrug(drugInfo);
    }

    @Override
    public void deleteDrug(Long id) {
        drugRepository.deleteDrug(id);
    }

    @Override
    public List<DrugInfo> getDrugList() {
       return drugRepository.getDrugList();
    }

    @Override
    public List<DrugInfo> sortAndPaginatDrug() {
        return drugRepository.sortAndPaginatDrug();
    }

    @Override
    public DrugInfo findByDrugId(Long id) {
        Optional<DrugInfo> optional = drugRepository.findByDrugId(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<DrugInfo> findActiveDrug(String ndc) {
        return drugRepository.findByActiveDrug(ndc);
    }

    @Override
    public void updateDrugStatus(String ndc, Boolean active) {
        drugRepository.updateDrugStatus(ndc, active);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = {ArithmeticException.class})
    @Override
    public void testTransactionalException(String ndc, Boolean active) {
        drugRepository.updateDrugStatus(ndc, active);
        throw new ArithmeticException("Exception occurred !!!!! ");
    }

}
