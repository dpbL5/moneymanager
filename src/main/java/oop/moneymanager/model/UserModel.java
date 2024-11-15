package oop.moneymanager.model;

public class UserModel {
    private String name;
    private String email;
    private String passWord;
    private String phone;

    public UserModel( String userName, String email, String passWord, String phone) {
        this.name = userName;
        this.email = email;
        this.passWord = passWord;
        this.phone = phone;

    }
    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.join(" ",name,email, passWord, phone);
    }
}
