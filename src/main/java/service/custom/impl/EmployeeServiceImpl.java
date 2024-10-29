package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.EmployeeService;
import util.DaoType;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity userEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        return employeeDao.add(userEntity);
    }

    @Override
    public boolean deleteEmployee(String text) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.delete(text);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);

        return employeeDao.update(employeeEntity);


    }

    @Override
    public Employee findEmployee(String id) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = employeeDao.find(id);

        return new ObjectMapper().convertValue(employeeEntity,Employee.class);

    }

    @Override
    public List getEmployee() {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        List<EmployeeEntity> list = employeeDao.findAll();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        list.forEach(employeeEntity -> {
            employeeList.add(new ObjectMapper().convertValue(employeeEntity,Employee.class));
        });
        return employeeList;
    }
}
