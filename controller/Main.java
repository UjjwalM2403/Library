package controller;

import EntityPackage.Librarian;
import EntityPackage.User;
import repository.LibraryRepository;
import service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        // Demo data
        LibraryRepository.users.add(new User("Alice", 1, 25, "1234567890", "Delhi"));
        Librarian demoLibrarian = new Librarian("Mr. Sharma", "Delhi Central", "9876543210");

        int choice=-1;
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
    }
}
