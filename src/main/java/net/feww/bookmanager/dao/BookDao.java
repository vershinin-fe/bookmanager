package net.feww.bookmanager.dao;

import net.feww.bookmanager.model.Paging;
import net.feww.bookmanager.model.Book;

import java.util.List;

public interface BookDao {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks(Paging paging, String searchString);

    public Long getCurrentCount(String searchString);
}
