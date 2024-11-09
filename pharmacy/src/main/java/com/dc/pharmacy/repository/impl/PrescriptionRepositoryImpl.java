package com.dc.pharmacy.repository.impl;

import com.dc.pharmacy.entity.DrugInfo;
import com.dc.pharmacy.entity.Prescription;
import com.dc.pharmacy.repository.IDrugRepository;
import com.dc.pharmacy.repository.IPrescriptionRepository;
import com.dc.pharmacy.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("prescriptionRepo")
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired
    private IPrescriptionRepository prescriptionRepository;

    @Autowired
    private IDrugRepository drugRepository;

    @Override
    public void createPrescription(Prescription prescription) {
        List<DrugInfo> drugs = drugRepository.findAll();
        prescription.setDrugs(drugs);
        prescriptionRepository.save(prescription);
    }
}
