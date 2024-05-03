package bookstore.cart;

import java.util.HashMap;
import java.util.Map;

import bookstore.bookDetails.BookDetails;

public class Cart {
    private final Map<BookDetails, Integer> items = new HashMap<>();

    public void addItem(BookDetails book) {
        items.merge(book, 1, Integer::sum); // Increment quantity for the book, or set to 1 if not present
    }

    public void removeItem(BookDetails book) {
        items.computeIfPresent(book, (k, v) -> v > 1 ? v - 1 : null); // Decrement or remove the book entirely
    }

    public void clearCart() {
        items.clear();
    }

    public double calculateTotalPrice() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public String getCartDetails() {
        StringBuilder sb = new StringBuilder("Cart Contents:\n");
        items.forEach((book, quantity) -> sb.append(book.getTitle()).append(" - Quantity: ").append(quantity).append("\n"));
        sb.append("Total Price: $").append(String.format("%.2f", calculateTotalPrice()));
        return sb.toString();
    }
}
