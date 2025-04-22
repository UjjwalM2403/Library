package EntityPackage;
import java.util.*;
public class User {
	private String name;
	private int id;
	private int age;
	private String contact;
	private String address;
	private List<String> borrowedBooks = new ArrayList<>();

	public User(String name, int id, int age, String contact, String address) {
		this.name = name;
		this.id = id;
		this.age = age;
		this.contact = contact;
		this.address = address;
	}

	public void chooseBook(String bookTitle) {
		borrowedBooks.add(bookTitle);
		System.out.println(name + " borrowed: " + bookTitle);
	}

	public void returnBook(String bookTitle) {
		borrowedBooks.remove(bookTitle);
		System.out.println(name + " returned: " + bookTitle);
	}

//    public void giveFeedback(String feedback) {
//        System.out.println("Feedback from " + name + ": " + feedback);
//    }

	public void viewBorrowedBooks() {
		System.out.println(name + "'s Borrowed Books: " + borrowedBooks);
	}

	public void payFine(double amount) {
		System.out.println(name + " paid fine: ₹" + amount);
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}
}
