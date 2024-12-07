package com.dc.pharmacy.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.dc.pharmacy.service.IDrugService;
import com.dc.pharmacy.service.IPharmacyService;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private IDrugService drugService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Override
    public Health health() {
        // Check the health of DrugController and PharmacyController
        boolean isDrugServiceHealthy = checkDrugService();
        boolean isPharmacyServiceHealthy = checkPharmacyService();

        if (isDrugServiceHealthy && isPharmacyServiceHealthy) {
            return Health.up()
                         .withDetail("DrugService", "Available")
                         .withDetail("PharmacyService", "Available")
                         .build();
        } else {
            Health.Builder healthBuilder = Health.down();
            if (!isDrugServiceHealthy) {
                healthBuilder.withDetail("DrugService", "Unavailable");
            }
            if (!isPharmacyServiceHealthy) {
                healthBuilder.withDetail("PharmacyService", "Unavailable");
            }
            return healthBuilder.build();
        }
    }

    private boolean checkDrugService() {
        try {
            return !drugService.getDrugList().isEmpty();
        } catch (Exception e) {
            return false; 
        }
    }

    private boolean checkPharmacyService() {
        try {
            return !pharmacyService.listPharmacy().isEmpty(); 
        } catch (Exception e) {
            return false; 
        }
    }
}
