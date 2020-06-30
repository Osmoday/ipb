package com.ipb.projekt.controllers;

import com.ipb.projekt.entities.AnnexEntity;
import com.ipb.projekt.entities.ProductEntity;
import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RemoveController {

    private final ProductManagementService productManagementService;

    @Autowired
    public RemoveController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @PostMapping("/remove/product")
    public String removeProduct(@RequestParam(name = "idProduct") int idProduct) {
        Optional<ProductEntity> e = this.productManagementService.getProductRepo().findById(idProduct);
        if (e.isPresent()) {
            AnnexEntity annex = e.get().getAnnexEntity();
            if (annex != null) {
                annex.setProductEntity(null);
                this.productManagementService.getAnnexRepo().delete(annex);
            }
            e.get().getShelfEntity().setProductEntity(null);
            this.productManagementService.getProductRepo().delete(e.get());
        }
        return "redirect:/add/product";
    }
}
