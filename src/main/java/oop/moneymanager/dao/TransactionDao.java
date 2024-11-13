package oop.moneymanager.dao;

import oop.moneymanager.model.TransactionModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionDao implements DaoInterface<TransactionModel> {
    public static TransactionDao instance()
    {
        return new TransactionDao();
    };

    @Override
    public int insert(TransactionModel transaction) {
        String url = "INSERT INTO transaction VALUES(?,?,?,?,?,?,?)";
        try (Connection con = JDBCUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,transaction.getTransactionID());
            stmt.setString(2,transaction.getCategory());
            stmt.setDouble(3,transaction.getIncome());
            stmt.setDouble(4,transaction.getOutcome());
            stmt.setString(5,transaction.getNote());
            stmt.setString(6,transaction.getUsername());
            stmt.setDate(6, (Date) transaction.getDate());
            int row = stmt.executeUpdate();
            return row;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int update(TransactionModel transactionModel) {
        return 0;
    }

    @Override
    public int delete(TransactionModel transactionModel) {
        return 0;
    }

    @Override
    public ArrayList<TransactionModel> selectAll() {
        String url = "SELECT * FROM transaction";
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(url)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionModel x = new TransactionModel(rs.getString("tran_id"),rs.getString("category"),rs.getDouble("income"),rs.getDouble("outcome"),
                        rs.getString("note"),rs.getString("username"),rs.getDate("date")
                        );
                transactions.add(x);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        return transactions;
    }

    @Override
    public ArrayList<TransactionModel> selectByCondition(String condition) {
        String url = "SELECT * FROM transaction WHERE date = ? ";
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)){
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionModel x = new TransactionModel(rs.getString("tran_id"),rs.getString("category"),rs.getDouble("income"),rs.getDouble("outcome"),
                        rs.getString("note"),rs.getString("username"),rs.getDate("date")
                );
                transactions.add(x);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        return transactions;
    }


    @Override
    public TransactionModel selectByID(String ID) {
        return null;
    }


    public ArrayList<TransactionModel> getTransactionsByUsernameAndDateRange(String username, LocalDate startDate, LocalDate endDate) {
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        String query = "SELECT category, income, outcome FROM transaction WHERE username = ? AND transaction_date >= ? AND transaction_date <= ?";
        try (   Connection con = JDBCUtil.getConnection();
                PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                double income = rs.getDouble("income");
                double outcome = rs.getDouble("outcome");
                transactions.add(new TransactionModel(category, income, outcome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
