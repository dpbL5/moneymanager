package oop.moneymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import oop.moneymanager.model.TransactionModel;

public class TransactionDao implements DaoInterface<TransactionModel>{

    @Override
    public int insert(TransactionModel t) throws SQLException {
        String url = "INSERT INTO transaction (id, username, day, month, year, hour, minute, category, type, fromAccount, amount, note) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, t.getId());
            stmt.setString(2, t.getUsername());
            stmt.setInt(3, t.getTransactionDateTime().getDayOfMonth());
            stmt.setInt(4, t.getTransactionDateTime().getMonthValue());
            stmt.setInt(5, t.getTransactionDateTime().getYear());
            stmt.setInt(6, t.getTransactionDateTime().getHour());
            stmt.setInt(7, t.getTransactionDateTime().getMinute());
            stmt.setString(8, t.getCategory());
            stmt.setString(9, t.getType().toString());
            stmt.setString(10, t.getFromAccount());
            stmt.setLong(11, t.getAmount());
            stmt.setString(12, t.getNote());
            int row = stmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(TransactionModel t) {
        String url = 
        "UPDATE transaction SET username = ?, day = ?, month = ?, year = ?, hour = ?, "+
        "minute = ?, category = ?, type = ?, fromAccount = ?, amount = ?, note = ? WHERE id = ?";
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, t.getUsername());
            stmt.setInt(2, t.getTransactionDateTime().getDayOfMonth());
            stmt.setInt(3, t.getTransactionDateTime().getMonthValue());
            stmt.setInt(4, t.getTransactionDateTime().getYear());
            stmt.setInt(5, t.getTransactionDateTime().getHour());
            stmt.setInt(6, t.getTransactionDateTime().getMinute());
            stmt.setString(7, t.getCategory());
            stmt.setString(8, t.getType().toString());
            stmt.setString(9, t.getFromAccount());
            stmt.setLong(10, t.getAmount());
            stmt.setString(11, t.getNote());
            stmt.setString(12, t.getId());
            int row = stmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(TransactionModel t) {
        String url = "DELETE FROM transaction WHERE id = ?";
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, t.getId());
            int row = stmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<TransactionModel> selectAll() {
        String url = "SELECT * FROM transaction WHERE username = ?";
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(url)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                TransactionModel x = new TransactionModel();
                transactions.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public ArrayList<TransactionModel> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByCondition'");
    }

    @Override
    public TransactionModel selectByID(String ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByID'");
    }
    
}
