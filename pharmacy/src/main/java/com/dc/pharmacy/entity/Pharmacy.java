package com.dc.pharmacy.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_id")
    private Long pharmacyId;

    @Column(name = "name")
    private String name;

    // One-to-many relationship: one pharmacy can have many drugs
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DrugInfo> drugs;

    public Pharmacy() {}

    public Pharmacy(String name) {
        this.name = name;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrugInfo> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugInfo> drugs) {
        this.drugs = drugs;
    }
}