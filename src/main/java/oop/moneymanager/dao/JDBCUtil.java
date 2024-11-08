package oop.moneymanager.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
<<<<<<< Updated upstream
    private static String url = "jdbc:mysql://localhost:3306/moneymanager?autoReconnect=true&useSSL=false";
    private static String userName = "root";
    private static String passWord = "shanks542";
=======
    private static String url = "jdbc:mysql://localhost:3306/StudentRecordsManager?autoReconnect=true&useSSL=false";
    private static String userName = "root";
    private static String passWord = "tumotden8";
>>>>>>> Stashed changes
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            System.out.println("Lỗi: Chưa kết nối cơ sở dữ liệu!!!");
            e.printStackTrace();
        }
        return con;
    }
}
