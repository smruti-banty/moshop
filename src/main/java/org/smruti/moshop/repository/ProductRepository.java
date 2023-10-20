package org.smruti.moshop.repository;

import java.util.List;

import org.smruti.moshop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByProductNameContainingIgnoreCase(String productName, Pageable pageable);

    long countByProductNameContainingIgnoreCase(String productName);
}
