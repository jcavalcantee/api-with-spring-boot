package com.example.springboot.dtos;

import java.math.BigDecimal;

public record ProductDto(
        String name,
        BigDecimal price
) {
}
