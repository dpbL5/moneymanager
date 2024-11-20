package oop.moneymanager.service;

import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.sql.SQLException;

public class RegisterHandle {
    private static UserModel user;
    public static RegisterHandle getInstance(){
        return new RegisterHandle();
    }
    public int isRegister(String username,String email, String password, String confirmpassword) throws SQLException {
        try {
            UserModel userCheck = null;
            System.out.println(username + " " + password);
            if(isLowercaseLettersOnly(username)==false){
                return 0;
            }
            userCheck = UserDao.getInstance().selectByUserName(username);
            if (userCheck != null) {
                return 2;
            }
            if (password.equals(confirmpassword) && !password.isEmpty() && !username.isEmpty()) {
                UserModel user = new UserModel(username, email, password, "000000000");
                UserDao.getInstance().insert(user);
                return 1;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public boolean isLowercaseLettersOnly(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        // Sử dụng biểu thức chính quy để kiểm tra
        return input.matches("[a-z0-9]+");
    }
}
