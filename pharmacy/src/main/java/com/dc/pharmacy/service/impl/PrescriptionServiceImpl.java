package com.dc.pharmacy.service.impl;

import com.dc.pharmacy.entity.Prescription;
import com.dc.pharmacy.repository.PrescriptionRepository;
import com.dc.pharmacy.service.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prescriptionService")
public class PrescriptionServiceImpl implements IPrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public void createPrescription(Prescription prescription) {
        prescriptionRepository.createPrescription(prescription);
    }
}
