package oop.moneymanager.dao;

import oop.moneymanager.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao implements DaoInterface<UserModel> {
    public static UserDao getInstance() {
        return new UserDao();
    }
    @Override
    public int insert(UserModel user) throws SQLException {
        String url = "INSERT INTO user (user_id,name,email, password,phone,money)VALUES(?, ?, ?, ?,?,?)";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,user.getUser_id());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassWord());
            stmt.setString(5,user.getPhone());
            stmt.setString(6,user.getBudgets());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(UserModel user) {
        String url = "UPDATE user SET password = ? WHERE name = ? and email = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, user.getPassWord());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getEmail());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(UserModel userModel) {
        String url = "DELETE FROM user WHERE name = ? and email = ?";
        try (Connection con = JDBCUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, userModel.getUserName());
            stmt.setString(2, userModel.getEmail());
            int row = stmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<UserModel> selectAll()  {
        String url = "SELECT * FROM user";
        ArrayList<UserModel> users = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                UserModel x = new UserModel(rs.getString("user_id"),rs.getString("name"),rs.getString("email"),
                        rs.getString("password"),rs.getString("phone"),rs.getString("money"));
                users.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public ArrayList<UserModel> selectByCondition(String condition) {
        return null;
    }

    @Override
    public UserModel selectByID(String ID) {
        return null;
    }
    public UserModel selectByUserName(String userName) {
        String sql = "SELECT * FROM user WHERE name = ?";
        UserModel user = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,userName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user = new UserModel(rs.getString("user_id"),rs.getString("name"),rs.getString("email"),
                        rs.getString("password"),rs.getString("phone"),rs.getString("money"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserModel selectByUserNamePassWord(String userName, String passWord) {
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        UserModel user = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,userName);
            stmt.setString(2,passWord);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
            user = new UserModel(rs.getString("user_id"),rs.getString("name"),rs.getString("email"),
            rs.getString("password"),rs.getString("phone"),rs.getString("money"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}