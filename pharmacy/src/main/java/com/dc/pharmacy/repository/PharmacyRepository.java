package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.Pharmacy;

import java.util.List;

public interface PharmacyRepository {

    void createPharmacyWithDrugs(Pharmacy pharmacy);

    List<Pharmacy> listPharmacy();
}
