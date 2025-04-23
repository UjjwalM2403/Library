package service;

import EntityPackage.Book;
import EntityPackage.Librarian;
import EntityPackage.User;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import repository.LibraryRepository;

public class LibraryService {
    Scanner sc;

    public LibraryService() {
        this.sc = new Scanner(System.in);
    }
    private int parseIntSafe(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid integer input: '" + input + "'. Defaulting to 0.");
            return 0;
        }
    }
    
    private double parseDoubleSafe(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid decimal input: '" + input + "'. Defaulting to NaN.");
            return Double.NaN;
        }
    }
    

    public void userActions(User user) {
        
        int choice = -1;
        do {
            try {
                System.out.println("\n--- User Menu ---");
                System.out.println("1. Borrow Book");
                System.out.println("2. Return Book");
                System.out.println("3. View Borrowed Books");
                System.out.println("5. Pay Fine");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (choice) {
                    case 0 -> System.out.println("Exiting user menu...");
                    case 1 -> {
                        System.out.print("Enter book title to borrow: ");
                        String title = sc.nextLine();
                        user.chooseBook(title);
                    }
                    case 2 -> {
                        System.out.print("Enter book title to return: ");
                        String title = sc.nextLine();
                        user.returnBook(title);
                    }
                    case 3 -> user.viewBorrowedBooks();
                    case 5 -> {
                        System.out.print("Enter fine amount: ");
                        String fineInput = sc.nextLine();
                        double fine = parseDoubleSafe(fineInput);
                        user.payFine(fine);
                    }
                    default -> System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input. Please enter a number.");
                sc.nextLine(); // clear the invalid input
            } catch (Exception e) {
                System.out.println("⚠ An error occurred: " + e.getMessage());
            }
        } while (choice != 0);
    }

    public void librarianActions(Librarian librarian) {
        
        int choice = -1;
        do {
            try {
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
                sc.nextLine(); // Clear buffer

                switch (choice) {
                    case 0 -> System.out.println("Exiting librarian menu...");

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
                        int quantity = sc.nextInt();
                        System.out.print("Rating: ");
                        double rating = sc.nextDouble();
                        sc.nextLine();

                        Book book = new Book(title, author, isbn, pub, price, genre, quantity, rating);
                        librarian.addBook(book);
                    }

                    case 2 -> {
                        System.out.print("Enter book title to remove: ");
                        String title = sc.nextLine();
                        Optional<Book> bookOpt = LibraryRepository.books.stream()
                                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                                .findFirst();
                        if (bookOpt.isPresent()) {
                            librarian.removeBook(bookOpt.get());
                        } else {
                            System.out.println("⚠ Book not found.");
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
                        String idInput = sc.nextLine();
                        int id = parseIntSafe(idInput);
                    
                        System.out.print("Age: ");
                        String ageInput = sc.nextLine();
                        int age = parseIntSafe(ageInput);
                    
                        System.out.print("Contact: ");
                        String contact = sc.nextLine();
                    
                        System.out.print("Address: ");
                        String address = sc.nextLine();
                    
                        User user = new User(name, id, age, contact, address);
                        librarian.addUser(user);
                    }
                    

                    case 6 -> {
                        System.out.print("Enter user ID to remove: ");
                        int id = sc.nextInt();
                        Optional<User> userOpt = LibraryRepository.users.stream()
                                .filter(u -> u.getId() == id)
                                .findFirst();
                        if (userOpt.isPresent()) {
                            librarian.removeUser(userOpt.get());
                        } else {
                            System.out.println("⚠ User not found.");
                        }
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input. Please enter the correct data type.");
                sc.nextLine(); // clear buffer
            } catch (Exception e) {
                System.out.println("⚠ An error occurred: " + e.getMessage());
            }
        } while (choice != 0);
    }
}
