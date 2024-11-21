package oop.moneymanager.service;

import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

public class LoginHandle {
    private UserModel user;
    public static LoginHandle getInstance(){
        return new LoginHandle();
    }
    public boolean isValidLogin(String username, String password) {
        try {
            System.out.println("Trying to login with username: " + username + " and password: " + password);
            user = UserDao.getInstance().selectByUserNamePassWord(username, password);
            return user != null && user.getUserName().equals(username) && user.getPassWord().equals(password);
        }catch (Exception e){
            // do user co the null
            e.printStackTrace();
            System.out.println("Error: " + e.toString());
            return false;
        }
    }
}
