package org.smruti.moshop.repository;

import org.smruti.moshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    
}
