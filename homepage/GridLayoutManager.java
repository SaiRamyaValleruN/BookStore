package bookstore.homepage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import bookstore.bookDetails.BookDetails;
import bookstore.cart.Cart;
import bookstore.databaseConnector.BookDatabaseConnector;

public class GridLayoutManager {

    public static void setupGridLayout(JFrame frame, Cart cart) {
        frame.setLayout(new BorderLayout(10, 10));

        // Improved Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(new EmptyBorder(20, 15, 20, 15));
        JLabel titleLabel = new JLabel("Discover Our New Bookstore!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0x005792));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Grid Panel Setup
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        gridPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        List<BookDetails> books = BookDatabaseConnector.fetchAllBooks();
        books.forEach(book -> {
            GridCellPanel panel = new GridCellPanel(book, cart);
            panel.setPreferredSize(new Dimension(250, 300));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                }
            });
            gridPanel.add(panel);
        });


        if (books.isEmpty()) {
            gridPanel.add(new JLabel("<html><i>No books available at the moment.</i></html>"));
        }

        frame.add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        frame.setSize(960, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
