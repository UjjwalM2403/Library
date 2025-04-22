package EntityPackage;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private String publication;
	private double price;
	private String genre;
	private int quantity;
//    private double rating;

	public Book(String title, String author, String isbn, String publication, double price, String genre, int quantity,
			double rating) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publication = publication;
		this.price = price;
		this.genre = genre;
		this.quantity = quantity;
//        this.rating = rating;
	}

	public void displayBook() {
		System.out.println(title + " by " + author);
	}

	public void displayDetails() {
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("ISBN: " + isbn);
		System.out.println("Publication: " + publication);
		System.out.println("Price: ₹" + price);
		System.out.println("Genre: " + genre);
		System.out.println("Quantity: " + quantity);
//        System.out.println("Rating: " + rating);
	}

	public String getStatus() {
		return quantity > 0 ? "Available" : "Out of Stock";
	}

	public String getTitle() {
		return title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void decreaseQuantity() {
		if (quantity > 0)
			quantity--;
	}

	public void increaseQuantity() {
		quantity++;
	}

	public String getGenre() {
// TODO Auto-generated method stub
		return this.genre;
	}
}
