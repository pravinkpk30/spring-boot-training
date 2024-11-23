package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}