/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.primefaces.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import nu.te4.primefaces.demo.enteties.Book;

@Named
@ApplicationScoped
public class BookBean {

    private List<Book> books;
    private Book book;

    @PostConstruct
    public void populateFromDatabase() {
        books = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM books";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                String title = data.getString("title");
                String author = data.getString("author");
                int price = data.getInt("price");
                books.add(new Book(title, author, price));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        book = new Book();
    }

    public void addBook() {
        books.add(book);
        try ( Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO books VALUES(?,?,?)");
            stmt.setString(1, book.getTitle() );
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getPrice());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
