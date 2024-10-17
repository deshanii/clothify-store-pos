package service.custom;

import dto.Employee;
import entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    boolean addEmployee (Employee employee);
    boolean deleteEmployee(String text);
    boolean updateEmployee(Employee employee);
    Employee findEmployee(String id);


    List<EmployeeEntity> getEmployee();
}
