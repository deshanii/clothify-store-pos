package dao.custom.impl;

import dao.custom.ItemDao;
import entity.ItemEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(ItemEntity itemEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(itemEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(ItemEntity item) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(item.getItemCode(),item);
        session.getTransaction().commit();

        return true;
    }


    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(ItemEntity.class,id));
        session.getTransaction().commit();
        return true;
    }


    @Override
    public List<ItemEntity> findAll() {
        Session session = HibernateUtil.getSession();
        List<ItemEntity> itemList = session.createQuery("FROM item").list();
        return itemList;
    }


    @Override
    public String findlastId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM item ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }


    @Override
    public ItemEntity find(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM item WHERE id=:id");
        query.setParameter("id", id);

        ItemEntity itemEntity = (ItemEntity) query.uniqueResult();
        session.close();
        return itemEntity;
    }
}
