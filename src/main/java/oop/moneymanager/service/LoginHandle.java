package oop.moneymanager.service;

import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

public class LoginHandle {
    private static UserModel user;
    public static LoginHandle getInstance(){
        return new LoginHandle();
    }
    public boolean isValidLogin(String username, String password) {
        try {
            System.out.println(username + " " + password);
            user = UserDao.getInstance().selectByUserNamePassWord(username, password);
            System.out.println(user);
            if(user == null){
                System.out.println("fix loi di ma");
            }
            return user != null && user.getUserName().equals(username) && user.getPassWord().equals(password);
        }catch (Exception e){
            // do user co the null
            e.printStackTrace();
            System.out.println("Nhập thông tin không đúng !!!");
            return false;
        }
    }
}
