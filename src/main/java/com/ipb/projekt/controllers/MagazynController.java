package com.ipb.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagazynController {

    @GetMapping("/magazyn")
    public String magazynHandler() {
        return "magazyn";
    }
}
