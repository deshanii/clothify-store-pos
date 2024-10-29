package dao.custom.impl;

import dao.custom.OrderDao;
import entity.OrderEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orderEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity order) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(order.getOrderID(),order);
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(OrderEntity.class,id));
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<OrderEntity> findAll() {
        Session session = HibernateUtil.getSession();
        List<OrderEntity> orderList = session.createQuery("FROM order_table", OrderEntity.class).getResultList();
        return orderList;
    }

    @Override
    public String findlastId() {
        return "";
    }

    @Override
    public OrderEntity find(String id) {
        return null;
    }
}
