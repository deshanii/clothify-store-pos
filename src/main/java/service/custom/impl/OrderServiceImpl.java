package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dto.Order;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.OrderService;
import util.DaoType;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addOrder(Order order) {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        OrderEntity userEntity = new ObjectMapper().convertValue(order, OrderEntity.class);
        return orderDao.add(userEntity);
    }

    @Override
    public boolean deleteOrder(String text) {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        return orderDao.delete(text);
    }

    @Override
    public boolean updateOrder(Order order) {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        OrderEntity orderEntity = new ObjectMapper().convertValue(order, OrderEntity.class);

        return orderDao.update(orderEntity);
    }

    @Override
    public Order findOrder(String id) {
        return null;
    }

    @Override
    public List getOrder() {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        List<OrderEntity> list = orderDao.findAll();
        System.out.println(list);
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        list.forEach(orderEntity -> {
            orderList.add(new ObjectMapper().convertValue(orderEntity,Order.class));
        });
        return orderList;
    }
}
