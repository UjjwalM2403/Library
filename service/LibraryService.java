package service;

import EntityPackage.Book;
import EntityPackage.Librarian;
import EntityPackage.User;
import repository.LibraryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryService {
    Scanner sc;

    public LibraryService() {
        this.sc = new Scanner(System.in);
    }

    private int parseIntSafe(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\u26A0 Invalid integer input: '" + input + "'. Defaulting to 0.");
            return 0;
        }
    }

    private double parseDoubleSafe(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("\u26A0 Invalid decimal input: '" + input + "'. Defaulting to NaN.");
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
                System.out.println("4. Sort Books");
                System.out.println("5. Pay Fine");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(sc.nextLine());

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

                    case 4 -> {
                        System.out.println("\n--- Sort Books ---");
                        System.out.println("1. Sort by Title");
                        System.out.println("2. Sort by Genre");
                        System.out.println("3. Sort by Price");
                        System.out.print("Enter sorting choice: ");
                        String sortInput = sc.nextLine();
                        int sortChoice = parseIntSafe(sortInput);

                        List<Book> sortedBooks = new ArrayList<>(LibraryRepository.books);

                        switch (sortChoice) {
                            case 1 -> sortedBooks.sort(Comparator.comparing(Book::getTitle));
                            case 2 -> sortedBooks.sort(Comparator.comparing(Book::getGenre));
                            case 3 ->
                                sortedBooks.sort(Comparator.comparingDouble(book -> book.getPrice().doubleValue()));
                            default -> System.out.println("⚠ Invalid sorting choice.");
                        }

                        System.out.println("\n--- Sorted Books ---");
                        for (Book book : sortedBooks) {
                            System.out.println(book);
                        }
                    }

                    case 5 -> {
                        System.out.print("Enter fine amount: ");
                        String fineInput = sc.nextLine();
                        double fine = parseDoubleSafe(fineInput);
                        user.payFine(fine);
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\u26A0 Invalid input. Please enter a number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\u26A0 An error occurred: " + e.getMessage());
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
                System.out.println("4. Show Books");
                System.out.println("5. Add User");
                System.out.println("6. Remove User");
                System.out.println("7. Show Users");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                String choiceInput = sc.nextLine();
                choice = parseIntSafe(choiceInput);

                switch (choice) {
                    case 0 -> System.out.println("Exiting librarian menu...");

                    case 1 -> {
                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("Price: ");
                        double price = parseDoubleSafe(sc.nextLine());
                        System.out.print("Genre: ");
                        String genre = sc.nextLine();
                        System.out.print("Quantity: ");
                        int quantity = parseIntSafe(sc.nextLine());

                        Book book = new Book(title, author, price, genre, quantity);
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
                            System.out.println("\u26A0 Book not found.");
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter book title to find: ");
                        String title = sc.nextLine();
                        librarian.findBook(title);
                    }

                    case 4 -> {
                        System.out.println("\n--- List of All Books ---");
                        if (LibraryRepository.books.isEmpty()) {
                            System.out.println("⚠ No Books present.");
                        } else {
                            for (Book book : LibraryRepository.books) {
                                System.out.println(book);
                            }
                        }
                    }

                    case 5 -> {
                        System.out.print("Enter user name: ");
                        String name = sc.nextLine();
                        System.out.print("ID: ");
                        int id = parseIntSafe(sc.nextLine());
                        boolean userExists = LibraryRepository.users.stream()
                                .anyMatch(u -> u.getId() == id);

                        if (userExists) {
                            System.out.println("⚠ User with ID " + id + " already exists! User not added.");
                            break;
                        }
                        System.out.print("Age: ");
                        int age = parseIntSafe(sc.nextLine());
                        System.out.print("Contact: ");
                        String contact = sc.nextLine();
                        System.out.print("Address: ");
                        String address = sc.nextLine();

                        User user = new User(name, id, age, contact, address);
                        librarian.addUser(user);
                    }

                    case 6 -> {
                        System.out.print("Enter user ID to remove: ");
                        int id = parseIntSafe(sc.nextLine());
                        Optional<User> userOpt = LibraryRepository.users.stream()
                                .filter(u -> u.getId() == id)
                                .findFirst();
                        if (userOpt.isPresent()) {
                            librarian.removeUser(userOpt.get());
                        } else {
                            System.out.println("\u26A0 User not found.");
                        }
                    }
                    case 7 -> {
                        System.out.println("\n--- List of All Users ---");
                        if (LibraryRepository.users.isEmpty()) {
                            System.out.println("⚠ No users registered.");
                        } else {
                            for (User user : LibraryRepository.users) {
                                System.out.println(user);
                            }
                        }
                    }
                    default -> System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("\u26A0 An error occurred: " + e.getMessage());
            }
        } while (choice != 0);
    }
}
