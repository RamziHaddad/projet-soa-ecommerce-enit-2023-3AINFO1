import java.util.Map;

import jakarta.persistence.criteria.Order;

import enit.bank.Models.Cart;
import enit.bank.Repository.OrderRepository

public interface OrderService {
//    public Cart getCart(Long idCart);
//
//    public Order saveOrder(Cart cart);
//
//    public double getPrice(Map<Long, Integer> prods);
    @Autowired
    private  OrderRepository OrderRepository   ;
    public OrderRepository addOrderRepository(@NotNull OrderRepository OrderRepository) {
        return repoAO.save(OrderRepository);
    }

    public List<OrderRepository> findAllOrderRepositorys() {
        return repoAO.findAll();
    }

    public OrderRepository updateOrderRepository(OrderRepository OrderRepository) {
        return repoAO.save(OrderRepository);
    }

    public OrderRepository findOrderRepositoryById(Integer id) {
        return repoAO.findById(id)
                .orElse(null);


}