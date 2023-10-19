package org.smruti.moshop.controller.admin;

import org.smruti.moshop.dto.PageDto;
import org.smruti.moshop.dto.ProductDto;
import org.smruti.moshop.model.Product;
import org.smruti.moshop.service.ProductService;
import org.smruti.moshop.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final TypeService typeService;

    @GetMapping
    public String getProducts(Model model) {
        return getProduct(1, 5, model);
    }

    @GetMapping("/size")
    public String getProductBySize(@RequestParam int pageSize, Model model) {
        return getProduct(1, pageSize, model);
    }

    @GetMapping("/page")
    public String getProductByPage(@RequestParam int pageNumber, @RequestParam int pageSize, Model model) {
        return getProduct(pageNumber, pageSize, model);
    }

    @GetMapping("/search")
    public String getProductSearch() {
        return "admin/products";
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {
        var types = typeService.getTypes();
        model.addAttribute("types", types);
        return "admin/forms/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDto dto) {
        var product = new Product();
        BeanUtils.copyProperties(dto, product);
        var types = typeService.getTypesById(dto.types());
        productService.createOrUpdate(product, types);

        return "redirect:/admin/product";
    }

    private String getProduct(int pageNumber, int pageSize, Model model) {
        var products = productService.getProductsAsDto(pageNumber, pageSize);
        model.addAttribute("products", products);

        var totalRows = productService.countAll();
        var totalPage = Math.round(Math.ceil(totalRows / pageSize) + 1);

        var page = new PageDto(pageSize, pageNumber, totalRows, totalPage);
        model.addAttribute("page", page);
        return "admin/products";
    }
}
