package ui;

import model.Car;
import model.Service;
import model.Fuel;
import dao.CarDAO;
import dao.ServiceDAO;
import dao.FuelDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrame extends JFrame {

    // ------------------- CAR -------------------
    JTextField carIdField, brandField, modelField, yearField, priceField;
    JComboBox<String> statusBox;
    JTable carTable;
    DefaultTableModel carTableModel;
    CarDAO carDAO = new CarDAO();

    // ------------------- SERVICE -------------------
    JTextField serviceIdField, serviceCarIdField, serviceDescField;
    JComboBox<String> serviceStatusBox;
    JFormattedTextField serviceDateField;
    JTable serviceTable;
    DefaultTableModel serviceTableModel;
    ServiceDAO serviceDAO = new ServiceDAO();

    // ------------------- FUEL -------------------
    JTextField fuelIdField, fuelCarIdField, litresField, costField;
    JFormattedTextField fuelDateField;
    JTable fuelTable;
    DefaultTableModel fuelTableModel;
    FuelDAO fuelDAO = new FuelDAO();

    // ------------------- DASHBOARD -------------------
    JLabel totalCarsLabel, pendingServicesLabel, totalFuelLabel;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MainFrame() {
        setTitle("Car Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // ------------------- CAR TAB -------------------
        JPanel carPanel = new JPanel(new BorderLayout());
        JPanel carInputPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        carIdField = new JTextField();
        carIdField.setEditable(false);
        brandField = new JTextField();
        modelField = new JTextField();
        yearField = new JTextField();
        priceField = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Available", "Sold"});

        JButton addCarBtn = new JButton("Add");
        JButton updateCarBtn = new JButton("Update");
        JButton deleteCarBtn = new JButton("Delete");

        carInputPanel.add(new JLabel("ID"));
        carInputPanel.add(carIdField);
        carInputPanel.add(new JLabel("Brand"));
        carInputPanel.add(brandField);
        carInputPanel.add(new JLabel("Model"));
        carInputPanel.add(modelField);
        carInputPanel.add(new JLabel("Year"));
        carInputPanel.add(yearField);
        carInputPanel.add(new JLabel("Price"));
        carInputPanel.add(priceField);
        carInputPanel.add(new JLabel("Status"));
        carInputPanel.add(statusBox);
        carInputPanel.add(addCarBtn);
        carInputPanel.add(updateCarBtn);
        carInputPanel.add(deleteCarBtn);

        carPanel.add(carInputPanel, BorderLayout.NORTH);

        carTableModel = new DefaultTableModel(new String[]{"ID", "Brand", "Model", "Year", "Price", "Status"}, 0);
        carTable = new JTable(carTableModel);
        carPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);

        tabs.addTab("Cars", carPanel);

        // ------------------- SERVICE TAB -------------------
        JPanel servicePanel = new JPanel(new BorderLayout());
        JPanel serviceInputPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        serviceIdField = new JTextField();
        serviceIdField.setEditable(false);
        serviceCarIdField = new JTextField();
        serviceDescField = new JTextField();
        serviceDateField = new JFormattedTextField(sdf);
        serviceStatusBox = new JComboBox<>(new String[]{"Pending", "Completed"});

        JButton addServiceBtn = new JButton("Add");
        JButton updateServiceBtn = new JButton("Update");
        JButton deleteServiceBtn = new JButton("Delete");

        serviceInputPanel.add(new JLabel("ID"));
        serviceInputPanel.add(serviceIdField);
        serviceInputPanel.add(new JLabel("Car ID"));
        serviceInputPanel.add(serviceCarIdField);
        serviceInputPanel.add(new JLabel("Date (yyyy-MM-dd)"));
        serviceInputPanel.add(serviceDateField);
        serviceInputPanel.add(new JLabel("Description"));
        serviceInputPanel.add(serviceDescField);
        serviceInputPanel.add(new JLabel("Status"));
        serviceInputPanel.add(serviceStatusBox);
        serviceInputPanel.add(addServiceBtn);
        serviceInputPanel.add(updateServiceBtn);
        serviceInputPanel.add(deleteServiceBtn);

        servicePanel.add(serviceInputPanel, BorderLayout.NORTH);

        serviceTableModel = new DefaultTableModel(new String[]{"ID", "Car ID", "Date", "Description", "Status"}, 0);
        serviceTable = new JTable(serviceTableModel);
        servicePanel.add(new JScrollPane(serviceTable), BorderLayout.CENTER);

        tabs.addTab("Services", servicePanel);

        // ------------------- FUEL TAB -------------------
        JPanel fuelPanel = new JPanel(new BorderLayout());
        JPanel fuelInputPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        fuelIdField = new JTextField();
        fuelIdField.setEditable(false);
        fuelCarIdField = new JTextField();
        litresField = new JTextField();
        costField = new JTextField();
        fuelDateField = new JFormattedTextField(sdf);

        JButton addFuelBtn = new JButton("Add");
        JButton updateFuelBtn = new JButton("Update");
        JButton deleteFuelBtn = new JButton("Delete");

        fuelInputPanel.add(new JLabel("ID"));
        fuelInputPanel.add(fuelIdField);
        fuelInputPanel.add(new JLabel("Car ID"));
        fuelInputPanel.add(fuelCarIdField);
        fuelInputPanel.add(new JLabel("Date (yyyy-MM-dd)"));
        fuelInputPanel.add(fuelDateField);
        fuelInputPanel.add(new JLabel("Litres"));
        fuelInputPanel.add(litresField);
        fuelInputPanel.add(new JLabel("Cost"));
        fuelInputPanel.add(costField);
        fuelInputPanel.add(addFuelBtn);
        fuelInputPanel.add(updateFuelBtn);
        fuelInputPanel.add(deleteFuelBtn);

        fuelPanel.add(fuelInputPanel, BorderLayout.NORTH);

        fuelTableModel = new DefaultTableModel(new String[]{"ID", "Car ID", "Date", "Litres", "Cost"}, 0);
        fuelTable = new JTable(fuelTableModel);
        fuelPanel.add(new JScrollPane(fuelTable), BorderLayout.CENTER);

        tabs.addTab("Fuel", fuelPanel);

        // ------------------- DASHBOARD TAB -------------------
        JPanel dashboardPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        totalCarsLabel = new JLabel();
        pendingServicesLabel = new JLabel();
        totalFuelLabel = new JLabel();

        JButton refreshDashboard = new JButton("Refresh Dashboard");
        dashboardPanel.add(totalCarsLabel);
        dashboardPanel.add(pendingServicesLabel);
        dashboardPanel.add(totalFuelLabel);
        dashboardPanel.add(refreshDashboard);

        tabs.addTab("Dashboard", dashboardPanel);

        add(tabs);

        // ------------------- ACTIONS -------------------

        // ---------- CAR ACTIONS ----------
        addCarBtn.addActionListener(e -> {
            String yearStr = yearField.getText().trim();
            String priceStr = priceField.getText().trim();
            if(yearStr.isEmpty()
            || priceStr.isEmpty()|| brandField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields before adding.");
                return;
            }

            try {
                int year = Integer.parseInt(yearField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                carDAO.addCar(new Car(0, brandField.getText(), modelField.getText(),year,price,

                        statusBox.getSelectedItem().toString()));
                loadCars();

                JOptionPane.showMessageDialog(this, "Car added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year and Price must be valid numbers.");

            }catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error adding car: " + ex.getMessage());
                ex.printStackTrace();
            }

        });

        updateCarBtn.addActionListener(e -> {
            if (carIdField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a car first!");
                return;
            }
            try {
                carDAO.updateCar(new Car(Integer.parseInt(carIdField.getText()), brandField.getText(),
                        modelField.getText(), Integer.parseInt(yearField.getText()),
                        Double.parseDouble(priceField.getText()), statusBox.getSelectedItem().toString()));
                loadCars();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating car");
                ex.printStackTrace();
            }
        });

        deleteCarBtn.addActionListener(e -> {
            if (carIdField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a car first!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this car?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    carDAO.deleteCar(Integer.parseInt(carIdField.getText()));
                    loadCars();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting car");
                    ex.printStackTrace();
                }
            }
        });

        carTable.getSelectionModel().addListSelectionListener(e -> {
            int row = carTable.getSelectedRow();
            if (row >= 0) {
                carIdField.setText(carTableModel.getValueAt(row, 0).toString());
                brandField.setText(carTableModel.getValueAt(row, 1).toString());
                modelField.setText(carTableModel.getValueAt(row, 2).toString());
                yearField.setText(carTableModel.getValueAt(row, 3).toString());
                priceField.setText(carTableModel.getValueAt(row, 4).toString());
                statusBox.setSelectedItem(carTableModel.getValueAt(row, 5));
            }
        });

        // ---------- SERVICE ACTIONS ----------
        addServiceBtn.addActionListener(e -> {

            try {
                serviceDAO.addService(new Service(0,
                        Integer.parseInt(serviceCarIdField.getText()),
                        sdf.parse(serviceDateField.getText()),
                        serviceDescField.getText(),
                        serviceStatusBox.getSelectedItem().toString()));
                loadServices();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding service");
                ex.printStackTrace();
            }
        });

        updateServiceBtn.addActionListener(e -> {

            try {
                serviceDAO.updateService(new Service(Integer.parseInt(serviceIdField.getText()),
                        Integer.parseInt(serviceCarIdField.getText()),
                        sdf.parse(serviceDateField.getText()),
                        serviceDescField.getText(),
                        serviceStatusBox.getSelectedItem().toString()));
                loadServices();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating service");
                ex.printStackTrace();
            }
        });

        deleteServiceBtn.addActionListener(e -> {
            try {
                serviceDAO.deleteService(Integer.parseInt(serviceIdField.getText()));
                loadServices();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting service");
                ex.printStackTrace();
            }
        });

        serviceTable.getSelectionModel().addListSelectionListener(e -> {
            int row = serviceTable.getSelectedRow();
            if (row >= 0) {
                serviceIdField.setText(serviceTableModel.getValueAt(row, 0).toString());
                serviceCarIdField.setText(serviceTableModel.getValueAt(row, 1).toString());
                serviceDateField.setText(serviceTableModel.getValueAt(row, 2).toString());
                serviceDescField.setText(serviceTableModel.getValueAt(row, 3).toString());
                serviceStatusBox.setSelectedItem(serviceTableModel.getValueAt(row, 4));
            }
        });

        // ---------- FUEL ACTIONS ----------
        addFuelBtn.addActionListener(e -> {
            try {
                fuelDAO.addFuel(new Fuel(0,
                        Integer.parseInt(fuelCarIdField.getText()),
                        sdf.parse(fuelDateField.getText()),
                        Double.parseDouble(litresField.getText()),
                        Double.parseDouble(costField.getText())));
                loadFuels();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding fuel");
                ex.printStackTrace();
            }
        });

        updateFuelBtn.addActionListener(e -> {
            try {
                fuelDAO.updateFuel(new Fuel(Integer.parseInt(fuelIdField.getText()),
                        Integer.parseInt(fuelCarIdField.getText()),
                        sdf.parse(fuelDateField.getText()),
                        Double.parseDouble(litresField.getText()),
                        Double.parseDouble(costField.getText())));
                loadFuels();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating fuel");
                ex.printStackTrace();
            }
        });

        deleteFuelBtn.addActionListener(e -> {
            try {
                fuelDAO.deleteFuel(Integer.parseInt(fuelIdField.getText()));
                loadFuels();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting fuel");
                ex.printStackTrace();
            }
        });

        fuelTable.getSelectionModel().addListSelectionListener(e -> {
            int row = fuelTable.getSelectedRow();
            if (row >= 0) {
                fuelIdField.setText(fuelTableModel.getValueAt(row, 0).toString());
                fuelCarIdField.setText(fuelTableModel.getValueAt(row, 1).toString());
                fuelDateField.setText(fuelTableModel.getValueAt(row, 2).toString());
                litresField.setText(fuelTableModel.getValueAt(row, 3).toString());
                costField.setText(fuelTableModel.getValueAt(row, 4).toString());
            }
        });

        // ---------- DASHBOARD ----------
        refreshDashboard.addActionListener(e -> loadDashboard());

        // ------------------- INITIAL LOAD -------------------
        loadCars();
        loadServices();
        loadFuels();
        loadDashboard();

        setVisible(true);
    }

    // ------------------- LOAD METHODS -------------------
    private void loadCars() {
        carTableModel.setRowCount(0);
        for (Car c : carDAO.getCars()) {
            carTableModel.addRow(new Object[]{ c.getCarId(),c.getBrand(), c.getModel(), c.getYear(), c.getPrice(), c.getStatus()});
        }
    }

    private void loadServices() {
        serviceTableModel.setRowCount(0);
        for (Service s : serviceDAO.getServices()) {
            serviceTableModel.addRow(new Object[]{s.getServiceId(), s.getCarId(), sdf.format(s.getServiceDate()), s.getDescription(), s.getStatus()});
        }
    }

    private void loadFuels() {
        fuelTableModel.setRowCount(0);
        for (Fuel f : fuelDAO.getFuels()) {
            fuelTableModel.addRow(new Object[]{f.getFuelId(), f.getCarId(), sdf.format(f.getDate()), f.getLitres(), f.getCost()});
        }
    }

    private void loadDashboard() {
        totalCarsLabel.setText("Total Cars: " + carDAO.getCars().size());
        long pending = serviceDAO.getServices().stream().filter(s -> s.getStatus().equalsIgnoreCase("Pending")).count();
        pendingServicesLabel.setText("Pending Services: " + pending);
        totalFuelLabel.setText("Fuel Logs: " + fuelDAO.getFuels().size());
    }

    }
