package controller;

import EntityPackage.Book;
import EntityPackage.Librarian;
import EntityPackage.User;
import repository.LibraryRepository;
import service.LibraryService;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        // Demo data
        LibraryRepository.users.add(new User("Alice", 1, 25, "1234567890", "Delhi"));
        LibraryRepository.users.add(new User("Sanyam", 2, 21, "123434310", "UP"));
        Librarian demoLibrarian = new Librarian("Mr. Sharma", "Delhi Central", "9876543210");
        Book b1 = new Book("The Alchemist", "Paulo Coelho", 299.99, "Fiction", 10);
        Book b2 = new Book("Atomic Habits", "James Clear", 450.50, "Self-help", 7);
        Book b3 = new Book("Clean Code", "Robert C. Martin", 650.00, "Programming", 5);
        Book b4 = new Book("Sapiens", "Yuval Noah Harari", 499.00, "History", 8);
        Book b5 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 399.75, "Fantasy", 12);
        
        LibraryRepository.books.addAll(Arrays.asList(b1, b2, b3, b4, b5));


        int choice = -1;
        do {
            try {
                System.out.println("\n====== Library Management System ======");
                System.out.println("1. Login as User");
                System.out.println("2. Login as Librarian");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter your User ID: ");
                        int userId = Integer.parseInt(sc.nextLine());

                        User existingUser = LibraryRepository.users.stream()
                                .filter(u -> u.getId() == userId)
                                .findFirst()
                                .orElse(null);

                        if (existingUser != null) {
                            service.userActions(existingUser);
                        } else {
                            System.out.println("⚠ User not found with ID: " + userId);
                        }
                    }

                    case 2 -> service.librarianActions(demoLibrarian);

                    case 0 -> System.out.println("Exiting system. Goodbye!");

                    default -> System.out.println("Invalid choice! Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("⚠ Unexpected error: " + e.getMessage());
            }
        } while (choice != 0);
        sc.close();
    }
}
