package com.dc.pharmacy.exception;

public class DrugNotFoundException extends RuntimeException {
    
    public DrugNotFoundException(String message) {
        super(message);
    }
}
