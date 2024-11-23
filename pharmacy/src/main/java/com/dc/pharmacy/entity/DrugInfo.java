package com.dc.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drugs")
public class DrugInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Long drugId;

    @Column(name = "description")
    private String description;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "ndc")
    private String ndc;

    @Column(name = "upc")
    private String upc;

    @Column(name = "gtin")
    private String gtin;

    @Column(name = "category")
    private String category;

    @Column(name = "active")
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_details_id", referencedColumnName = "detail_id")
    @JsonManagedReference // Serialize this part of the relationship
    private DrugDetail drugDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false, referencedColumnName = "pharmacy_id")
    @JsonBackReference
    private Pharmacy pharmacy;

//    mappedBy: This attribute is used in the Drug entity to indicate that the relationship is managed by the drugs collection in the Prescription entity.entity
    @ManyToMany(mappedBy = "drugs", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public DrugDetail getDrugDetails() {
        return drugDetails;
    }

    public void setDrugDetails(DrugDetail drugDetails) {
        this.drugDetails = drugDetails;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugInfo drugInfo = (DrugInfo) o;
        return Objects.equals(drugId, drugInfo.drugId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugId);
    }

    @Override
    public String toString() {
        return "DrugInfo{" +
                "drugId=" + drugId +
                ", description='" + description + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", ndc='" + ndc + '\'' +
                ", upc='" + upc + '\'' +
                ", gtin='" + gtin + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
