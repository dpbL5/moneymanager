package oop.moneymanager.service;

import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.sql.SQLException;

public class RegisterHandle {
    public static RegisterHandle getInstance(){
        return new RegisterHandle();
    }
    public int isRegister(String username,String email, String password, String confirmpassword) throws SQLException {
        try {
            UserModel userCheck = null;
            System.out.println(username + " " + password);
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
}
