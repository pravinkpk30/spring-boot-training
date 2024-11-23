package com.dc.pharmacy.exception;

import org.springframework.stereotype.Component;

public class DrugNotFoundException extends RuntimeException {
    
    public DrugNotFoundException(String message) {
        super(message);
    }
}
