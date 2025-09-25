import java.util.*;

// Book Class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() { this.isAvailable = false; }
    public void returnBook() { this.isAvailable = true; }

    @Override
    public String toString() {
        return title + " by " + author + " [ISBN: " + isbn + "] " + 
               (isAvailable ? "(Available)" : "(Issued)");
    }
}

// Member Class
class Member {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getMemberId() { return memberId; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrowBook();
            borrowedBooks.add(book);
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by " + name);
        }
    }
}

// Library Class
class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to the library.");
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member " + member.getName() + " registered successfully.");
    }

    public void showAvailableBooks() {
        System.out.println("\nAvailable Books in Library:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add Books
        Book b1 = new Book("Clean Code", "Robert Martin", "111");
        Book b2 = new Book("Java Basics", "James Gosling", "222");
        Book b3 = new Book("Data Structures", "Cormen", "333");
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Register Members
        Member m1 = new Member("Ankit", 1);
        Member m2 = new Member("Shashwat", 2);
        library.registerMember(m1);
        library.registerMember(m2);

        // Borrow & Return
        library.showAvailableBooks();
        m1.borrowBook(b1);
        m2.borrowBook(b1);  // Already borrowed
        m2.borrowBook(b2);

        library.showAvailableBooks();

        m1.returnBook(b1);
        library.showAvailableBooks();
    }
}