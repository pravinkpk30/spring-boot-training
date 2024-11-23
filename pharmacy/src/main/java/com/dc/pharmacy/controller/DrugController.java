package com.dc.pharmacy.controller;

import java.util.List;

import com.dc.pharmacy.service.impl.DrugDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.dc.pharmacy.dto.ApplicationProperties;
import com.dc.pharmacy.dto.Drug;
import com.dc.pharmacy.entity.DrugInfo;
import com.dc.pharmacy.exception.InvalidInputException;
import com.dc.pharmacy.service.IDrugService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/drug")
public class DrugController {
    
    @Value("${app.title}")
    private String title;

    @Value("${app.description}")
    private String description;

    private final ApplicationProperties appProperties;

    @Autowired
    private IDrugService drugService;

    @Autowired
    private DrugDetailsServiceImpl drugDetailsService;

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
    
    @PostMapping
    public void addDrug(@Valid @RequestBody DrugInfo drugInfo) {
        drugService.createDrug(drugInfo);
    }

    @PutMapping
    public void updateDrug(@Valid @RequestBody DrugInfo drugInfo) {
        drugService.updateDrug(drugInfo);
    }

    @DeleteMapping("/{drugId}")
    public void deleteDrug(@PathVariable("drugId") Long id) {
        drugService.deleteDrug(id);
    }

    @GetMapping
    public DrugInfo findDrug(@RequestParam("drugId") Long id) {
        return drugService.findByDrugId(id);
    }

    @GetMapping("/list-drugs")
    public List<DrugInfo> listDrug() {
        return drugService.getDrugList();
    }

    @GetMapping("/sort-drugs")
    public List<DrugInfo> sortAndPaginatDrug() {
        return drugService.sortAndPaginatDrug();
    }

    @GetMapping("/ndc/{ndc}")
    public List<DrugInfo> findActiveDrug(@PathVariable("ndc") String ndc) {
        return drugService.findActiveDrug(ndc);
    }

    @PutMapping("/status/ndc/{ndc}/{active}")
    public void updateDrugStatus(@PathVariable("ndc") String ndc, @PathVariable("active") Boolean active) {
        drugService.updateDrugStatus(ndc, active);
    }

    @PutMapping("/trans-exception/ndc/{ndc}/{active}")
    public void testTransactionalException(@PathVariable("ndc") String ndc, @PathVariable("active") Boolean active) {
        drugDetailsService.invokeTestTransactionalExceptionFromOtherService(ndc, active);
    }
}
