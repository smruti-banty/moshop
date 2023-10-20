package org.smruti.moshop.controller.admin;

import java.util.List;

import java.util.Base64;
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
    public String getProducts(@RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "0") int pageSize,
            @RequestParam(required = false, defaultValue = "") String search, Model model) {
        pageNumber = pageNumber == 0 ? 1 : pageNumber;
        pageSize = pageSize == 0 ? 5 : pageSize;

        model.addAttribute("searchItem", search);

        model.addAttribute("search", !search.isBlank());
        return getProduct(pageNumber, pageSize, search, model);
    }

    @PostMapping("/search")
    public String getProductSearch(@RequestParam String search, Model model) {
        search = search.isBlank() ? "" : search;
        return "redirect:/admin/product?search=" + search;
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

    private String getProduct(int pageNumber, int pageSize, String search, Model model) {
        List<ProductDto> products = null;
        long totalRows = 0;

        if (search.isBlank()) {
            products = productService.getProductsAsDto(pageNumber, pageSize);
            totalRows = productService.countAll();
        } else {
            products = productService.getProductsByNameAsDto(pageNumber, pageSize, search);
            totalRows = productService.countByName(search);
        }

        model.addAttribute("products", products);

        var totalPage = getTotalPage(totalRows, pageSize);
        var page = new PageDto(pageSize, pageNumber, totalRows, totalPage);
        model.addAttribute("page", page);
        return "admin/products";
    }

    private long getTotalPage(long totalRows, long pageSize) {
        var totalPage = totalRows % pageSize;
        var result = totalRows / pageSize;
        return totalRows == 0 || totalPage != 0 ? result + 1 : result;
    }
}
