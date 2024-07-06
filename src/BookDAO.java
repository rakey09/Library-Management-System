package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {
    public void addBook(String title, String author, String isbn, String publisher, int yearPublished) throws SQLException {
        String query = "INSERT INTO books (title, author, isbn, publisher, year_published) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, publisher);
            stmt.setInt(5, yearPublished);
            stmt.executeUpdate();
        }
    }
    public List<Book> getAllBooks() throws SQLException {
        String query = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("publisher"), rs.getInt("year_published"));
                books.add(book);
            }
        }
        return books;
    }

    public void updateBook(int id, String title, String author, String isbn, String publisher, int yearPublished) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, publisher = ?, year_published = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, publisher);
            stmt.setInt(5, yearPublished);
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("publisher"), rs.getInt("year_published"));
                    books.add(book);
                }
            }
        }
        return books;
    }

    
    
}

