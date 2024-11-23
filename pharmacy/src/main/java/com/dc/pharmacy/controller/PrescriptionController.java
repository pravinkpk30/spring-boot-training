package com.dc.pharmacy.controller;

import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.entity.Prescription;
import com.dc.pharmacy.service.IPrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/prescription")
@Validated
public class PrescriptionController {

    @Autowired
    private IPrescriptionService prescriptionService;

    @PostMapping
    public void createPrescription(@Valid @RequestBody Prescription prescription) {
        prescriptionService.createPrescription(prescription);
    }
}
