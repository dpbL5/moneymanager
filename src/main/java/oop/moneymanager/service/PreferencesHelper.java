package oop.moneymanager.service;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.prefs.Preferences;

import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;

public class PreferencesHelper {

    private static final String PREFS_NODE = "myAppPrefs";  // Tên của node lưu trữ
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    
    private static TreeSet<String> savedCategory = new TreeSet<>();
    private static Preferences preferences = Preferences.userRoot().node(PREFS_NODE);  
    
    // Lưu thông tin danh mục
    public static TreeSet<String> getSavedCategory() {
        TransactionDao transactionDao = new TransactionDao();
        ArrayList<TransactionModel> list = transactionDao.selectByCondition("username = \"" + getUsername() + "\"");
        list.forEach(category -> {
            savedCategory.add(category.getCategory());
        });
        return savedCategory;
    }

    // Lưu thông tin đăng nhập
    public static void saveLoginInfo(String username, String password) {
        preferences.put(KEY_USERNAME, username);
        preferences.put(KEY_PASSWORD, password);
    }

    // Lấy tên người dùng
    public static String getUsername() {
        return preferences.get(KEY_USERNAME, null); // trả về null nếu không có giá trị
    }
    // Lấy mật khẩu
    public static String getPassword() {
        return preferences.get(KEY_PASSWORD, null);
    }

    // Xóa thông tin đăng nhập (khi đăng xuất)
    public static void clearLoginInfo() {
        preferences.remove(KEY_USERNAME);
        preferences.remove(KEY_PASSWORD);
    }
}
