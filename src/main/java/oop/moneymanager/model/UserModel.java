package oop.moneymanager.model;

public class UserModel {
    private String user_id;
    private String name;
    private String email;
    private String passWord;
    private String phone;
    private String money;

    public UserModel(String user_id, String userName, String email, String passWord, String phone, String budgets) {
        this.user_id = user_id;
        this.name = userName;
        this.email = email;
        this.passWord = passWord;
        this.phone = phone;
        this.money = budgets;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getBudgets() {
        return money;
    }

    public void setBudgets(String budgets) {
        this.money = budgets;
    }

    @Override
    public String toString() {
        return String.join(" ",user_id, name,email, passWord, phone, String.valueOf(money));
    }
}
