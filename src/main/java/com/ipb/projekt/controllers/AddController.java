package com.ipb.projekt.controllers;

import com.ipb.projekt.entities.ProductEntity;
import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {


        final ProductManagementService productManagementService;

        @Autowired
        public AddController(ProductManagementService productManagementService) {
            this.productManagementService = productManagementService;
        }

        @GetMapping("/add/product")
        public String main() {
            return "addProduct";
        }

        @PostMapping("/add/product/insert")
        public String addProduct(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "serialNumber") String serialNumber,
                                 @RequestParam(value = "amount") int amount,
                                 @RequestParam(value = "status") String status,
                                 @RequestParam(value = "idShelf") int idShelf) {
            ProductEntity e = new ProductEntity(name, serialNumber, amount, status);
            try {
                this.productManagementService.putProductOnShelf(e, idShelf);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return "redirect:/add/product";
        }
}
