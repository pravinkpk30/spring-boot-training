package com.dc.pharmacy.service.impl;

import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.repository.PharmacyRepository;
import com.dc.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pharmacyService")
public class PharmacyServiceImpl implements IPharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public void createPharmacyWithDrugs(Pharmacy pharmacy) {
        pharmacyRepository.createPharmacyWithDrugs(pharmacy);
    }

    @Override
    public List<Pharmacy> listPharmacy() {
        return pharmacyRepository.listPharmacy();
    }
}
