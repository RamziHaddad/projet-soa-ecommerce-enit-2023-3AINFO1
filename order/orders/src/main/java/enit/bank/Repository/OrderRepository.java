package enit.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enit.bank.Models.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    
}
