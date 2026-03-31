package dao;



import model.Service;

import java.sql.*;
import java.util.ArrayList;

    public class ServiceDAO {

        public void addService(Service service) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO service (car_id, service_date, description, status) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, service.getCarId());
                pst.setDate(2, new java.sql.Date(service.getServiceDate().getTime()));
                pst.setString(3, service.getDescription());
                pst.setString(4, service.getStatus());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateService(Service service) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "UPDATE service SET car_id=?, service_date=?, description=?, status=? WHERE service_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, service.getCarId());
                pst.setDate(2, new java.sql.Date(service.getServiceDate().getTime()));
                pst.setString(3, service.getDescription());
                pst.setString(4, service.getStatus());
                pst.setInt(5, service.getServiceId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void deleteService(int id) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM service WHERE service_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Service> getServices() {
            ArrayList<Service> list = new ArrayList<>();
            try (Connection con = DBConnection.getConnection()) {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM service");
                while (rs.next()) {
                    list.add(new Service(
                            rs.getInt("service_id"),
                            rs.getInt("car_id"),
                            rs.getDate("service_date"),
                            rs.getString("description"),
                            rs.getString("status")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

