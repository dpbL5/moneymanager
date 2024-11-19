package oop.moneymanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import oop.moneymanager.model.TransactionModel;

//     1 `tran_id` INT NOT NULL AUTO_INCREMENT,
///     2 `category` VARCHAR(255) NOT NULL,  
//     3 `type` VARCHAR(255) NOT NULL, 	
//     4 `amount` DECIMAL(10, 2) NOT NULL,
//     5`note` VARCHAR(255) NOT NULL,
//     6`username` VARCHAR(255) NOT NULL,
//     7 `transaction_kind` VARCHAR(255) NOT NULL,
//     8 `transaction_date` DATE NOT NULL,
//      PRIMARY KEY (`tran_id`),

public class TransactionDao implements DaoInterface<TransactionModel>{
    public static TransactionDao getInstance() {
        return new TransactionDao();
    }

    @Override
    public int insert(TransactionModel t) throws SQLException {
        String url = "INSERT INTO transaction (category, type, amount, note, username, transaction_kind, transaction_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(url);
            statement.setString(1, t.getCategory());
            statement.setString(2, t.getType().toString());
            statement.setDouble(3, t.getAmount());
            statement.setString(4, t.getNote());
            statement.setString(5, t.getUsername());
            statement.setString(6, t.getKind().toString());
            statement.setString(7, t.getDate().toString());

            int row = statement.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public int update(TransactionModel t) {
        String url = "UPDATE transactions " +
            "SET category = ?, type = ?, amount = ?, note = ?, username = ?, transaction_kind = ?, transaction_date = ?" + 
            "WHERE tran_id = ?";
        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(url);
            statement.setString(1, t.getCategory());
            statement.setString(2, t.getType().toString());
            statement.setDouble(3, t.getAmount());
            statement.setString(4, t.getNote());
            statement.setString(5, t.getUsername());
            statement.setString(6, t.getKind().toString());
            statement.setDate(7, Date.valueOf(t.getDate()));
            statement.setInt(8, t.getId());
            int row = statement.executeUpdate(url);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(TransactionModel t) {
        String url = "DELETE FROM transactions WHERE tran_id = ?";
        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(url);
            statement.setInt(1, t.getId());
            int row = statement.executeUpdate(url);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<TransactionModel> selectAll() {
        String url = "SELECT * FROM transaction";
        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(url);
            var result = statement.executeQuery(url);
            ArrayList<TransactionModel> list = new ArrayList<>();
            while (result.next()) {
                list.add(new TransactionModel(
                    result.getInt(0), 
                    result.getString(6), 
                    result.getString(2), 
                    result.getDouble(4), 
                    result.getString(5), 
                    LocalDate.parse(result.getString(8)),
                    TransactionModel.TransactionType.valueOf(result.getString(3)),
                    TransactionModel.TransactionKind.valueOf(result.getString(7))
                ));
            }
            return list;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<TransactionModel> selectByCondition(String condition) {
        String url = "SELECT * FROM transactions WHERE " + condition;
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement statement = con.prepareStatement(url);
            var result = statement.executeQuery(url)) {
            ArrayList<TransactionModel> list = new ArrayList<>();
            while (result.next()) {
                list.add(new TransactionModel(
                    result.getInt(0), 
                    result.getString(6), 
                    result.getString(2), 
                    result.getDouble(4), 
                    result.getString(5), 
                    LocalDate.parse(result.getString(8)),
                    TransactionModel.TransactionType.valueOf(result.getString(3)),
                    TransactionModel.TransactionKind.valueOf(result.getString(7))
                ));
            }
            return list;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public TransactionModel selectByID(String ID) {
        String url = "SELECT * FROM transactions WHERE trans_id = " + ID;
        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(url);
            var result = statement.executeQuery(url);
            if (result.next()) {
                return new TransactionModel(
                    result.getInt(0), 
                    result.getString(6), 
                    result.getString(2), 
                    result.getDouble(4), 
                    result.getString(5), 
                    LocalDate.parse(result.getString(8)),
                    TransactionModel.TransactionType.valueOf(result.getString(3)),
                    TransactionModel.TransactionKind.valueOf(result.getString(7))
                );
            }
            return null;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<TransactionModel> selectByRangedateAndUsername(String username, LocalDate dateStart, LocalDate dateEnd) {
        List<TransactionModel> transactions = new ArrayList<>();
        String query = "SELECT category, type, amount FROM transaction WHERE username = ? AND transaction_date >= ? AND transaction_date <= ?";

        try (var connection = JDBCUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);

            // Thiết lập các tham số cho truy vấn
            statement.setString(1, username);
            statement.setDate(2, java.sql.Date.valueOf(dateStart));
            statement.setDate(3, java.sql.Date.valueOf(dateEnd));
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                transactions.add(new TransactionModel(category, type, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }


    
}