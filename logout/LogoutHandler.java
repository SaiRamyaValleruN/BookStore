package bookstore.logout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bookstore.loginWindow.LoginWindow;

public class LogoutHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Perform logout actions here
        // For example, dispose the current window and open the login window
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
        frame.dispose();
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
