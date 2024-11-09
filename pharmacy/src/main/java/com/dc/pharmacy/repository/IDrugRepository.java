package com.dc.pharmacy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dc.pharmacy.entity.DrugInfo;

import jakarta.transaction.Transactional;

@Transactional
public interface IDrugRepository extends JpaRepository<DrugInfo, Long> {

    @Query("SELECT d FROM DrugInfo d WHERE d.ndc = ?1 AND d.active = ?2")
    List<DrugInfo> findDrugNdc(String ndc, Boolean active);

    @Query(value="SELECT * FROM drug-details WHERE upc = ?1 AND active = ?2", nativeQuery = true)
    List<DrugInfo> findDrugUpc(String upc, Boolean active);

    @Query("SELECT d FROM DrugInfo d WHERE d.gtin = :gtin AND d.ndc = :ndc")
    List<DrugInfo> findDrugGtin(@Param("gtin") String gtin, @Param("ndc") String ndc);

    @Modifying
    @Query("UPDATE DrugInfo d SET d.active = :active WHERE d.ndc = :ndc")
    int updateDrugStatus(@Param("ndc") String ndc, @Param("active") Boolean active);

    @Query("SELECT u FROM DrugInfo u WHERE u.active = :active")
    Page<DrugInfo> findByActive(@Param("active") Boolean active, Pageable pageable);

    @Query("SELECT u FROM DrugInfo u WHERE u.active = :#{#active}")
    List<DrugInfo> findDrugsWithActive(@Param("active") Boolean active);
}
