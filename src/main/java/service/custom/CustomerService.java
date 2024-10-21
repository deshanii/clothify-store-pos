package service.custom;

import dto.Customer;

import java.util.List;

public interface CustomerService {
    boolean addCustomer (Customer customer);
    boolean deleteCustomer(String text);
    boolean updateCustomer(Customer customer);
    Customer findCustomer(String id);

    List<Customer> getCustomer();
}
