package org.smruti.moshop.repository;

import org.smruti.moshop.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    
}
