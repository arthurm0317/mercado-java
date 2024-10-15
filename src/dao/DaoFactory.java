package dao;

import dao.impl.CategoryDaoJDBC;
import dao.impl.ProdDaoJDBC;
import db.DB;

public class DaoFactory {
    public static ProductDao createProdDao(){
        return new ProdDaoJDBC(DB.getConnection());
    }
    public static CategoryDao createCatDao(){
        return new CategoryDaoJDBC(DB.getConnection());
    }
}