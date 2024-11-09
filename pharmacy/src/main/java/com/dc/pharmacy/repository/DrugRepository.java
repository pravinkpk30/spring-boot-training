package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.DrugInfo;

import java.util.List;
import java.util.Optional;

public interface DrugRepository {

    void createDrug(DrugInfo drugInfo);

    void updateDrug(DrugInfo drugInfo);

    void deleteDrug(Long id);

    List<DrugInfo> getDrugList();

    List<DrugInfo> sortAndPaginatDrug();

    Optional<DrugInfo> findByDrugId(Long id);

    List<DrugInfo> findByActiveDrug(String ndc);

    void updateDrugStatus(String ndc, Boolean active);
}
