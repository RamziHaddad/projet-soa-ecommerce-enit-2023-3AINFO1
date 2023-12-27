package enit.bank.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import enit.bank.Models.Cart;

@FeignClient(name = "Cart", url = "http://localhost:8081/")

public interface CartFeign {
    @GetMapping(value = "/carts/{id}")
    public Cart getCartById(@PathVariable(value = "id") Long idCart);

}
