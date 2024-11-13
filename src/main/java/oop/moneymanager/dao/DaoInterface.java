package oop.moneymanager.dao;

import oop.moneymanager.model.TransactionModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface DaoInterface <T> {
    int insert(T t) throws SQLException;
    int update(T t);
    int delete(T t);
    ArrayList<T> selectAll();
    ArrayList<T> selectByCondition(String condition);
    T selectByID(String ID);
}
