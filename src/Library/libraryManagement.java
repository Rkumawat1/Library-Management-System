
package Library;

/**
 *
 * @author asus
 */
import java.util.Scanner;

public class libraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySQL librarySQL = new LibrarySQL(); // Database connection and methods

        System.out.println("Welcome to Library Management System");
        while (true) {
            System.out.println("\n1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int option = getValidIntInput(scanner);
            switch (option) {
                case 1:
                    librarySQL.adminMenu(); // Admin functionality
                    break;
                case 2:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}