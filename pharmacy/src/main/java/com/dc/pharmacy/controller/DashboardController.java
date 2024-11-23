package com.dc.pharmacy.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class DashboardController {
    
    @Secured("ROLE_USER")
    @GetMapping("/user/purchased/drugs")
    public String getPurchasedDrugs() {
        return "Get the count of purchased drug item last month";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/expired/drugs")
    public String getExpiredDrugs() {
        return "Get the count of expired drug item last month";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/admin/inactive/drugs")
    public String getInactiveDrugs() {
        return "Get the count of inactive drugs last month";
    }
}
