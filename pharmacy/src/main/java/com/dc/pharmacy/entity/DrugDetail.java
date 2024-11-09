package com.dc.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "drug_details")
public class DrugDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    @Column(name = "dosage")
    private String dosageInformation;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(mappedBy = "drugDetails", cascade = CascadeType.ALL)
    @JsonBackReference  // Do not serialize this part to break the recursion
    private DrugInfo drug;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDosageInformation() {
        return dosageInformation;
    }

    public void setDosageInformation(String dosageInformation) {
        this.dosageInformation = dosageInformation;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DrugInfo getDrug() {
        return drug;
    }

    public void setDrug(DrugInfo drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "DrugDetail{" +
                "id=" + id +
                ", dosageInformation='" + dosageInformation + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", drug=" + drug +
                '}';
    }
}
