package com.dc.pharmacy.controller;

import com.dc.pharmacy.entity.DrugInfo;
import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.service.IPharmacyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/pharmacy")
@Validated
public class PharmacyController {

    @Autowired
    private IPharmacyService pharmacyService;

    @PostMapping
    public void createPharmacyWithDrugs(@Valid @RequestBody Pharmacy pharmacy) {
        pharmacyService.createPharmacyWithDrugs(pharmacy);
    }

    @GetMapping("/list")
    public List<Pharmacy> listPharamcy() {
        return pharmacyService.listPharmacy();
    }
}
