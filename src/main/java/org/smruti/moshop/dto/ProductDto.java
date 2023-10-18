package org.smruti.moshop.dto;

import java.util.List;

public record ProductDto(String productId, String productName, String productBrand, List<String> types) {

}
