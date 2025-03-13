import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Book> books;
    private List<Patron> patrons;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Method to remove a book by ISBN
    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book with ISBN " + isbn + " removed.");
    }

    // Method to display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Method to register a patron
    public void registerPatron(Patron patron) {
        patrons.add(patron);
        System.out.println("Patron registered: " + patron.getName());
    }

    // Method to display all patrons
    public void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons registered.");
        } else {
            for (Patron patron : patrons) {
                System.out.println(patron);
            }
        }
    }

    // Method to borrow a book
    public void borrowBook(String isbn, String patronId) {
        Book book = findBookByIsbn(isbn);
        Patron patron = findPatronById(patronId);

        if (book != null && patron != null) {
            if (book.getCopiesAvailable() > 0) {
                book.setCopiesAvailable(book.getCopiesAvailable() - 1);
                System.out.println("Book borrowed: " + book.getTitle());
                patron.addBook(book);
            } else {
                System.out.println("No copies available for: " + book.getTitle());
            }
        } else {
            System.out.println("Book or patron not found.");
        }
    }

    // Method to return a book
    public void returnBook(String isbn, String patronId) {
        Book book = findBookByIsbn(isbn);
        Patron patron = findPatronById(patronId); 
    
        if (book != null && patron != null) {
            if (patron.getBorrowedBooks().contains(book)) {
                patron.returnBook(book);
                book.setCopiesAvailable(book.getCopiesAvailable() + 1); 
                System.out.println("Book returned: " + book.getTitle());
            } else {
                System.out.println("This patron did not borrow this book.");
            }
        } else {
            System.out.println("Book or patron not found.");
        }
    }

    // Method to find a book by ISBN
    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Method to find books by title
    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to find books by author
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to find a patron by ID
    public Patron findPatronById(String id) {
        for (Patron patron : patrons) {
            if (patron.getId().equals(id)) {
                return patron;
            }
        }
        return null;
    }

    // Method to show how many books a patron has borrowed
    public void showBorrowedBooks(String patronId) {
        Patron patron = findPatronById(patronId);
        if (patron != null) {
            int borrowedBooksCount = patron.getBorrowedBooks().size();
            System.out.println("Patron " + patron.getName() + " has borrowed " + borrowedBooksCount + " books.");
        } else {
            System.out.println("Patron not found.");
        }
    }
    // Method to show books borrowed by a patron
    public void booksPerPatron(String patronId) {
        Patron patron = findPatronById(patronId);
        if (patron != null) {
            System.out.println("Patron: " + patron.getName());
            List<Book> borrowedBooks = patron.getBorrowedBooks();
            System.out.println("Number of books borrowed: " + borrowedBooks.size());
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        } else {
            System.out.println("Patron not found.");
        }
    }
    // Method to edit book details
    public void editBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            System.out.println("Current details:");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Copies: " + book.getCopiesAvailable());

            System.out.print("Enter new title: ");
            String newTitle = Main.sc.nextLine();

            System.out.print("Enter new author: ");
            String newAuthor = Main.sc.nextLine();

            System.out.print("Enter new number of copies: ");
            int newCopies = Main.sc.nextInt();

            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCopiesAvailable(newCopies);

            System.out.println("Book details updated.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to edit patron details
    public void editPatron(String id) {
        Patron patron = findPatronById(id);
        if (patron != null) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Current details:");
            System.out.println("Name: " + patron.getName());
            System.out.println("ID: " + patron.getId());
            System.out.println("Contact: " + patron.getContactDetails());

            System.out.print("Enter new contact details: ");
            String newContact = sc.nextLine();

            patron.setContactDetails(newContact);

            System.out.println("Patron contact details updated.");
        } else {
            System.out.println("Patron not found.");
        }
    }
}