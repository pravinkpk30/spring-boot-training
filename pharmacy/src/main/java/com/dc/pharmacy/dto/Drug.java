package com.dc.pharmacy.dto;

public class Drug {
    
    private Integer itemCode;
    private String ndc;
    private String upc;
    private String itemDescription;

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "itemCode=" + itemCode +
                ", ndc='" + ndc + '\'' +
                ", upc='" + upc + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
