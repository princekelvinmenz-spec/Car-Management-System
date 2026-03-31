package dao;

import java.sql.*;

    public class UserDAO {

        public boolean login(String username, String password) {
            boolean isValid = false;

            try {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    isValid = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return isValid;
        }
    }

