package org.smruti.moshop.service;

import java.util.List;

import org.smruti.moshop.model.Variant;
import org.smruti.moshop.repository.VariantRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariantService {
    private final VariantRepository variantRepository;

    public void createOrUpdate(Variant variant) {
        variantRepository.save(variant);
    }

    public List<Variant> getVariants() {
        return variantRepository.findAll();
    }
}