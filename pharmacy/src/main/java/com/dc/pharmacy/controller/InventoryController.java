package com.dc.pharmacy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class InventoryController {
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/inventory/list")
    public String listInventories() {
        return "List of inventory items returned";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/inventory")
    public String addInventory() {
        return "New inventory item added";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping("/admin/inventory")
    public String updateInventory() {
        return "New inventory item updated";
    }

    

}
