package org.smruti.moshop.repository;

import org.smruti.moshop.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, String> {
    
}
