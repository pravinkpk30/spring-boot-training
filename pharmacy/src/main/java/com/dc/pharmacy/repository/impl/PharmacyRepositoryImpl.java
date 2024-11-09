package com.dc.pharmacy.repository.impl;

import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.repository.IPharmacyRepository;
import com.dc.pharmacy.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("pharmacyRepository")
@Transactional
public class PharmacyRepositoryImpl implements PharmacyRepository {

    @Autowired
    private IPharmacyRepository pharmacyRepository;

    @Override
    public void createPharmacyWithDrugs(Pharmacy pharmacy) {
        // for avoiding null value in pharmacy_id fk column in drug entity. Set pharmacy in each drug entity
        pharmacy.getDrugs().forEach((drug) -> {
            drug.setPharmacy(pharmacy);
        });
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public List<Pharmacy> listPharmacy() {
        return pharmacyRepository.findAll();
    }
}
