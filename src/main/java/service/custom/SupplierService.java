package service.custom;

import dto.Supplier;

import java.util.List;

public interface SupplierService {

    boolean addSupplier (Supplier supplier);
    boolean deleteSupplier(String text);
    boolean updateSupplier(Supplier supplier);
    Supplier findSupplier(String name);

    List<Supplier> getSupplier();
}
