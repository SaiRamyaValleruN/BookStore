package bookstore.checkout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutWindow extends JPanel {
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    public CheckoutWindow() {
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns

        // Add components
        add(new JLabel("Card Number:"));
        cardNumberField = new JTextField(20);
        add(cardNumberField);

        add(new JLabel("Expiry Date (MM/YY):"));
        expiryDateField = new JTextField(5);
        add(expiryDateField);

        add(new JLabel("CVV:"));
        cvvField = new JTextField(3);
        add(cvvField);

        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(new ProceedButtonListener());
        add(proceedButton);
    }

    private class ProceedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validate payment details
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();

            if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(CheckoutWindow.this,
                        "Please fill in all payment details.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Proceed to payment (you can implement your payment logic here)
                JOptionPane.showMessageDialog(CheckoutWindow.this,
                        "Payment successful! Thank you for your purchase.",
                        "Payment Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                // Close the dialog after successful payment
                SwingUtilities.getWindowAncestor(CheckoutWindow.this).dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog();
            dialog.setTitle("Checkout");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.getContentPane().add(new CheckoutWindow());
            dialog.pack();
            dialog.setLocationRelativeTo(null); // Center the dialog on screen
            dialog.setVisible(true);
        });
    }
}
