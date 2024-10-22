package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import entity.OrderDetailsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean add(OrderDetailsEntity orderDetailsEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orderDetailsEntity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(orderDetailsEntity.getOrderID(),orderDetailsEntity);
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(OrderDetailsEntity.class,id));
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<OrderDetailsEntity> findAll() {
        Session session = HibernateUtil.getSession();
        List<OrderDetailsEntity> orderDetailsList = session.createQuery("FROM orderDetails").list();
        return orderDetailsList;
    }

    @Override
    public String findlastId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM orderDetails ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public OrderDetailsEntity find(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM orderDetails WHERE id=:id");
        query.setParameter("id", id);

        OrderDetailsEntity orderDetailsEntity = (OrderDetailsEntity) query.uniqueResult();
        session.close();
        return orderDetailsEntity;
    }
}
