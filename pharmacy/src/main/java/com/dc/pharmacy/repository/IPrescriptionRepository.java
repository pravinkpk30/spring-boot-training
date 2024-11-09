package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrescriptionRepository extends JpaRepository<Prescription, Long> {
}
