package dao.custom.impl;

import dao.custom.SupplierDao;
import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean add(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(supplierEntity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(supplierEntity.getSupplierID(),supplierEntity);
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(SupplierEntity.class,id));
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<SupplierEntity> findAll() {
        Session session = HibernateUtil.getSession();
        List<SupplierEntity> supplierList = session.createQuery("FROM supplier").list();
        return supplierList;
    }

    @Override
    public String findlastId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public SupplierEntity find(String name) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM supplier WHERE name=:name");
        query.setParameter("name", name);

        SupplierEntity supplierEntity = (SupplierEntity) query.uniqueResult();
        session.close();
        return supplierEntity;
    }
}
