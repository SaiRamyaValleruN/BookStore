package bookstore;

import javax.swing.SwingUtilities;

import bookstore.loginWindow.LoginWindow;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}