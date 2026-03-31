package dao;


import java.sql.Connection;
import java.sql.DriverManager;

    public class DBConnection {
        public static Connection getConnection() {
            Connection connection = null;
            try {
                String url = "jdbc:mysql://localhost:3306/car_management";
                String user = "root";
                String password = "Prince@28";
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return connection;
        }
    }

