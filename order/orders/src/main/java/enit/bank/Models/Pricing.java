package enit.bank.Models;

import java.util.Map;

import jakarta.persistence.Entity;

@Entity

public class Pricing {
    
    private Long idItem;
    private Double price;

    public Double GetTotalPrice(int quantity) {
        return 0.0;
    };
}