package com.ipb.projekt.controllers;

import com.ipb.projekt.entities.AnnexEntity;
import com.ipb.projekt.entities.ProductEntity;
import com.ipb.projekt.entities.ShelfEntity;
import com.ipb.projekt.exceptions.NoShelfException;
import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class AddController {

        final ProductManagementService productManagementService;

        @Autowired
        public AddController(ProductManagementService productManagementService) {
            this.productManagementService = productManagementService;
        }

        @GetMapping("/add/product")
        public String main(Model model) {
            // make this assemble the shelf model and send it to the view
            Iterable<ShelfEntity> shelves = this.productManagementService.getAllShelves();
            model.addAttribute("shelves", shelves);
            return "addProduct";
        }

        // TODO: remove this, or include in a future admin panel
        @GetMapping("/add/magazyn")
        public String initialize() {
            //this.productManagementService.CreateMagazyn();
            return "redirect:/add/product";
        }

        @PostMapping("/add/product/insert")
        public String addProduct(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "serialNumber") String serialNumber,
                                 @RequestParam(value = "amount") int amount,
                                 @RequestParam(value = "status") String status,
                                 @RequestParam(value = "idShelf") int idShelf) throws NoShelfException {
            if (idShelf == -1) {
                throw new NoShelfException("No shelf selected");
            }
            ProductEntity e = new ProductEntity(name, serialNumber, amount, status);
            try {
                this.productManagementService.putProductOnShelf(e, idShelf);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return "redirect:/add/product";
        }

        @PostMapping("/add/product/annex")
        public String addAnnex(@RequestParam(value = "info") String info,
                               @RequestParam(value = "idProduct") int idProduct) {
            Optional<ProductEntity> e = this.productManagementService.getProductRepo().findById(idProduct);
            if (e.isPresent()) {
                if (e.get().getAnnexEntity() == null) {
                    AnnexEntity annex = new AnnexEntity(LocalDate.now(), info);
                    annex.setProductEntity(e.get());
                    e.get().setAnnexEntity(annex);
                    this.productManagementService.getAnnexRepo().save(annex);
                    this.productManagementService.getProductRepo().save(e.get());
                } else {
                    AnnexEntity annex = e.get().getAnnexEntity();
                    annex.setDate(LocalDate.now());
                    annex.setInfo(info);
                    this.productManagementService.getAnnexRepo().save(annex);
                }
            }
            return "redirect:/add/product";
        }
}
