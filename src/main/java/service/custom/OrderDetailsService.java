package service.custom;

import dto.Employee;
import dto.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    boolean addOrderDetails (OrderDetails orderDetails);
    boolean deleteOrderDetails(String text);
    boolean updateOrderDetails(OrderDetails orderDetails);
    OrderDetails findOrderDetails(String id);

    List<OrderDetails> getOrderDetails();
}
