package com.ipb.projekt.controllers;

import com.ipb.projekt.entities.ClientEntity;
import com.ipb.projekt.entities.ProductEntity;
import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class JSONcontroller {

    final
    private ProductManagementService productManagementService;

    @Autowired
    public JSONcontroller(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/get/clients")
    public Iterable<ClientEntity> clients() {
        return productManagementService.getClientRepo().findAll();
    }


    @GetMapping(value = "/get/product")
    @ResponseBody
    public Optional<ProductEntity> product(@RequestParam(name = "id") int id) {
        return productManagementService.getProductRepo().findById(id);
    }

    @GetMapping(value = "/get/products")
    @ResponseBody
    public Iterable<ProductEntity> products() {
        return productManagementService.getProductRepo().findAll();
    }

}
