package ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Car Management System Login");
        setSize(400, 300);
        setLocationRelativeTo(null); // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background panel
        JPanel background = new JPanel();
        background.setBackground(new Color(45, 62, 80)); // dark bluish color
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Title label
        JLabel titleLabel = new JLabel("Login to Car System");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        background.add(titleLabel);
        background.add(Box.createVerticalStrut(30)); // space

        // Username label and field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        background.add(userLabel);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        usernameField.setBackground(Color.LIGHT_GRAY);
        usernameField.setForeground(Color.BLACK);
        background.add(usernameField);
        background.add(Box.createVerticalStrut(15));

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        background.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setForeground(Color.BLACK);
        background.add(passwordField);
        background.add(Box.createVerticalStrut(25));

        // Login button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(52, 152, 219)); // nice blue
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        background.add(loginButton);

        // Add background panel to frame
        add(background);

        // Action listener for login
        loginButton.addActionListener(e -> login());
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // TEMP: simple test login
        if (username.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new MainFrame().setVisible(true); // open your main UI
            dispose(); // close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }

    // TEST
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}