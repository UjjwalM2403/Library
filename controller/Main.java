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

        User demoUser = new User("Alice", 1, 25, "1234567890", "Delhi");
        Librarian demoLibrarian = new Librarian("Mr. Sharma", "Delhi Central", "9876543210");

        LibraryRepository.users.add(demoUser);

        int choice;
        do {
            System.out.println("\n====== Library Management System ======");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Librarian");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> service.userActions(demoUser);
                case 2 -> service.librarianActions(demoLibrarian);
                case 0 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }
}