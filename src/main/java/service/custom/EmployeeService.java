package service.custom;

import dto.Employee;


import java.util.List;

public interface EmployeeService {

    boolean addEmployee (Employee employee);
    boolean deleteEmployee(String text);
    boolean updateEmployee(Employee employee);
    Employee findEmployee(String id);

    List<Employee> getEmployee();
}
