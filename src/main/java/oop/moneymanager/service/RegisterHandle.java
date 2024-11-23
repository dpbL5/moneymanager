package oop.moneymanager.service;

import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.sql.SQLException;

public class RegisterHandle {
    private static UserModel user;
    public static RegisterHandle getInstance(){
        return new RegisterHandle();
    }
    public int isRegister(String username,String email,String phone, String password, String confirmpassword) throws SQLException {
        try {
            UserModel userCheck = null;
            System.out.println(username + " " + password);
            boolean checkemail = UserDao.getInstance().selectByEmail(email);
            if(checkemail==true) {
                return 4;
            }
            boolean checkphone = UserDao.getInstance().selectByPhone(phone);
            if(checkphone==true){
                return 3;
            }
            userCheck = UserDao.getInstance().selectByUserName(username);
            if (userCheck != null) {
                return 2;
            }
            if (password.equals(confirmpassword) && !password.isEmpty() && !username.isEmpty()) {
                UserModel user = new UserModel(username, email, password, phone);
                UserDao.getInstance().insert(user);
                return 1;
            }
//            if(isLowercaseLettersOnly(username)==false){
//                return 0;
//            }
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
        return input.matches("[a-z0-9]+");
    }
}
