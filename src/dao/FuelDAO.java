package dao;



import model.Fuel;

import java.sql.*;
import java.util.ArrayList;

    public class FuelDAO {

        public void addFuel(Fuel fuel) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO fuel (car_id, date, litres, cost) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, fuel.getCarId());
                pst.setDate(2, new java.sql.Date(fuel.getDate().getTime()));
                pst.setDouble(3, fuel.getLitres());
                pst.setDouble(4, fuel.getCost());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateFuel(Fuel fuel) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "UPDATE fuel SET car_id=?, date=?, litres=?, cost=? WHERE fuel_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, fuel.getCarId());
                pst.setDate(2, new java.sql.Date(fuel.getDate().getTime()));
                pst.setDouble(3, fuel.getLitres());
                pst.setDouble(4, fuel.getCost());
                pst.setInt(5, fuel.getFuelId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void deleteFuel(int id) {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM fuel WHERE fuel_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Fuel> getFuels() {
            ArrayList<Fuel> list = new ArrayList<>();
            try (Connection con = DBConnection.getConnection()) {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fuel");
                while (rs.next()) {
                    list.add(new Fuel(
                            rs.getInt("fuel_id"),
                            rs.getInt("car_id"),
                            rs.getDate("date"),
                            rs.getDouble("litres"),
                            rs.getDouble("cost")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

