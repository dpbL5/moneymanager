package oop.moneymanager.service;

import java.util.ArrayList;

import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;

public class DataManipulation {
    public ArrayList<TransactionModel> getTransactionModels(String username) {
        TransactionDao tran = TransactionDao.getInstance();
        return tran.selectByCondition(String.format("username = \"%s\"", username));
    }
}
