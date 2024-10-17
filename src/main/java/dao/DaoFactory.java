package dao;


import dao.custom.impl.*;

import static util.DaoType.*;

public class DaoFactory {
    private static DaoFactory daoFactory;
    public DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        return daoFactory!=null?daoFactory:(daoFactory=new DaoFactory());
    }


    public <T extends SuperDao>T getDaoType(util.DaoType type){
        switch (type){
            case ITEM : return (T)new ItemDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
           // case ORDER:return (T) new OrderDaoImpl();
           // case ORDERDETAILS:return (T) new OrderDetailsDaoImpl();
            default:return null;
        }
    }
}
