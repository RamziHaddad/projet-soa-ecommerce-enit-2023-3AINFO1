package com.example.shipping.repository;

import com.example.shipping.shipping.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {


    Optional<Shipping> findByIdShipping(Long idShipping);
    List<Shipping> findShippingsByIdUser(Long idUser);
}
