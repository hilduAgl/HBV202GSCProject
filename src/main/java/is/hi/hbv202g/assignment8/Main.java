package is.hi.hbv202g.assignment8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        LibraryUI libraryUI = new LibraryUI();

        // Register the UI as an observer
        librarySystem.addObserver(libraryUI);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Show menu
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Faculty Member");
            System.out.println("3. Add Book");
            System.out.println("4. List Books");
            System.out.println("5. List Users");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter choice (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Is fee paid? (true/false): ");
                    boolean feePaid = scanner.nextBoolean();
                    librarySystem.addStudentUser(studentName, feePaid);
                    break;

                case 2:
                    System.out.print("Enter faculty name: ");
                    String facultyName = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    librarySystem.addFacultyMemberUser(facultyName, department);
                    break;

                case 3:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    try {
                        librarySystem.addBookWithTitleAndNameOfSingleAuthor(bookTitle, authorName);
                        System.out.println("Book added successfully!");
                    } catch (EmptyAuthorListException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    librarySystem.listBooks();
                    break;

                case 5:
                    librarySystem.listUsers();
                    break;

                case 6:
                    System.out.print("Enter your name (user): ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter book title to borrow: ");
                    String borrowBookTitle = scanner.nextLine();
                    librarySystem.borrowBook(userName, borrowBookTitle);
                    break;

                case 7:
                    System.out.print("Enter your name (user): ");
                    String returnUserName = scanner.nextLine();
                    System.out.print("Enter book title to return: ");
                    String returnBookTitle = scanner.nextLine();
                    librarySystem.returnBook(returnUserName, returnBookTitle);
                    break;

                case 8:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }
}
