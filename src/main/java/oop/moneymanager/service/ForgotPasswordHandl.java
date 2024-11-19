package oop.moneymanager.service;


import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.util.UUID;
public class ForgotPasswordHandl {

    public static ForgotPasswordHandl getInstance(){
        return new ForgotPasswordHandl();
    }
    public static String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("[^a-zA-Z]", "").substring(0, 8);
    }
    public static boolean confirmPasswordResetRequest(String userName, String email) {
        try {
            if (userName.isEmpty() || email.isEmpty()) return false;
            String newPassWord = generateRandomPassword();
            UserModel user = new UserModel(userName,email, newPassWord, UserDao.getInstance().selectByUserName(userName).getPhone());
            int count;
            count = UserDao.getInstance().update(user);
            return count > 0 && EmailHandler.sendMail(email, newPassWord, userName );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkSendMail(String userName, String email){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (userName.isEmpty() || email.isEmpty()) return false;
        UserModel user = UserDao.getInstance().selectByUserName(userName);
        return user != null && email.matches(EMAIL_REGEX);
    }
}