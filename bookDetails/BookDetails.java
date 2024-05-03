package bookstore.bookDetails;

public class BookDetails {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int publicationYear;
    private int edition;
    private double price;
    private boolean availability;
    
	public BookDetails(String title, String author, String isbn, String publisher, int publicationYear, int edition,
			double price, boolean availability) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.edition = edition;
		this.price = price;
		this.availability = availability;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\n" +
		           "Author: " + author + "\n" +
		           "ISBN: " + isbn + "\n" +
		           "Publisher: " + publisher + "\n" +
		           "Publication Year: " + publicationYear + "\n" +
		           "Edition: " + edition + "\n" +
		           "Price: $" + String.format("%.2f", price) + "\n" +
		           "Availability: " + availability;
	}

    
    
}
