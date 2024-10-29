package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.OrderDetailsDao;
import dto.OrderDetails;
import entity.OrderDetailsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.OrderDetailsService;
import util.DaoType;

import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {
//    @Override
//    public boolean addOrderDetails(OrderDetails orderDetails) {
//        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
//        OrderDetailsEntity userEntity = new ObjectMapper().convertValue(orderDetails, OrderDetailsEntity.class);
//        return orderDetailsDao.add(userEntity);
//    }

    @Override
    public boolean deleteOrderDetails(String text) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        return orderDetailsDao.delete(text);
    }

    @Override
    public boolean updateOrderDetails(OrderDetails orderDetails) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        OrderDetailsEntity orderDetailsEntity = new ObjectMapper().convertValue(orderDetails, OrderDetailsEntity.class);

        return orderDetailsDao.update(orderDetailsEntity);
    }

    @Override
    public OrderDetails findOrderDetails(String id) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        OrderDetailsEntity orderDetailsEntity = orderDetailsDao.find(id);

        return new ObjectMapper().convertValue(orderDetailsEntity,OrderDetails.class);
    }

    @Override
    public List getOrderDetails() {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        List<OrderDetailsEntity> list = orderDetailsDao.findAll();
        ObservableList<OrderDetails> orderDetailsList = FXCollections.observableArrayList();
        list.forEach(orderDetailsEntity -> {
            orderDetailsList.add(new ObjectMapper().convertValue(orderDetailsEntity,OrderDetails.class));
        });
        return orderDetailsList;
    }
}
