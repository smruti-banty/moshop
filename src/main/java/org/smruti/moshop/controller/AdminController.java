package org.smruti.moshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.smruti.moshop.dto.ProductDto;
import org.smruti.moshop.dto.TypeRequestDto;
import org.smruti.moshop.model.Product;
import org.smruti.moshop.model.Type;
import org.smruti.moshop.service.ProductService;
import org.smruti.moshop.service.StockService;
import org.smruti.moshop.service.TypeService;
import org.smruti.moshop.service.VariantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final StockService stockService;
    private final VariantService variantService;
    private final TypeService typeService;

    @GetMapping("/home")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("/product")
    public String getProducts(Model model) {
        var products = productService.getProductsAsDto();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        var types = typeService.getTypes();
        model.addAttribute("types", types);
        return "admin/forms/add-product";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute ProductDto dto) {
        var product = new Product();
        BeanUtils.copyProperties(dto, product);
        var types = typeService.getTypesById(dto.types());
        productService.createOrUpdate(product, types);

        return "redirect:/admin/product";
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
    public String getType(Model model) {
        var types = typeService.getTypes();
        model.addAttribute("types", types);
        return "admin/types";
    }

    @GetMapping("/type/add")
    public String addTypePage() {
        return "admin/forms/add-type";
    }

    @PostMapping("/type/add")
    public String addType(@ModelAttribute TypeRequestDto dto) {
        var type = new Type();
        BeanUtils.copyProperties(dto, type);
        typeService.createOrUpdate(type);
        return "redirect:/admin/type";
    }
}