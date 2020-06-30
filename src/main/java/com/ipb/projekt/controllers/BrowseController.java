package com.ipb.projekt.controllers;

import com.ipb.projekt.entities.ProductEntity;
import com.ipb.projekt.entities.ShelfEntity;
import com.ipb.projekt.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BrowseController {

    final ProductManagementService productManagementService;

    @Autowired
    public BrowseController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/browse/product")
    public String browseProduct(Model model) {
        Iterable<ShelfEntity> shelves = this.productManagementService.getAllShelves();
        Iterable<ProductEntity> products = this.productManagementService.getAllProducts();
        model.addAttribute("shelves", shelves);
        model.addAttribute("products", products);
        return "browseProduct";
    }

    @GetMapping("/browse/product/search")
    public String browseProductSearch(@RequestParam(name = "searchString") String searchString,
                                      Model model) {
        Iterable<ShelfEntity> shelves = this.productManagementService.getAllShelves();
        Iterable<ProductEntity> products = this.productManagementService.getAllProducts();
        ArrayList<ProductEntity> productsFinal = new ArrayList<>();
        for (ProductEntity productEntity : products) {
            boolean flag = true;
            if (similarity(productEntity.getName(), searchString) > 0.5 && flag) {
                productsFinal.add(productEntity);
                flag = false;
            }
            if (similarity(productEntity.getStatus(), searchString) > 0.5 && flag) {
                productsFinal.add(productEntity);
                flag = false;
            }
            if (similarity(productEntity.getSerialNumber(), searchString) > 0.5 && flag) {
                productsFinal.add(productEntity);
                flag = false;
            }
            if (similarity(String.valueOf(productEntity.getAmount()), searchString) > 0.5 && flag) {
                productsFinal.add(productEntity);
                flag = false;
            }
            if (productEntity.getAnnexEntity() != null) {
                if (similarity(productEntity.getAnnexEntity().getInfo(), searchString) > 0.5 && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
                if (similarity(String.valueOf(productEntity.getAnnexEntity().getDate()), searchString) > 0.5 && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
            }
            if (productEntity.getShelfEntity() != null) {
                if (String.valueOf(productEntity.getShelfEntity().getIdShelf()).equals(searchString) && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
                if (similarity(String.valueOf(productEntity.getShelfEntity().getLevel()), searchString) > 0.5 && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
                if (similarity(String.valueOf(productEntity.getShelfEntity().getColumn()), searchString) > 0.5 && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
                if (similarity(String.valueOf(productEntity.getShelfEntity().getRow()), searchString) > 0.5 && flag) {
                    productsFinal.add(productEntity);
                    flag = false;
                }
            }

        }
        model.addAttribute("shelves", shelves);
        model.addAttribute("products", productsFinal);
        return "browseProduct";
    }


    //TODO: re-evaluate the the used here similarity function, see if other distance functions
    // work better
    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
