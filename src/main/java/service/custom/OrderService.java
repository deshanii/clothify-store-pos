package service.custom;

import dto.Order;

import java.util.List;

public interface OrderService {
    boolean addOrder (Order order);
    boolean deleteOrder(String text);
    boolean updateOrder(Order order);
    Order findOrder(String id);

    List<Order> getOrder();
}
