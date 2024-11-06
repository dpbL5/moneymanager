package oop.moneymanager.Model;

public class UserModel {
    private String userName;
    private String email;
    private String passWord;
    private float budgets;

    public UserModel(String userName, String email, String passWord, float budgets) {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.budgets = budgets;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public float getBudgets() {
        return budgets;
    }

    public void setBudgets(float budgets) {
        this.budgets = budgets;
    }
    @Override
    public String toString() {
        return String.join(" ", userName,email, passWord, String.valueOf(budgets));
    }
}
