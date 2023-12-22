package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class ProductPrice {
    @Id
    private UUID productId;
    private double productPrice;

    public ProductPrice() {
    }



}
