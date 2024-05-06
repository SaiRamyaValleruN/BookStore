package bookstore.bookDetails;

import javax.swing.JOptionPane;
import bookstore.databaseConnector.BookDatabaseConnector;
import java.sql.SQLException;

public class BookSearchHandler {
    public static void handleSearch() throws SQLException {
        String searchQuery = JOptionPane.showInputDialog(null, "Enter ISBN number, author, or book title:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            BookDetails book = BookDatabaseConnector.fetchBookByISBN(searchQuery);
            if (book != null) {

                String bookInfo = String.format(
                        "<html><b>Title:</b> %s<br><b>Author:</b> %s<br><b>ISBN:</b> %s<br>" +
                                "<b>Publisher:</b> %s<br><b>Publication Year:</b> %d<br><b>Edition:</b> %d<br>" +
                                "<b>Price:</b> $%.2f<br><b>Availability:</b> %s</html>",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPublisher(),
                        book.getPublicationYear(),
                        book.getEdition(),
                        book.getPrice(),
                        book.isAvailability() ? "Yes" : "No"
                       // book.getDescription()
                );
                JOptionPane.showMessageDialog(null, bookInfo, "Book Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
