package org.smruti.moshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("/product")
    public String getProducts() {
        return "admin/products";
    }

    @GetMapping("/product/add")
    public String addProduct() {
        return "admin/forms/add-product";
    }

    @GetMapping("/variant")
    public String getVariant() {
        return "admin/variants";
    }

    @GetMapping("/variant/add")
    public String addVariant() {
        return "admin/forms/add-variant";
    }

    @GetMapping("/stock")
    public String getStock() {
        return "admin/stocks";
    }

    @GetMapping("/stock/add")
    public String addStock() {
        return "admin/forms/add-stock";
    }

    @GetMapping("/type")
    public String getType() {
        return "admin/types";
    }

    @GetMapping("/type/add")
    public String addtype() {
        return "admin/forms/add-type";
    }
}
