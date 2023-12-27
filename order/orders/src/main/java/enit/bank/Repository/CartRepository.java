package enit.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enit.bank.Models.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
}
