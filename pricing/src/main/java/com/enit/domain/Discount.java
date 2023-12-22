package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Discount{
    @Id
    private UUID discountId;
    private double discountPercentage;
    private LocalDateTime discountStartDate;
    private LocalDateTime discountEndDate;
    private boolean discountValidation;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductPrice product;

    public Discount()
    {
        this.discountValidation = false;
    }
    public Discount(UUID idProduct, double percentage, LocalDateTime startDate, LocalDateTime endDate) {
        //idproduct -> product
        this.discountId = UUID.randomUUID();
        this.discountPercentage = percentage;
        this.discountStartDate = startDate;
        this.discountEndDate = endDate;
        LocalDateTime currentDate = LocalDateTime.now();
        if(currentDate.isEqual(startDate) || currentDate.isEqual(endDate) ||
                (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)))
        {
            this.discountValidation = true;
        }
        else {
            this.discountValidation = false;
        }
        this.product= new ProductPrice(product.getProductId(),product.getProductPrice());
    }

    public boolean isValid() {
        return this.discountValidation;
    }
}