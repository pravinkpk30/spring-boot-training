package com.dc.pharmacy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dc.pharmacy.dto.ApplicationProperties;
import com.dc.pharmacy.dto.Drug;
import com.dc.pharmacy.exception.DrugNotFoundException;
import com.dc.pharmacy.exception.InvalidInputException;

@RestController
@RequestMapping("/api/drug")
public class DrugController {
    
    @Value("${app.title}")
    private String title;

    @Value("${app.description}")
    private String description;

    private final ApplicationProperties appProperties;

    public DrugController (ApplicationProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "Title: " + title + ", Description: " + description;
    }

    @GetMapping("/{id}")
    public Drug getDrugById(@PathVariable Long id) {
        // throw new DrugNotFoundException("Drug with id " + id + " not found.");
        throw new NullPointerException("Drug with id " + id + " should not be NULL.");
    }

    @GetMapping("/validate")
    public String validateInput(@RequestParam String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidInputException("Input cannot be null or empty.");
        }
        return "Valid input: " + input;
    }
    
}
