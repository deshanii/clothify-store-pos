package dao.custom.impl;

import dao.custom.EmployeeDao;
import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean add(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        //EmployeeEntity employeeEntity = new EmployeeEntity(employee.getEmpID(), employee.getTitle(), employee.getName(), employee.getAddress(), employee.getDob(), employee.getContactNumber(), employee.getBankAccNo(), employee.getNic());

        session.persist(employeeEntity);
        session.getTransaction().commit();
        session.close();
        return true;


    }


    @Override
    public boolean update(EmployeeEntity employee)  {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(employee.getEmpID(),employee);
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id)  {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(EmployeeEntity.class,id));
        session.getTransaction().commit();
        return true;
    }



    @Override
    public String findlastId()  {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public EmployeeEntity find(String id)  {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM employee WHERE id=:id");
        query.setParameter("id", id);

        EmployeeEntity employee = (EmployeeEntity) query.uniqueResult();
        session.close();
        return employee;
    }

    @Override
    public List<EmployeeEntity> findAll()  {
        Session session = HibernateUtil.getSession();
        List<EmployeeEntity> employeeList = session.createQuery("FROM employee").list();
        return employeeList;
    }
}
