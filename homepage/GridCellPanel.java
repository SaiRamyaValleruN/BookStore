package bookstore.homepage;

import bookstore.bookDetails.BookDetails;
import bookstore.cart.Cart;

import javax.swing.*;
import java.awt.*;

public class GridCellPanel extends JPanel {
    private JLabel imageLabel;
    private JTextPane commentPane;
    private JButton addToCartButton;
    private Cart cart;
    private BookDetails book;

    public GridCellPanel(BookDetails book, Cart cart) {
        this.cart = cart;
        this.book = book;
        setLayout(new BorderLayout());
        String imagePath = book.getImagePath();

        ImageIcon imageIcon;
        if (imagePath != null && !imagePath.isEmpty()) {
            imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        } else {
            imageIcon = new ImageIcon(new ImageIcon("resources/images/imagenotfound.jpeg").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        }
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.WEST);

        commentPane = new JTextPane();
        commentPane.setContentType("text/html");
        commentPane.setEditable(false);
        String bookDetailsHtml = String.format(
                "<html><b>Title:</b> %s<br/>" +
                        "<b>Author:</b> %s<br/>" +
                        "<b>ISBN:</b> %s<br/>" +
                        "<b>Publisher:</b> %s<br/>" +
                        "<b>Publication Year:</b> %d<br/>" +
                        "<b>Edition:</b> %d<br/>" +
                        "<b>Price:</b> $%.2f<br/>" +
                        "<b>Available:</b> %s<br/>" +
                        "<b>Description:</b> %s</html>",
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getEdition(),
                book.getPrice(),
                book.isAvailability() ? "Yes" : "No",
                book.getDescription()
        );
        commentPane.setText(bookDetailsHtml);
        JScrollPane scrollPane = new JScrollPane(commentPane);
        add(scrollPane, BorderLayout.CENTER);

        // Button setup
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            this.cart.addItem(book);
            JOptionPane.showMessageDialog(this, "Added to book to cart!", "Cart", JOptionPane.INFORMATION_MESSAGE);
        });
        add(addToCartButton, BorderLayout.SOUTH);
    }
}
