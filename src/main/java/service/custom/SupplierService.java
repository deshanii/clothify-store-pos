package service.custom;

import dto.Employee;
import dto.Supplier;
import entity.EmployeeEntity;
import entity.SupplierEntity;

import java.util.List;

public interface SupplierService {

    boolean addSupplier (Supplier supplier);
    boolean deleteSupplier(String text);
    boolean updateSupplier(Supplier supplier);
    boolean findSupplier(String name);

    List<SupplierEntity> getSupplier();
}
