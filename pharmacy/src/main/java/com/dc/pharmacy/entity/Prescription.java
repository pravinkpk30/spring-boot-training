package com.dc.pharmacy.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long prescriptionId;

    @Column(name = "patient_name")
    private String patientName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "prescription_drug",  // The name of the join table
            joinColumns = @JoinColumn(name = "prescription_id"),  // The column in the join table that references Prescription (FK)
            inverseJoinColumns = @JoinColumn(name = "drug_id")  // The column in the join table that references Drug (FK)
    )
    private List<DrugInfo> drugs;

    // Getters and Setters
    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public List<DrugInfo> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugInfo> drugs) {
        this.drugs = drugs;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}

