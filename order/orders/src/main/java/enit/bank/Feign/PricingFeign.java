package enit.bank.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Pricing", url = "http://localhost:8086/")
public interface PricingFeign {
    @GetMapping(value = "/price", params = { "productId", "quantity" })
    public Double getTotal(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity);
}
