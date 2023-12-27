package enit.bank.Controller;

import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.GET;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import enit.bank.Feign.CartFeign;
import enit.bank.Models.Cart;

@RestController
public class OrderController {
    @Autowired
    private Orderservice Orderservice;
    CartFeign cartFeign;

    @GetMapping(value = "/cart/{idCart}")
    public Cart getCart(@PathVariable(value = "idCart") Long idCart) {
        Cart cart = cartFeign.getCartById(idCart);
        return cart;
    }
    @Autowired
    private Orderservice Orderservice;


    @GetMapping("/all")
    public ResponseEntity<List<Appeldoffre>> getAllOrder () {
        List<Appeldoffre> Order = Orderservice.findAllOrder();
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Appeldoffre> getAppeldoffreById (@PathVariable("id") Integer id) {
        Appeldoffre Appeldoffre = Orderservice.findAppeldoffreById(id);
        return new ResponseEntity<>(Appeldoffre, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Appeldoffre> addAppeldoffre(@RequestBody Appeldoffre Appeldoffre) {
        Appeldoffre newAppeldoffre = Orderservice.addAppeldoffre(Appeldoffre);
        return new ResponseEntity<>(newAppeldoffre, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Appeldoffre> updateAppeldoffre(@RequestBody Appeldoffre Appeldoffre) {
        Appeldoffre updateAppeldoffre = Orderservice.updateAppeldoffre(Appeldoffre);
        return new ResponseEntity<>(updateAppeldoffre, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppeldoffre(@PathVariable("id") Integer id) {
        Orderservice.deleteAppeldoffre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
