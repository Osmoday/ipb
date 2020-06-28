package com.ipb.projekt.controllers;

import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagazynController {

    final ProductManagementService productManagementService;

    @Autowired
    public MagazynController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/magazyn")
    public String magazynHandler() {
        return "magazyn";
    }
}
