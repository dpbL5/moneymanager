package oop.moneymanager.dao;

import oop.moneymanager.model.BudgetModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BudgetDao implements DaoInterface<BudgetModel> {

    @Override
    public int insert(BudgetModel budgetModel) throws SQLException {
        String sql = "INSERT INTO budget (username, date_init, amount) VALUES (?, ?, ?)";;
        try(Connection con = JDBCUtil.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, budgetModel.getUsername());
            ps.setDate(2, Date.valueOf(budgetModel.getDate_init()));
            ps.setDouble(3, budgetModel.getAmount());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(BudgetModel budgetModel) {
        String sql = "UPDATE budget " +
                "SET amount = ? " +
                "WHERE username = ? " +
                "AND MONTH(date_init) = MONTH(CURRENT_DATE()) " +
                "AND YEAR(date_init) = YEAR(CURRENT_DATE())";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, budgetModel.getAmount()); // Giá trị mới cho amount
            statement.setString(2, budgetModel.getUsername()); // Điều kiện username

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Budget limit updated successfully.");
            } else {
                System.out.println("No matching budget found for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(BudgetModel budgetModel) {
        return 0;
    }

    @Override
    public ArrayList<BudgetModel> selectAll() {
        return null;
    }

    @Override
    public ArrayList<BudgetModel> selectByCondition(String condition) {
        return null;
    }

    @Override
    public BudgetModel selectByID(String ID) {
        return null;
    }
    public Map<String, Double> getBudgetAndExpenseSummary(String username) {
        Map<String, Double> summary = new HashMap<>();

        String sql = "SELECT (SELECT COALESCE(SUM(amount), 0) "
                + "FROM budget WHERE username = ? AND MONTH(date_init) = MONTH(CURRENT_DATE()) "
                + "AND YEAR(date_init) = YEAR(CURRENT_DATE())) AS total_limit, "
                + "(SELECT COALESCE(SUM(amount), 0) FROM transaction "
                + "WHERE username = ? AND type = 'EXPENSE' "
                + "AND MONTH(transaction_date) = MONTH(CURRENT_DATE()) "
                + "AND YEAR(transaction_date) = YEAR(CURRENT_DATE())) AS total_expense";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                summary.put("total_limit", resultSet.getDouble("total_limit"));
                summary.put("total_expense", resultSet.getDouble("total_expense"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summary;
    }
//    public void updateBudgetLimit(String username, double newLimit) {
//        String sql = "UPDATE budget " +
//                "SET amount = ? " +
//                "WHERE username = ? " +
//                "AND MONTH(date_init) = MONTH(CURRENT_DATE()) " +
//                "AND YEAR(date_init) = YEAR(CURRENT_DATE())";
//        try (Connection connection = JDBCUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setDouble(1, newLimit); // Giá trị mới cho amount
//            statement.setString(2, username); // Điều kiện username
//
//            int rowsAffected = statement.executeUpdate(); // Thực thi lệnh UPDATE
//            if (rowsAffected > 0) {
//                System.out.println("Budget limit updated successfully.");
//            } else {
//                System.out.println("No matching budget found for update.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}