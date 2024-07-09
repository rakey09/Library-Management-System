public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int yearPublished;

    public Book(int id, String title, String author, String isbn, String publisher, int yearPublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }
}
