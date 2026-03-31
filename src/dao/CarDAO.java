package dao;

import model.Car;
import java.sql.*;
import java.util.ArrayList;

public class CarDAO {

    // Add a new car
    public void addCar(Car car) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO car (brand, model, year, price, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, car.getBrand());
            pst.setString(2, car.getModel());
            pst.setInt(3, car.getYear());
            pst.setDouble(4, car.getPrice());
            pst.setString(5, car.getStatus());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Update existing car
    public void updateCar(Car car) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE car SET brand=?, model=?, year=?, price=?, status=? WHERE car_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, car.getBrand());
            pst.setString(2, car.getModel());
            pst.setInt(3, car.getYear());
            pst.setDouble(4, car.getPrice());
            pst.setString(5, car.getStatus());
            pst.setInt(6, car.getCarId());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Delete a car by ID
    public void deleteCar(int car_id) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM car WHERE car_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, car_id);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Get all cars
    public ArrayList<Car> getCars() {
        ArrayList<Car> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM car");
            while (rs.next()) {
                list.add(new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("status")
                ));
            }
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}