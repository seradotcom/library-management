import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String id;
    private String contactDetails;
    private List<Book> borrowedBooks;

    // Constructor
    public Patron(String name, String id, String contactDetails) {
        this.name = name;
        this.id = id;
        this.contactDetails = contactDetails;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addBook(Book book) {
        borrowedBooks.add(book);
    }
    
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }


    @Override
    public String toString() {
        return "Patron{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}