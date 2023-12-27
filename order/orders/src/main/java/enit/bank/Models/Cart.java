package enit.bank.Models;


import java.util.Date;
import java.util.Collection;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;
    
    private String userId;
    private Collection<Long> productId;
    private int quantity;
    private Date date;

    
}
