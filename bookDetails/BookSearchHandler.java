package bookstoreRound2.bookDetails;

import javax.swing.JOptionPane;

import bookstoreRound2.databaseConnector.BookDatabaseConnector;

public class BookSearchHandler {
    public static void handleSearch() {
        String searchQuery = JOptionPane.showInputDialog(null, "Enter ISBN number, author, or book title:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            BookDetails book = BookDatabaseConnector.searchBook(searchQuery);
			if (book != null) {
			    JOptionPane.showMessageDialog(null, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    }
}
