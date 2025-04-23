package service;

import EntityPackage.Book;
import EntityPackage.Librarian;
import EntityPackage.User;
import repository.LibraryRepository;

import java.util.Scanner;

public class LibraryService {

    Scanner sc = new Scanner(System.in);
    public void userActions(User user) {
        int choice;
        do {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Borrowed Books");
//            System.out.println("4. Give Feedback");
            System.out.println("5. Pay Fine");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title to borrow: ");
                    String bookTitle = sc.nextLine();
                    user.chooseBook(bookTitle);
                }
                case 2 -> {
                    System.out.print("Enter book title to return: ");
                    String bookTitle = sc.nextLine();
                    user.returnBook(bookTitle);
                }
                case 3 -> user.viewBorrowedBooks();
//                case 4 -> {
//                    System.out.print("Enter your feedback: ");
//                    String feedback = sc.nextLine();
////                    user.giveFeedback(feedback);
//                }
                case 5 -> {
                    System.out.print("Enter fine amount: ");
                    double fine = sc.nextDouble();
                    user.payFine(fine);
                }
                case 0 -> System.out.println("Exiting user menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void librarianActions(Librarian librarian) {
        int choice;
        do {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Find Book");
            System.out.println("4. Filter by Genre");
            System.out.println("5. Add User");
            System.out.println("6. Remove User");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Publication: ");
                    String pub = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Rating: ");
                    double rating = sc.nextDouble();
                    sc.nextLine();

                    Book newBook = new Book(title, author, isbn, pub, price, genre, qty, rating);
                    librarian.addBook(newBook);
                }
                case 2 -> {
                    System.out.print("Enter book title to remove: ");
                    String title = sc.nextLine();
                    Book bookToRemove = LibraryRepository.books.stream()
                            .filter(b -> b.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .orElse(null);
                    if (bookToRemove != null) {
                        librarian.removeBook(bookToRemove);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter book title to find: ");
                    String title = sc.nextLine();
                    librarian.findBook(title);
                }
                case 4 -> {
                    System.out.print("Enter genre to filter: ");
                    String genre = sc.nextLine();
                    librarian.filter(genre);
                }
                case 5 -> {
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Address: ");
                    String address = sc.nextLine();
                    User newUser = new User(name, id, age, contact, address);
                    librarian.addUser(newUser);
                }
                case 6 -> {
                    System.out.print("Enter user ID to remove: ");
                    int id = sc.nextInt();
                    User userToRemove = LibraryRepository.users.stream()
                            .filter(u -> u.getId() == id)
                            .findFirst()
                            .orElse(null);
                    if (userToRemove != null) {
                        librarian.removeUser(userToRemove);
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 0 -> System.out.println("Exiting librarian menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
