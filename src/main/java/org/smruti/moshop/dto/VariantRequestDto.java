package org.smruti.moshop.dto;

public record VariantRequestDto(String variantId,
        String variantName, String productId, String description,
        double price) {

}
