package database;
import java.sql.*;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/internship_portal",
                    "root", "rkvk18003"
                );
                System.out.println("✅ Database Connected Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
