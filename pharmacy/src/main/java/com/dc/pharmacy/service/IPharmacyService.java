package com.dc.pharmacy.service;

import com.dc.pharmacy.entity.Pharmacy;

import java.util.List;

public interface IPharmacyService {

    void createPharmacyWithDrugs(Pharmacy pharmacy);

    List<Pharmacy> listPharmacy();
}
