import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Library library = new Library();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
        sc.close();
    }

    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Patrons");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = getIntInput();
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    bookMenu();
                    break;
                case 2:
                    patronMenu();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Thanks for using the Library Management System Team 44. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }


    public static void bookMenu() {
        int choice;
        do {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Edit Book");
            System.out.println("5. Search Books");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = getIntInput();
            if (choice == -1) continue;


            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    editBook();
                    break;
                case 5:
                    searchBooksMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public static void patronMenu() {
        int choice;
        do {
            System.out.println("\n--- Patron Management ---");
            System.out.println("1. Register Patron");
            System.out.println("2. Display Patrons");
            System.out.println("3. Edit Patron");
            System.out.println("4. Search Patron by ID");
            System.out.println("5. Books per Patron");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = getIntInput();
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    registerPatron();
                    break;
                case 2:
                    library.displayPatrons();
                    break;
                case 3:
                    editPatron();
                    break;
                case 4:
                    searchPatronById();
                    break;
                case 5:
                    booksPerPatron();
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
    
    public static void addBook() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter number of copies: ");
        int copies = getIntInput();
        if (copies == -1) {
            System.out.println("Invalid number of copies.");
            return;
        }
        if (copies < 0) {
            System.out.println("Number of copies cannot be negative.");
            return;
        }
        library.addBook(new Book(title, author, isbn, copies));
    }

    public static void removeBook() {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = sc.nextLine();
        library.removeBook(isbn);
    }

    public static void editBook() {
        System.out.print("Enter ISBN of the book to edit: ");
        String isbn = sc.nextLine();
        library.editBook(isbn);
    }

    public static void searchBooksMenu() {
        int choice;
        do {
            System.out.println("\n--- Search Books By ---");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. ISBN");
            System.out.println("0. Back to Book Menu");
            System.out.print("Enter your choice: ");

            choice = getIntInput();
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    searchBooksByAuthor();
                    break;
                case 3:
                    searchBooksByISBN();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public static void searchBooksByTitle() {
        System.out.print("Enter title to search: ");
        String title = sc.nextLine();
        List<Book> books = library.findBooksByTitle(title);  //Corrected method call
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static void searchBooksByAuthor() {
        System.out.print("Enter author to search: ");
        String author = sc.nextLine();
        List<Book> books = library.findBooksByAuthor(author); //Corrected method call
        if (books.isEmpty()) {
            System.out.println("No books found by that author.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static void searchBooksByISBN() {
        System.out.print("Enter ISBN to search: ");
        String isbn = sc.nextLine();
        Book book = library.findBookByIsbn(isbn); //Corrected method call
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("No book found with that ISBN.");
        }
    }


    public static void registerPatron() {
        System.out.print("Enter patron name: ");
        String name = sc.nextLine();
        System.out.print("Enter patron ID: ");
        String id = sc.nextLine();
        System.out.print("Enter contact details: ");
        String contact = sc.nextLine();
        library.registerPatron(new Patron(name, id, contact));
    }

    public static void editPatron() {
        System.out.print("Enter ID of the patron to edit: ");
        String id = sc.nextLine();
        library.editPatron(id);
    }
    public static void searchPatronById() {
        System.out.print("Enter ID of the patron to search: ");
        String id = sc.nextLine();
        Patron patron = library.findPatronById(id); //Corrected method call
        if (patron != null) {
            System.out.println(patron);
        } else {
            System.out.println("No Patron found with that ID.");
        }
    }
    public static void booksPerPatron() {
        System.out.print("Enter patron ID: ");
        String patronId = sc.nextLine();
        library.booksPerPatron(patronId);
    }

    public static void borrowBook() {
        System.out.print("Enter ISBN of the book to borrow: ");
        String borrowIsbn = sc.nextLine();
        System.out.print("Enter patron ID: ");
        String patronId = sc.nextLine();
        library.borrowBook(borrowIsbn, patronId);
    }

    public static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String returnIsbn = sc.nextLine();
        System.out.print("Enter patron ID: "); // Prompt for patron ID
        String patronId = sc.nextLine();  // Read patron ID
        library.returnBook(returnIsbn, patronId); 
    }

    private static int getIntInput() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next(); // Consume the invalid input
            return -1; // Indicate an error
        } finally {
            sc.nextLine(); // Consume the newline character
        }
    }
}