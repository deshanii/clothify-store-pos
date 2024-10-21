package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.EmployeeDao;
import dto.Customer;
import dto.Employee;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.CustomerService;
import util.DaoType;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public boolean addCustomer(Customer customer) {
        CustomerDao customerDao = DaoFactory.getDaoFactory().getDaoType(DaoType.CUSTOMER);
        CustomerEntity userEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);
        return customerDao.add(userEntity);
    }

    @Override
    public boolean deleteCustomer(String text) {
        CustomerDao customerDao = DaoFactory.getDaoFactory().getDaoType(DaoType.CUSTOMER);
        return customerDao.delete(text);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerDao customerDao = DaoFactory.getDaoFactory().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);

        return customerDao.update(customerEntity);
    }

    @Override
    public Customer findCustomer(String id) {
        CustomerDao customerDao = DaoFactory.getDaoFactory().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = customerDao.find(id);

        return new ObjectMapper().convertValue(customerEntity, Customer.class);
    }

    @Override
    public List<Customer> getCustomer() {
        CustomerDao customerDao = DaoFactory.getDaoFactory().getDaoType(DaoType.CUSTOMER);
        List<CustomerEntity> list = customerDao.findAll();
        ObservableList<Customer>customerList = FXCollections.observableArrayList();
        list.forEach(customerEntity-> {
            customerList.add(new ObjectMapper().convertValue(customerEntity,Customer.class));
        });
        return customerList;
    }
}
