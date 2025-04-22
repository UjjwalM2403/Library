package EntityPackage;

import repository.LibraryRepository;

public class Librarian {
	private String name;
	private String location;
	private String contact;

	public Librarian(String name, String location, String contact) {
		this.name = name;
		this.location = location;
		this.contact = contact;
	}

	public void addUser(User user) {
		LibraryRepository.users.add(user);
		System.out.println("User added: " + user.getName());
	}

	public void removeUser(User user) {
		LibraryRepository.users.remove(user);
		System.out.println("User removed: " + user.getName());
	}

	public void addBook(Book book) {
		LibraryRepository.books.add(book);
		System.out.println("Book added: " + book.getTitle());
	}

	public void removeBook(Book book) {
		LibraryRepository.books.remove(book);
		System.out.println("Book removed: " + book.getTitle());
	}

	public void findBook(String title) {
		for (Book b : LibraryRepository.books) {
			if (b.getTitle().equalsIgnoreCase(title)) {
				b.displayDetails();
				return;
			}
		}
		System.out.println("Book not found.");
	}

	public void viewBorrowedBooks(User user) {
		user.viewBorrowedBooks();
	}

//    public void viewFines(User user) {
//
//        System.out.println("View fines is not implemented with actual amounts.");
//    }

	public void penalty(User user, double fine) {
		user.payFine(fine);
	}

//    public void verifyUser(User user) {
//        System.out.println("User verified: " + user.getName());
//    }

	public void filter(String genre) {
		for (Book book : LibraryRepository.books) {
			if (book.getStatus().equals("Available") && book.getGenre().equalsIgnoreCase(genre)) {
				book.displayBook();
				return;
			}
		}
		System.out.println("Genre not found");
	}

	public void issuedTo(String bookTitle) {
		for (User user : LibraryRepository.users) {
			if (user.toString().contains(bookTitle)) {
				System.out.println(bookTitle + " is issued to: " + user.getName());
				return;
			}
		}
		System.out.println("This book is not issued to any user");
	}

	public String getName() {
		return this.name;
	}
}
