import java.util.Scanner;

public class LibraryCatalog {
    private static final int MAX_BOOKS = 100;
    private static String[] titles = new String[MAX_BOOKS];
    private static String[] authors = new String[MAX_BOOKS];
    private static String[] isbns = new String[MAX_BOOKS];
    private static boolean[] availability = new boolean[MAX_BOOKS];
    private static int numBooks = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize sample data
        initializeSampleData();

        System.out.println("Welcome to the library catalog system");

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    searchForBook(scanner);
                    break;
                case 2:
                    checkoutBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the library catalog system. bye");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Search for a book");
        System.out.println("2. Checkout a book");
        System.out.println("3. Return a book");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
        return choice;
    }

    private static void searchForBook(Scanner scanner) {
        System.out.print("Enter book title, author, or ISBN: ");
        String query = scanner.nextLine().toLowerCase();

        System.out.println("Search Results:");
        for (int i = 0; i < numBooks; i++) {
            if (titles[i].toLowerCase().contains(query) || authors[i].toLowerCase().contains(query) || isbns[i].toLowerCase().contains(query)) {
                System.out.println("Title: " + titles[i]);
                System.out.println("Author: " + authors[i]);
                System.out.println("ISBN: " + isbns[i]);
                System.out.println("Availability: " + (availability[i] ? "Available" : "Not Available"));
                System.out.println();
            }
        }
    }

    private static void checkoutBook(Scanner scanner) {
        System.out.print("Enter the ISBN of the book you want to checkout: ");
        String isbn = scanner.nextLine();

        int index = findBookIndex(isbn);
        if (index == -1) {
            System.out.println("Book not found.");
        } else if (availability[index]) {
            availability[index] = false;
            System.out.println("Book successfully checked out.");
        } else {
            System.out.println("Book is already checked out.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter the ISBN of the book you want to return: ");
        String isbn = scanner.nextLine();

        int index = findBookIndex(isbn);
        if (index == -1) {
            System.out.println("Book not found.");
        } else if (!availability[index]) {
            availability[index] = true;
            System.out.println("Book successfully returned.");
        } else {
            System.out.println("Book is already in the library.");
        }
    }

    private static int findBookIndex(String isbn) {
        for (int i = 0; i < numBooks; i++) {
            if (isbns[i].equalsIgnoreCase(isbn)) {
                return i;
            }
        }
        return -1;
    }

    private static void initializeSampleData() {
        addBook("justbook", "Aone", "1");
        addBook("thefunny", "Atwo", "2");
        addBook("Abook", "Athree", "3");
    }

    private static void addBook(String title, String author, String isbn) {
        if (numBooks < MAX_BOOKS) {
            titles[numBooks] = title;
            authors[numBooks] = author;
            isbns[numBooks] = isbn;
            availability[numBooks] = true;
            numBooks++;
        }
    }
}
