package bookstore.admin;

import bookstore.bookDetails.BookDetails;
import bookstore.loginWindow.LoginWindow;
import bookstore.databaseConnector.BookDatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class addBook extends JFrame {
    private JTextField titleField, authorField, isbnField, publisherField, yearField, editionField, priceField, descriptionField;
    private JCheckBox availabilityBox;
    private JButton submitButton, chooseImageButton, logoutButton;
    private JLabel imageLabel;
    private File selectedFile;

    public addBook() {
        setTitle("Add New Book");
        setLayout(new GridLayout(11, 2, 5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        addFormFields();

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginWindow().setVisible(true);
        });
        add(logoutButton);

        setVisible(true);
    }

    private void addFormFields() {

        add(new JLabel("Title:"));
        titleField = new JTextField(20);
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField(20);
        add(authorField);

        add(new JLabel("ISBN:"));
        isbnField = new JTextField(20);
        add(isbnField);

        add(new JLabel("Publisher:"));
        publisherField = new JTextField(20);
        add(publisherField);

        add(new JLabel("Publication Year:"));
        yearField = new JTextField(20);
        add(yearField);

        add(new JLabel("Edition:"));
        editionField = new JTextField(20);
        add(editionField);

        add(new JLabel("Price:"));
        priceField = new JTextField(20);
        add(priceField);

        add(new JLabel("Available:"));
        availabilityBox = new JCheckBox();
        add(availabilityBox);

        add(new JLabel("Description:"));
        descriptionField = new JTextField(200);
        add(descriptionField);

        imageLabel = new JLabel("No Image Selected");
        add(imageLabel);

        chooseImageButton = new JButton("Choose Image");
        chooseImageButton.addActionListener(e -> chooseImage());
        add(chooseImageButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitBookDetails());
        add(submitButton);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            imageLabel.setText(selectedFile.getName());
        } else {
            selectedFile = null; // Explicitly set to null if no file is chosen
            imageLabel.setText("No Image Selected");
        }
    }


    private void submitBookDetails() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            int edition = Integer.parseInt(editionField.getText());
            double price = Double.parseDouble(priceField.getText());
            boolean availability = availabilityBox.isSelected();
            String imagePath = (selectedFile != null) ? selectedFile.getPath() : "";
            String description = descriptionField.getText();

            BookDetails newBook = new BookDetails(title, author, isbn, publisher, year, edition, price, availability, imagePath, description);
            BookDatabaseConnector.addBook(newBook);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for year, edition, and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to add book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new addBook().setVisible(true));
    }
}
