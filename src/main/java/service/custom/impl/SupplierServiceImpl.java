package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.SupplierDao;
import dto.Supplier;
import entity.CustomerEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getDaoFactory().getDaoType(DaoType.SUPPLIER);
        SupplierEntity userEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return supplierDao.add(userEntity);
    }


    @Override
    public boolean deleteSupplier(String text) {
        SupplierDao supplierDao = DaoFactory.getDaoFactory().getDaoType(DaoType.SUPPLIER);
        return supplierDao.delete(text);
    }


    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getDaoFactory().getDaoType(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);

        return supplierDao.update(supplierEntity);
    }



    @Override
    public Supplier findSupplier(String name) {
        SupplierDao supplierDao = DaoFactory.getDaoFactory().getDaoType(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = supplierDao.find(name);

        return new ObjectMapper().convertValue(supplierEntity, Supplier.class);

    }


    @Override
    public List getSupplier() {
        SupplierDao supplierDao = DaoFactory.getDaoFactory().getDaoType(DaoType.SUPPLIER);
        List<SupplierEntity> list = supplierDao.findAll();
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        list.forEach(supplierEntity -> {
            supplierList.add(new ObjectMapper().convertValue(supplierEntity,Supplier.class));
        });
        return supplierList;
    }
}
