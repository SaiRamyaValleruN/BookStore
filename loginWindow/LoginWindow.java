package bookstore.loginWindow;

import javax.swing.*;

import bookstore.RibbonLayout.RibbonLayout;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginWindow extends JFrame implements ActionListener {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create panel to hold components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Book Store");
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 18f)); // Make it bold and increase font size
        panel.add(welcomeLabel, gbc);

        // Add user id field
        gbc.gridy++;
        JLabel userIdLabel = new JLabel("User ID:");
        panel.add(userIdLabel, gbc);
        gbc.gridy++;
        userIdField = new JTextField(15);
        panel.add(userIdField, gbc);

        // Add password field
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Add login button
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton, gbc);

        // Add image label at the right corner
        gbc.gridx = 2; // Right corner
        gbc.gridy = 0; // Align with welcome label
        gbc.gridheight = GridBagConstraints.REMAINDER; // Span all rows
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the end
        gbc.insets = new Insets(10, 0, 10, 10); // Adjust padding

        ImageIcon imageIcon = resizeImage("resources/images/BookStore.jpg", 450, 450); // Resize the image to 150x150 pixels
        JLabel imageLabel = new JLabel(imageIcon);
        panel.add(imageLabel, gbc);

        // Set frame properties
        add(panel);
        pack(); // Pack components tightly
        setLocationRelativeTo(null); // Center the window on screen
        setVisible(true);
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());
            if (validateUser(userId, password)) {
                // If user credentials are valid, close the login window and open the main application window
                dispose(); // Close the login window
                SwingUtilities.invokeLater(RibbonLayout::new);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateUser(String userId, String password) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String user = "root";
        String pass = "password";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement()) {
            // Query the database for the user with the given userId and password
            String query = "SELECT * FROM users WHERE userId = '" + userId + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            // If the resultSet has any rows, it means the user exists with the given credentials
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}