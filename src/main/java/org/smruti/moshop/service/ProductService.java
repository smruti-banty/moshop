package org.smruti.moshop.service;

import java.util.ArrayList;
import java.util.List;

import org.smruti.moshop.dto.ProductDto;
import org.smruti.moshop.model.Product;
import org.smruti.moshop.model.Type;
import org.smruti.moshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createOrUpdate(Product product) {
        productRepository.save(product);
    }

    public void createOrUpdate(Product product, List<Type> types) {
        if (types == null || !types.isEmpty()) {
            product.setProductType(types);
        }
        productRepository.save(product);
    }

    public List<ProductDto> getProductsAsDto() {
        var products = productRepository.findAll();

        List<ProductDto> dto = new ArrayList<>();
        products.forEach(product -> {
            var productId = product.getProductId();
            var productName = product.getProductName();
            var productBrand = product.getProductBrand();
            var types = product.getProductType();

            List<String> typeNames = new ArrayList<String>();
            if (types != null) {
                typeNames = types.stream().map(type -> type.getTypeName()).toList();
            }

            var productDto = new ProductDto(productId, productName, productBrand, typeNames);
            dto.add(productDto);
        });

        return dto;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
