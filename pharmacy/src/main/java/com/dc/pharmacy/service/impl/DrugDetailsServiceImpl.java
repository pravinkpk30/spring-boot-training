package com.dc.pharmacy.service.impl;

import com.dc.pharmacy.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("drugDetails")
public class DrugDetailsServiceImpl {

    @Autowired
    private IDrugService drugService;

    public void invokeTestTransactionalExceptionFromOtherService(String ndc, Boolean active) {
        drugService.testTransactionalException(ndc, active);  // This will trigger @Transactional from outside of the DrugServiceImpl
    }
}
