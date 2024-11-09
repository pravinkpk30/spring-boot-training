package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.Pharmacy;
import com.dc.pharmacy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
