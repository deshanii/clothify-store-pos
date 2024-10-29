package dao.custom.impl;

import dao.custom.CustomerDao;
import entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean add(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(customerEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CustomerEntity customer) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(customer.getCustID(),customer);
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(CustomerEntity.class,id));
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<CustomerEntity> findAll() {
        Session session = HibernateUtil.getSession();
        List<CustomerEntity> customerList = session.createQuery("FROM customer").list();
        return customerList;
    }

    @Override
    public String findlastId() {
        return "";
    }

    @Override
    public CustomerEntity find(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM customer WHERE id=:id");
        query.setParameter("id", id);

        CustomerEntity customerEntity = (CustomerEntity) query.uniqueResult();
        session.close();
        return customerEntity;
    }
}
