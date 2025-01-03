package com.dc.pharmacy.service;

import java.util.List;

import com.dc.pharmacy.entity.DrugInfo;

public interface IDrugService {
    
    String getDrugInfo();

    void createDrug(DrugInfo drugInfo);

    void updateDrug(DrugInfo drugInfo);

    void deleteDrug(Long id);

    List<DrugInfo> getDrugList();

    List<DrugInfo> sortAndPaginatDrug();

    DrugInfo findByDrugId(Long id);

    List<DrugInfo> findActiveDrug(String ndc);

    void updateDrugStatus(String ndc, Boolean active);

    void testTransactionalException(String ndc, Boolean active);
}
